# """
# CSCI-603: Assignment (week 2)
# Author: Shreyas Belkune

# This is a program to write 
# the usage of control structures (if, while, and for).
# """
import turtle

t = turtle.Turtle()

def userInput ():
    """
    Takes the input from the user for the command
    :return: None
    """
    usercmd = input("Please enter the command \n")
    n = 0
    chooser(usercmd, n)

def chooser(usercmd, n):
    """
    Checks which turtle function needs to be called
    :pre position Centre of the Block
    :post: Bottom Left Corner of the Block
    :return: usercmd,n
    """
    command = usercmd[n]
    pos = 0
    Itcount = 0
    if command=='F' or command=='B' or command=='L' or command=='R' or command=='C':
        basic(usercmd, command, n)
    
    elif command=='P' or command=='I':
        Itcount = int(usercmd[n+1])
        pos = n+3
        enhanced(usercmd, command, n)
    
    elif command=='U' or command=='D':
        penOp(usercmd, command, n)
        
    # elif n+1==(len(usercmd)):
    #     print("Finished")

    else:
        
        if n+1==(len(usercmd)):
            print("Finished")
        elif usercmd[n]=='@':
            print()
            # n=n+2
            # count = 0
        # else:
        #  count+=1
        #  chooser(usercmd, n=pos)


def basic(usercmd, command, n):
    """
    Function for running basic TT functions
    :pre position Centre of the Block
    :post: Bottom Left Corner of the Block
    :return: usercmd, command, n
    """
    # print("type of usercmd", type(usercmd))
    movement = int(usercmd[n+1:n+4])
    # print("movement:",type(movement))
    # print("Movment: ",movement)
    if command == 'F':
        print("F",movement)
        moveForward(usercmd,movement,n)

    elif command == 'B':
        print("B",movement)

        moveBack(usercmd,movement,n)
    
    elif command == 'L':
        print("L",movement)
        turnLeft(usercmd,movement,n)

    elif command == 'R':
        print("R",movement)
        turnRight(usercmd,movement,n)

    elif command == 'C':
        print("C",movement)
        drawCircle(usercmd,movement,n)

def enhanced(usercmd, command, n):
    """
    Function for running enhanced TT functions
    :pre position Centre of the Block
    :post: Bottom Left Corner of the Block
    :return: usercmd, command, n
    """
    if command == 'P':
        movement = int(usercmd[n+3:n+6])
        print("P",movement)
        drawPolygon(usercmd, movement, n)
    else:
        Iterative(usercmd,n)

def Iterative(usercmd, n):
    """
    Function for running Iterative Commands
    :pre position Centre of the Block
    :post: Bottom Left Corner of the Block
    :return: usercmd, n
    """
    iterations = int(usercmd[n+1])
    n=n+3
    for j in range(iterations):
        chooser(usercmd,n)
    n = n+12
    chooser(usercmd,n)

def penOp(usercmd, command, n):
    """
    Function for choosing pen Operations
    :pre position Centre of the Block
    :post: Bottom Left Corner of the Block
    :return: usercmd, n
    """
    if command == 'U':
        penUp(usercmd,n)

    elif command == 'D':
        penDown(usercmd,n)



def moveForward(usercmd,movement,n):
    """
    Function to move the turtle forward
    :pre position Centre of the Block
    :post: Bottom Left Corner of the Block
    :return: usercmd ,n
    """
    t.forward(movement)
    n=n+5
    chooser(usercmd,n)

def moveBack(usercmd,movement,n):
    """
    Function to move the turtle back
    :pre position Centre of the Block
    :post: Bottom Left Corner of the Block
    :return: usercmd ,n
    """
    t.back(movement)
    n=n+5
    chooser(usercmd,n)

def turnLeft(usercmd,movement,n):
    """
    Function to turn it left
    :pre position Centre of the Block
    :post: Bottom Left Corner of the Block
    :return: usercmd ,n
    """
    t.left(movement)
    n=n+5
    print(n)
    chooser(usercmd,n)

def turnRight(usercmd,movement,n):
    """
    Function to turn it right
    :pre position Centre of the Block
    :post: Bottom Left Corner of the Block
    :return: usercmd ,n
    """
    t.right(movement)
    n=n+5
    chooser(usercmd,n)


def penUp(usercmd,n):
    """
    Function to stop drawing
    :pre position Centre of the Block
    :post: Bottom Left Corner of the Block
    :return: usercmd ,n
    """
    t.up()
    n=n+2
    chooser(usercmd,n)

def penDown(usercmd,n):
    """
    Function to start Drawing
    :pre position Centre of the Block
    :post: Bottom Left Corner of the Block
    :return: usercmd ,n
    """
    t.down()
    n=n+2
    chooser(usercmd,n)

def drawCircle(usercmd,movement,n):
    """
    Function to draw Circle
    :pre position Centre of the Block
    :post: Bottom Left Corner of the Block
    :return: usercmd ,n
    """
    t.circle(movement)
    n=n+5
    chooser(usercmd,n)

def drawPolygon(usercmd,movement,n):
    """
    Function to draw polygon of size specified
    :pre position Centre of the Block
    :post: Bottom Left Corner of the Block
    :return: usercmd ,n
    """
    side = int(usercmd[n+1])

    exteriorAngle = int(360/side)
    for i in range(side):
        t.forward(movement)
        t.left(exteriorAngle)
    n=n+7
    chooser(usercmd,n)

def main() -> None:
    """
    main function
    :pre position Centre of the Block
    :post: Bottom Left Corner of the Block
    :return: None
    """
    userInput()
    turtle.mainloop()

if __name__ == '__main__':
    main()
# I2 I4 F100 L090 @ F100 @
# I4 P3 100 F100 @ F200
# "I4 P3 100 F100 @"
# "I2 I4 F100 L090 @ F100 @ I4 P3 100 F100 @"
