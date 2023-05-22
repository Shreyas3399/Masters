# """
# CSCI-651: Project 1   
# Author: Shreyas Belkune
# File: pktsniffer.py
# This program reads a pcap file and filters according to the user
# and prints the filtered packets details according to the header
# """
from scapy.all import *
import sys
import ipaddress

def parser(pack) -> None:
    """
    Recieves packet from pkt_filter() 
    parses the data recieved bit by bit 
    and prints the output
    argument - pack
    return - None
    """
    a = pack
    for i in range(len(a)):
        print("Packet number:",i+1)
        pkt = a[i]
        pkt_raw = raw(pkt)
        hex_dump = list(pkt_raw)
        for i in range(len(hex_dump)):
            hex_dump[i] = bin(hex_dump[i]).replace("0b", "")
        hex_dump = [str(item).zfill(8) for item in hex_dump]
        print(hex_dump)
        break
        print("Ethernet Header")
        print("============================================")
        dest_mac = str(hex(int(hex_dump[0],2))),".",str(hex(int(hex_dump[1],2))),".",str(hex(int(hex_dump[2],2))),".",str(hex(int(hex_dump[3],2))),".",str(hex(int(hex_dump[4],2))),".",str(hex(int(hex_dump[5],2)))
        dest_mac = "".join(dest_mac)
        dest_mac = dest_mac.replace('0x','')
        print("Destination MAC address:",dest_mac)
        source_mac = str(hex(int(hex_dump[6],2))),".",str(hex(int(hex_dump[7],2))),".",str(hex(int(hex_dump[8],2))),".",str(hex(int(hex_dump[9],2))),".",str(hex(int(hex_dump[10],2))),".",str(hex(int(hex_dump[11],2)))
        source_mac = "".join(source_mac)
        source_mac = source_mac.replace('0x','')
        print("Source MAC address:",source_mac)
        type = hex(int(hex_dump[12]+hex_dump[13],2))
        print("Ether Type:",type)
        print("============================================")
        bin_ipv_ihl = hex_dump[14]
        ipv = bin_ipv_ihl[:4]
        ihl = bin_ipv_ihl[4:]
        ipv = int(ipv, 2)
        ihl = int(ihl, 2)
        tos = hex_dump[15]
        total_length = hex_dump[16] + hex_dump[17]
        total_length = int(total_length, 2)
        identify = hex_dump[18]+ hex_dump[19]
        identify = int(identify, 2)
        flag = hex_dump[20]
        frag_off = flag[3:] + hex_dump[21]
        flag = flag[:3]
        frag_off = int(frag_off, 2)
        flag = int(flag,2)
        ttl = int(hex_dump[22],2)
        protocol = int(hex_dump[23],2)
        header_checksum = int(hex_dump[24]+hex_dump[25],2)
        source_ip = str(int(hex_dump[26],2)),".",str(int(hex_dump[27],2)),".",str(int(hex_dump[28],2)),".",str(int(hex_dump[29],2))
        source_ip = "".join(source_ip)
        dest_ip = str(int(hex_dump[30],2)),".",str(int(hex_dump[31],2)),".",str(int(hex_dump[32],2)),".",str(int(hex_dump[33],2))
        dest_ip = "".join(dest_ip)
        if protocol == 17:
            protocol_name = "UDP"
        elif protocol == 1:
            protocol_name = "ICMP"
        elif protocol == 6:
            protocol_name = "TCP"
        print("IP HEADER")
        print("============================================")
        print("IP Version:",ipv)
        print("Header Length:",(ihl*4),"bytes")
        print("Type of Service:",tos)
        print("Total Lenght:",total_length,"bytes")
        print("Identification:",identify)
        print("Flag: 0x",flag)
        print("Fragment Offset:",frag_off)
        print("Time to live:",ttl,"secs/hop")
        print("Protocol:",protocol,protocol_name)
        print("Header Checksum:",hex(header_checksum))
        print("Source IP Address:",source_ip)
        print("Destination IP Address:",dest_ip)
        print("============================================")
        if protocol == 17:
            print("UDP HEADER")
            print("============================================")
            print("Source Port:",int(hex_dump[34]+hex_dump[35],2))
            print("Destination Port:",int(hex_dump[36]+hex_dump[37],2))
            print("Length:",int(hex_dump[38]+hex_dump[39],2))
            print("Checksum:",hex(int(hex_dump[40]+hex_dump[41],2)))            
        elif protocol == 1:
            print("ICMP HEADER")
            print("============================================")
            print("Type:",int(hex_dump[34],2))
            print("Code:",int(hex_dump[35],2))
            print("Checksum:",hex(int(hex_dump[36]+hex_dump[37],2)))
        elif protocol == 6:
            print("TCP/IP HEADER")
            print("============================================")
            print("Source Port:",int(hex_dump[34]+hex_dump[35],2))
            print("Destination Port:",int(hex_dump[36]+hex_dump[37],2))
            print("Sequence Number:",int(hex_dump[38]+hex_dump[39]+hex_dump[40]+hex_dump[41],2))
            print("Acknowledgement Number:",int(hex_dump[42]+hex_dump[43]+hex_dump[44]+hex_dump[45],2))
            header_length = hex_dump[46]
            header_length = header_length[0:4]
            print("Header Length:",int(header_length,2)*4,"bytes")
            flag_tcp = hex_dump[46]+hex_dump[47]
            flag_tcp = flag_tcp[4:16]
            print("Flag:",hex(int(flag_tcp,2)))
            print("Window:",int(hex_dump[48]+hex_dump[49],2))
            print("Checksum:",hex(int(hex_dump[50]+hex_dump[51],2)))
            print("Urgent Pointer",int(hex_dump[52]+hex_dump[53],2))
        print("============================================")
        print("============================================")
        print("")

def pkt_filter(filter_exp,val,pack):
    """
    Recieves orignal packet from main()
    reads the filter condition  
    and calls the parse function by passing the filtered list as argument
    argument - pack
    return - None
    """
    a = []
    if filter_exp == 'host':
        a = [pkt for pkt in pack if pkt.haslayer(IP) and pkt[IP].src == val]
    elif filter_exp == 'port':
        a = [pkt for pkt in pack if pkt.haslayer(IP) and (pkt.dport == int(val) or pkt.sport == int(val))]
    elif filter_exp == 'c':
        a = pack[0:int(val)]
    elif filter_exp == 'ip':
        print("true")
        a = [pkt for pkt in pack if pkt.haslayer(IP) and (pkt[IP].src == val or pkt[IP].dst == val)]
    elif filter_exp == 'tcp' or filter_exp == 'udp' or filter_exp == 'icmp':
        a = [pkt for pkt in pack if pkt.haslayer(IP) and pkt.haslayer(filter_exp.upper())]
    elif filter_exp == 'net':
        val = ipaddress.ip_network(val)
        a = [pkt for pkt in pack if pkt.haslayer(IP) and ipaddress.ip_address(pkt[IP].src) in val or ipaddress.ip_address(pkt[IP].dst) in val ]   
    print("Number of filtered packets: ",len(a))
    parser(a)

def main():
    if len(sys.argv) <= 1:
        print("No arguments passed")
    pack = sys.argv[1]
    pack = rdpcap(pack)
    print(pack)
    if len(sys.argv) == 2:
        parser(pack)
    if len(sys.argv) == 3:
        pkt_filter(sys.argv[2],None,pack)
    elif len(sys.argv) == 4:
        pkt_filter(sys.argv[2],sys.argv[3],pack)

if __name__ == '__main__':
    main()