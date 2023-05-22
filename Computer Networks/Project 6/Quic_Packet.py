
import math
import struct

from cryptography.hazmat.backends import default_backend

from scapy.layers.dot11 import algorithms
from scapy.layers.ipsec import Cipher, modes
from scapy.layers.tls.crypto.hkdf import TLS13_HKDF
from scapy.layers.tls.crypto.suites import TLS_AES_128_GCM_SHA256
from scapy.layers.tls.extensions import TLS_Ext_ServerName, ServerName, \
    TLS_Ext_CSR, OCSPStatusRequest, TLS_Ext_SignatureAlgorithms, TLS_Ext_ALPN, \
    ProtocolName, TLS_Ext_SupportedVersion_CH, TLS_Ext_SupportedGroups
from scapy.layers.tls.handshake import TLS13ClientHello, TLS13Certificate, \
    TLSEncryptedExtensions, TLSCertificateVerify, TLSFinished, TLS13ServerHello
from scapy.layers.tls.keyexchange_tls13 import TLS_Ext_KeyShare_CH, \
    KeyShareEntry
from transport_ext import QUIC_Ext_Transport

class Variable_Integer():
    __slots__ = ["number", "bytes"]

    def __init__(self, val=0):
        if type(val) is int:
            self.number = val
        elif type(val) is bytes:
            self.bytes = val
        elif type(val) is str:
            self.bytes = bytes(val, 'utf-8')

    def encode_integer(self, num=None):
        if num is None:
            num = self.number
        self.number = num

        # two bits are used to encode integer length
        encoded_length_bits = 2
        # We need atleast 1 bit that translate in atleast 1 byte
        # bit_length() returns 0 if the integer is 0
        bit_length = num.bit_length()
        bit_length = max(bit_length, 1)

        byte_length = math.ceil((bit_length + encoded_length_bits) / 8)
        byte_length = max(byte_length, 1)

        encoded_length = math.ceil(math.log(byte_length, 2))
        encoded_num = num | encoded_length << (bit_length - 1)

        bin_num = encoded_num.to_bytes(byte_length, 'big')
        bin_array = bytearray(bin_num)

        # Note that in QUIC1 the encoded length is put in the two most significant bits
        # This will override the first two most valuable bits with the length
        bin_array[0] = bin_array[0] | (encoded_length << 6)

        self.bytes = bytes(bin_array)
        return self.bytes

    def decode(self, data=None):
        if data is None:
            data = self.bytes
        # The length of variable-length integers is encoded in the
        # first two bits of the first byte.
        byte_array = bytearray(data)
        v = byte_array[0]
        prefix = v >> 6
        length = 1 << prefix

        # Once the length is known, remove these bits and read any
        # remaining bytes.
        v = v & 0x3f
        for x in range(1, length):
            v = (v << 8) + byte_array[x]
        self.number = v
        return self.number, length

