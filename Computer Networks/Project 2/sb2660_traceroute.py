# """
# CSCI-651: Project 2 
# Author: Shreyas Belkune
# File: sb2660_traceroute.py
# This program creates an ICMP packet with varting TTL
# To find the traceroute to a particular destination
# """
import socket
import struct
from scapy.all import ICMP
import time
import sys

def traceroute(dest_address, queries):
    """
    Creates packet with incrementing TTL until destination is reached
    """
    summary = ""
    not_answered = 0
    dest_addr = dest_address
    dest_addr = socket.gethostbyname(dest_addr)
    print("Destination IP Address",dest_addr)
    raw_socket = socket.socket(socket.AF_INET, socket.SOCK_RAW, socket.IPPROTO_ICMP)
    for i in range(1,64):   
        l = 0
        print("Hop number",i)
        for j in range(queries):
            raw_socket.settimeout(5)
            try:
                icmp_packet = ICMP(type = 8, code = 0, id = 1234, seq = 1)
                payload = bytes(8)
                packet = icmp_packet / payload
                icmp_bytes = bytes(packet)
                raw_socket.setsockopt(socket.IPPROTO_IP, socket.IP_TTL, i)
                start = time.time()
                raw_socket.sendto(icmp_bytes, (dest_addr, 0)) 
                response, address = raw_socket.recvfrom(1024)
                try:
                    hostname = socket.gethostbyaddr(address[0])[0]
                except socket.herror as e:
                    hostname = address[0]
                end = time.time()
                rtt = (end - start)*1000
                rtt = round(rtt,3)
                icmp_header = response[20:28]
                type, code, checksum, packet_id, sequence = struct.unpack("bbHHh", icmp_header)
                if type == 11 or type == 0:
                    print("packet no.",j+1, "Recieved Response",hostname,"",rtt,"ms")
            except socket.timeout:
                not_answered += 1
                summary += "Probe not answered for hop "+str(i)+"\n"
                print("*    ",end = '')
            finally:
                pass 
        print('')
        if dest_addr == str(address[0]):
            print("Destination Reached",address[0])
            break
    return summary, not_answered
    
def main():
    if len(sys.argv) <= 1:
        print("No arguments passed")
    if len(sys.argv) == 3:
        traceroute(sys.argv[2],3)    
    if len(sys.argv) == 5:
        if sys.argv[3] == "queries":
            traceroute(sys.argv[2], int(sys.argv[4]))
    if len(sys.argv) == 4:
        summary, probes = (traceroute(sys.argv[2],3))
        print("==============================")
        print("Total unanswered Probes",probes)
        print(summary)
main()