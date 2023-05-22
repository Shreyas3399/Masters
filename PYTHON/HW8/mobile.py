# """
# CSCI-603: Assignment (week 12)
# Author: Shreyas Belkune

# This is a program to play mobile game
# the usage of Trees in python
# """
from rod import Rod
from ball import Ball
import sys
import turtle

def set_pos(t,node):
    t.up()
    t.left(90)
    t.forward(node.get_height()/2)
    if(node.get_left_width()<node.get_right_width()):
        t.left(90)
        t.back((node.get_left_width()-node.get_right_width())/2+10)
        t.left(90)
    elif(node.get_left_width()>node.get_right_width()):
        t.right(90)
        t.forward((node.get_right_width()-node.get_left_width())/2)
        t.right(90)
    else:
        t.right(180)
    t.down()


def commands(node):
    """
    Letting the user play with the commands
    once correct file has been input and parsed
    """
    cmd = input()
    if cmd == 'help':
        print("1. help: print the list of available commands")
        print("2. quit: end the program")
        print("3. root: display the root node of the mobile")
        print("4. balanced: print whether the mobile is balanced or not")
        print("5. print: print the nodes in the mobile in preorder fashion")
        print("6. infix: print the nodes in the mobile in index fashion")
        print("7. find node:  nd and print the node in the mobile")
        print("8. weight node: print the weight of the node in the mobile")
        print("9. height node: print the height of the node in the mobile")
        print("10. width node: print the width of the node in the mobile")
        print("11. draw: draw the mobile")
        commands(node)
    elif cmd == 'quit':
        exit()
    elif cmd == 'root':
        print(node)
        commands(node)
    elif cmd == 'balanced':
        print(node.get_name()+" balanced? "+str(node.is_balanced()))
        print("Imbalance Amount : "+str(node.get_imbalance()))
        commands(node)
    elif cmd == 'print':
        tab = ""
        print(node.print_pretty(tab))
        commands(node)
    elif cmd == 'infix':
        print(node.infix())
        commands(node)
    elif cmd[:4] == 'find':
        fin = cmd[5:]
        print("Found "+str(node.find(fin)))
        commands(node)
    elif cmd[:6] == 'weight':
        weigh = cmd[7:]
        found = node.find(weigh)
        print(weigh+" weight? "+str(found.get_weight()))
        commands(node)
    elif cmd[:6] == 'height':
        heig = cmd[7:]
        found = node.find(heig)
        print(heig+" height? "+str(found.get_height()))
        commands(node)
    elif cmd[:5] == 'width':
        wid = cmd[6:]
        found = node.find(wid)
        print(wid+" width? "+str(found.get_width()))
        print(wid+" left width? "+str(found.get_left_width()))
        print(wid+" right width? "+str(found.get_right_width()))
        commands(node)
    elif cmd == 'draw':
        t = turtle.Turtle()
        sc = turtle.Screen()
        sc.screensize((node.get_width()+25),(node.get_height()+25))
        set_pos(t,node)
        t.speed(5) 
        t.down()
        node.draw(t)
        commands(node)
    else:
        print("Wrong Choice, Try Again")
        commands(node)


def parse(data):
    """
    Using the read file in the list to push and create 
    relevant objects for computation
    """
    line = data[0].split(" ")
    data.pop(0)

    if line[0] == "BALL":
        return Ball(line[1],line[2],line[3],line[4])
    else:
        left = parse(data)
        right = parse(data)
    return Rod(line[1], line[2], line[3], line[4], left, right) 

def file(node):
    """
    Reading text file and using namedTuple to store the information 
    and returning it so that other functions can make use for computation
    """
    data = []
    if len(sys.argv) == 0:
        print("Usage:")
    else:
        try:
            with open(sys.argv[1]) as f:
                for line in f:
                    line = line.strip()
                    if(line == ""):
                        break
                    data.append(line)
            node = parse(data)
            print("Welcome to Mobiles App!")
            print(sys.argv[1]+" loaded and parsed!")
            return node
            
        except FileNotFoundError as e:
            print("File not found: "+sys.argv[1])
    
                

def main():
    """
    Main method to run the mobile
    """
    node=None
    node=file(node)
    commands(node)
    turtle.mainloop()

if __name__ == '__main__':
    main()