class QUIC():

    public_flags = {'FIXED' : 64,'PKT_NO_1': 0x00,'PKT_NO_2': 0x10,'PKT_NO_4': 0x20}
    header_type = {'SHORT':0,'LONG':128}
    packet_type = {'INITIAL':0,'0RTT':16,'HANDSHAKE':32,'RETRY':64}
    version = int.to_bytes(1, 4, 'big')
    pkt_no = 0x0000001
    initial_salt = bytes.fromhex('38762cf7f55934b34d179ae6a4c80cadccbb7f0a')
    aes_tag_len = 16
    required_len = 1250

    def __init__(self,SCID,DCID,add_ack = False,largest_ack = None,add_crypto = False,token = None,pkt_type = 'INITIAL',client=True):
        self.header_form = 1
        self.client=client
        self.fixed_bit = 1
        if pkt_type == 'INITIAL':
            self.long_packet_type = 0x00
        elif pkt_type == 'HANDSHAKE':
            self.long_packet_type = 0x02
        self.reserved_bits = 0
        self.packet_number_length = 0x00
        self.version = 0x1
        self.dest_conn_id_length = 0x08
        self.DCID = DCID
        self.src_conn_id_length = 0x08
        self.SCID = SCID

        self.token_length = 0x00
        self.token = b""
        self.packet_number = 0x0000001
        self.packet_header()
        self.add_ack = add_ack
        self.largest_ack = largest_ack
        self.add_crypto = add_crypto
        if pkt_type == 'INITIAL':
            if self.add_crypto:
                self.crypto_frame = self.get_crypto_frame()
            else:
                self.crypto_frame = b''
            if self.add_ack:
                self.ack_frame = self.get_ack_frame()
            else:
                self.ack_frame = b''

        if pkt_type == 'HANDSHAKE':
            self.crypto1 = self.handshake(1)
            self.crypto2 = self.handshake(2)
            self.crypto3 = self.handshake(3)


        self.derive_initial_secrets()
        self.derive_key_iv_hp()

        header_len = len(self.header)
        if pkt_type == 'INITIAL':
            crypto_frame_len = len(self.crypto_frame) + QUIC.aes_tag_len if self.add_crypto else 0
            padding_len = QUIC.required_len - (header_len + crypto_frame_len + len(self.ack_frame))
            padding = b'\x00' * padding_len
            self.payload = self.ack_frame + self.crypto_frame + padding
            total_len = Variable_Integer(
                len(self.ack_frame) + crypto_frame_len + padding_len + self.packet_number_length + 1).encode_integer()
        elif pkt_type == 'HANDSHAKE':
            crypto_frame_len = len(self.crypto1) + QUIC.aes_tag_len
            crypto_frame_len += len(self.crypto2) + QUIC.aes_tag_len
            crypto_frame_len += len(self.crypto3) + QUIC.aes_tag_len
            padding_len = QUIC.required_len - (header_len + crypto_frame_len+2*len(self.ping_frame()))
            padding = b'\x00' * padding_len
            self.payload = self.crypto1 +self.ping_frame()+ self.crypto2 + self.ping_frame()+self.crypto3  + padding
            total_len = Variable_Integer(crypto_frame_len +2*len(self.ping_frame())+ padding_len + self.packet_number_length + 1).encode_integer()

        self.header = self.header + total_len + self.packet_number.to_bytes(
            self.packet_number_length + 1, 'big')
        print('without encryption', self.header, self.payload)
        self.encrypt_payload()
        self.encrypt_header()
        self.packet = self.encrypted_header + self.encrypted_payload
        

    def packet_header(self):
        packet_header = ((self.header_form ) << 7) | ((self.fixed_bit) << 6) | ((self.long_packet_type) << 4) | ((self.reserved_bits) << 2) | (self.packet_number_length)
        print('public flags',bin(packet_header))
        packet_header = struct.pack("!B",packet_header)
        print('public flags',packet_header)
        packet_header += struct.pack("!I", self.version)
        packet_header += struct.pack("!B", self.dest_conn_id_length)
        packet_header += self.DCID
        packet_header += struct.pack("!B", self.src_conn_id_length)
        packet_header += self.SCID
        packet_header += struct.pack("!B", self.token_length)
        packet_header += self.token
        self.header =  packet_header

    def encrypt_payload(self):
        nonce = int.from_bytes(self.initial_vector, byteorder='big') ^ self.packet_number
        nonce = nonce.to_bytes(12, byteorder='big')
        encryptor = Cipher(algorithms.AES(self.key), modes.GCM(nonce),
                           backend=default_backend()).encryptor()
        encryptor.authenticate_additional_data(self.header)
        self.encrypted_payload = encryptor.update(
            self.payload) + encryptor.finalize() + encryptor.tag

    def encrypt_header(self):
        PNL = self.packet_number_length + 1

        sample_start = 4 - PNL  
        sample = self.encrypted_payload[sample_start: sample_start + 16]
        print('sample',sample)
        print('header protection key ',self.header_protection_key)
        encryptor = Cipher(algorithms.AES(self.header_protection_key), modes.ECB(),
                           backend=default_backend()).encryptor()
        mask = encryptor.update(sample) + encryptor.finalize()
        print('mask',mask)
        encrypted_header = bytearray(self.header)

        for i in range(PNL):
            encrypted_header[-PNL + i] ^= mask[i + 1]

        encrypted_header[0] ^= (mask[0] & 0x0f)
        self.encrypted_header =  bytes(encrypted_header)

    def get_ack_frame(self):
        frame = b'\x02' + Variable_Integer(self.largest_ack).encode_integer() + Variable_Integer(0).encode_integer()*3
        return frame

    def handshake(self,type):
        certificate = TLS13Certificate(certs = 'server.crt')
        extensions = TLSEncryptedExtensions(ext=[TLS_Ext_ServerName(servernames=[
                                                ServerName(servername="127.0.0.1")
                                            ]),
                                                TLS_Ext_CSR(stype='ocsp',
                                                            req=OCSPStatusRequest()),
                                                TLS_Ext_ALPN(protocols=[
                                                    ProtocolName(
                                                        protocol="hq-interop"),
                                                    # ProtocolName(protocol="h3"),
                                                    ProtocolName(protocol="hq-32"),
                                                ]),
                                                TLS_Ext_SupportedVersion_CH(
                                                    versions=["TLS 1.3"]),
                                                QUIC_Ext_Transport.initial(self.SCID),
                                            ])
        finished = TLSFinished()

        if type == 1:
            initial_data = certificate
        elif type == 2:
            initial_data = extensions
        elif type ==3:
            initial_data = finished

        initial_len = Variable_Integer(len(initial_data)).encode_integer()
        frame = b'\x06' + b'\x00' + initial_len + bytes(initial_data)
        return frame

    def ping_frame(self):
         return  b'\x01'



    def get_crypto_frame(self):

        key_group = 29
        supported_groups = ["x25519"]
        signature_algs = ["sha256+rsaepss",
                          "sha256+rsa"]
        if self.client:
            initial_data = TLS13ClientHello(ciphers=TLS_AES_128_GCM_SHA256,
                                            ext=[TLS_Ext_ServerName(servernames=[
                                                ServerName(servername="127.0.0.1")
                                            ]),
                                                TLS_Ext_CSR(stype='ocsp',
                                                            req=OCSPStatusRequest()),
                                                TLS_Ext_SignatureAlgorithms(
                                                    sig_algs=signature_algs),
                                                TLS_Ext_ALPN(protocols=[
                                                    ProtocolName(
                                                        protocol="hq-interop"),
                                                    # ProtocolName(protocol="h3"),
                                                    ProtocolName(protocol="hq-32"),
                                                ]),
                                                TLS_Ext_SupportedVersion_CH(
                                                    versions=["TLS 1.3"]),
                                                TLS_Ext_SupportedGroups(
                                                    groups=supported_groups),
                                                TLS_Ext_KeyShare_CH(
                                                    client_shares=[KeyShareEntry(
                                                        group=key_group)]),
                                                QUIC_Ext_Transport.initial(self.SCID),
                                            ]
                                            )
        else:
            initial_data = TLS13ServerHello(
                                            ext=[TLS_Ext_ServerName(servernames=[
                                                ServerName(servername="127.0.0.1")
                                            ]),
                                                TLS_Ext_CSR(stype='ocsp',
                                                            req=OCSPStatusRequest()),
                                                TLS_Ext_SignatureAlgorithms(
                                                    sig_algs=signature_algs),
                                                TLS_Ext_ALPN(protocols=[
                                                    ProtocolName(
                                                        protocol="hq-interop"),
                                                    # ProtocolName(protocol="h3"),
                                                    ProtocolName(protocol="hq-32"),
                                                ]),
                                                TLS_Ext_SupportedVersion_CH(
                                                    versions=["TLS 1.3"]),
                                                TLS_Ext_SupportedGroups(
                                                    groups=supported_groups),
                                                TLS_Ext_KeyShare_CH(
                                                    client_shares=[KeyShareEntry(
                                                        group=key_group)]),
                                                QUIC_Ext_Transport.initial(self.SCID),
                                            ]
                                            )

        initial_len = Variable_Integer(len(initial_data)).encode_integer()
        frame = b'\x06'  + b'\x00' + initial_len + bytes(initial_data)

        return frame

    def derive_initial_secrets(self):
        initial_secret = TLS13_HKDF().extract(QUIC.initial_salt, self.DCID)
        print('initial secret',initial_secret)
        client_initial_secret = TLS13_HKDF().expand_label(initial_secret,
                                                       b"client in", b"", 32)
        self.c_initial_secret = client_initial_secret

    def derive_key_iv_hp(self):
        key = TLS13_HKDF().expand_label(self.c_initial_secret, b"quic key", b"", 16)
        iv = TLS13_HKDF().expand_label(self.c_initial_secret, b"quic iv", b"", 12)
        hp = TLS13_HKDF().expand_label(self.c_initial_secret, b"quic hp", b"", 16)
        self.key,self.initial_vector,self.header_protection_key =  key, iv, hp
