# """
# CSCI-651: Project 2 
# Author: Shreyas Belkune
# File: sb2660_ping.py
# This program creates an ICMP packet type 8, code 0
# To ping a particular destination
# """
import socket
import struct
from scapy.all import IP , ICMP
import time
import sys
import signal

def ping(count, dest_addr, size):
    """
    Creates packet and waits for ping response and displays the ping result
    """
    raw_socket = socket.socket(socket.AF_INET, socket.SOCK_RAW, socket.IPPROTO_ICMP)
    raw_socket.settimeout(1/2)
    try:
        icmp_packet = ICMP(type = 8, code = 0, id = 1234, seq = count)
        payload = bytes(size)
        packet = icmp_packet / payload
        icmp_bytes = bytes(packet)
        start_time = time.time()
        raw_socket.sendto(icmp_bytes, (dest_addr, 0))
        response, address = raw_socket.recvfrom(1024)
        end_time = time.time()
        ttl = response[8:9]
        ttl = ttl[0]
        packet_size = len(response) + 14
        icmp_header = response[20:28]
        type, code, checksum, packet_id, sequence = struct.unpack("bbHHh", icmp_header)
        try:
            hostname = socket.gethostbyaddr(address[0])[0]
        except socket.herror as e:
            hostname = address[0]
                    
        # Check if the response is an ICMP Echo Reply
        if type == 0 and code == 0:
            rtt = (end_time - start_time)* 1000
            rtt = round(rtt,3)
            print("ICMP_SEQUENCE = "+str(count)+"",packet_size,"bytes received ICMP Echo Reply from ",address[0],"in",rtt,"seconds and with ttl",ttl)
            return 0 
    except socket.timeout:
        print("Request timeout for icmp_seq "+str(count))
        return 1
    finally:
        pass

        # Close the socket  
    raw_socket.close()
def main():
    unaswered = 0
    try:
        sleep_time = 1/10
        if len(sys.argv) <= 1:
            print("No arguments passed")
        count = 0
        if len(sys.argv) == 3:
            while(True):
                unaswered += ping(count, sys.argv[2], 56)
                count+=1
                time.sleep(sleep_time)
        if len(sys.argv) == 5:
            if sys.argv[3] == "c":
                while count != int(sys.argv[4]):
                    ping(count, sys.argv[2], 56)
                    count+=1
                    time.sleep(sleep_time)
            if sys.argv[3] == "wait":  
                while(True):
                    ping(count, sys.argv[2],56)
                    print("Waiting ",sys.argv[4],"seconds .........")
                    time.sleep(int(sys.argv[4]))
                    count+=1
            if sys.argv[3] == "pktsize":
                while(True):
                    ping(count, sys.argv[2], int(sys.argv[4]))
                    count+=1
            if sys.argv[3] == "timeout":
                signal.signal(signal.SIGALRM, exit)
                signal.alarm(int(sys.argv[4]))
                try:
                    while(True):
                        ping(count, sys.argv[2], 56)
                        count += 1
                except Exception as ex:
                    print("Time out!")
        packet_loss = (unaswered/count)*100
        print(str(count)+" transmitted, "+str(count - unaswered)+" packets received, "+str(packet_loss)+" packet loss")
    except KeyboardInterrupt:
        print()
        print("Ping interrupted manually")
        packet_loss = (unaswered/count)*100
        print(str(count)+" transmitted, "+str(count - unaswered)+" packets received, "+str(packet_loss)+" % packet loss")

        
def exit(signum, frame):
    """
    Implemented to exit after a particular timeout occurs
    """
    raise Exception("Timed out ")



if __name__ == '__main__':
    main()