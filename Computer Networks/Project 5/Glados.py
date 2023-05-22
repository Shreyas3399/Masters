import socket
import threading
import time
import pickle

ip_address = 'localhost'
count1, count2 = 0, 0
neighbors = {"Queeg" : (5, "Queeg"), "Rhea" : (1, "Rhea")}
default = {"Queeg" : (5, "Queeg"), "Rhea" : (1, "Rhea"), "self" : "Glados"}
router_id = "Glados"

def send_messages():
    while True:
        global default
        try:
            my_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
            address = (ip_address, 5005)
            my_socket.sendto(pickle.dumps(default), address)
            address = (ip_address, 5003)
            my_socket.sendto(pickle.dumps(default), address)
            my_socket.close()
            time.sleep(1)
        except ConnectionRefusedError:
            pass

def receive_messages():
    global default
    while True:
        my_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
        my_socket.settimeout(6)
        try:
            address = (ip_address, 5006)
            my_socket.bind(address)
            data, recvaddr = my_socket.recvfrom(1024)
            if data is not None:
                my_socket.close()
                new_data = dvr(data)
                default = new_data
        except socket.timeout:
            dead_router("Queeg")
            dead_router("Rhea")
            print_table(default)

def dvr(data):  
    global count1, count2, default
    data = pickle.loads(data)
    if data["self"] == "Queeg":
        count1 = 0
        count2 += 1
    elif data["self"] == "Rhea":
        count1 += 1
        count2 = 0
    update_table(data)
    print_table((default))
    new_message = default   
    return new_message

def print_table(table):
    print("Routing Table for Glados",)
    print("Destination\tCost\t\tNexthop")
    print("========================================")
    for each in default:
        if each != "self":
            print(each,"\t\t",default[each][0],"\t\t",default[each][1])
    print("========================================")

def update_table(table):
    lock = threading.Lock()
    global count1, count2
    with lock:
        global default, neighbors
        if count1 >= 6:
            dead_router("Queeg")
        if count2 >= 6:
            dead_router("Rhea")
        for each in table:
            if each not in default and each != router_id:
                default[each] = (default[table["self"]][0] + table[each][0], table["self"])
        for each in table:
            if each != "self":
                if each in default:
                    if each != "self":
                        if default[each][0] > table[each][0] + default[table["self"]][0]:
                           if each not in neighbors:
                                default[each] = (default[table["self"]][0] + table[each][0], table["self"])
                        if default[each][0] == 16:
                            if each not in neighbors:
                                if table[each][0] != 16:
                                    default[each] = (default[table["self"]][0] + table[each][0], table["self"] )
                        if table[each][0] == 16:
                            if each not in neighbors and each in default:
                                default[each] = (16, default[each][1])
        if table[default["self"]][0] != 16:
            default[table["self"]] = (table[default["self"]][0], table["self"])

def dead_router(router):
    global default
    down_route = ""
    default[router] = (16, router)
    for each in default:
        if each!= "self":
            if default[each][1] == router and default[each][0] != 16: 
                down_route = each
    if down_route != "":
        default.pop(down_route)            

def main(): 
    send_thread = threading.Thread(target=send_messages)
    receive_thread = threading.Thread(target=receive_messages)
    print_table(default)
    send_thread.start()
    receive_thread.start()
    send_thread.join()
    receive_thread.join()

main()