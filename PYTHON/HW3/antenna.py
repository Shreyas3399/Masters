# """
# CSCI-603: Assignment (week 3)
# Author: Shreyas Belkune

# This is a program to write 
# the usage of recursion.
# """
import math                                             #required for calculating Diagonal of the square
import turtle

t = turtle.Turtle()
t.shape("turtle")                                       #setting the shape of the turtle
t.speed(0)                                              #setting speed of the turtle

def userInput()->None:
    """
    Takes the input from the user for the command
    :return: None
    """
    n = input('Length of initial side:')
    level = input('Number of levels: ')
    if level.isnumeric()==True and n.isnumeric()==True:
        level = int(level)
        n = int(n)      
        fucn(level,n)
    else :
        print("Wrong Input Try Again")
        userInput()


def fucn(level,n): 
    """
    Sets up the turtle to draw the Antennas for both methods
    :return: None
    """
    centre = ((math.sqrt(2))*n)
    t.up()
    t.right(90)
    t.forward(centre*2)
    t.left(135)
    draw(level,n)
    draw(level,n)
    t.left(45)
    t.up()
    t.forward(centre*1.5)
    t.right(90)
    input("Hit enter to continue...")
    t.clear()
    print("Strategy 2 - Antenna's length is 2000.0 units.", side(level,n))


def strat1(level,n):
    """
    Function to execute the First Strat
    :return: None
    """
    if level == 1:
        t.down()
        t.forward(n)
    else:
        strat1(level-1,n/2)
        t.left(90)
        strat1(level-1,n/2)
        t.right(90)
        strat1(level-1,n/2)
        t.right(90)
        strat1(level-1,n/2)
        t.left(90)
        strat1(level-1,n/2)

def draw(level,n):
    """
    Function to execute the First Strat
    :return: None
    """
    strat1(level,n)
    t.left(90)
    strat1(level,n)
    t.left(90)

def side(level, n):
    """
    Function to execute the Second Strat
    :return: None
    """   
    centre  = (math.sqrt(2)*n)/2
    if level == 1:
        t.up()
        t.right(90)
        t.forward(centre)
        t.left(135)
        t.down()
        t.forward(n)
        t.left(90)
        t.forward(n)
        t.left(90)
        t.forward(n)
        t.left(90)
        t.forward(n)
        t.up()
        t.left(135)
        t.forward(centre)
        t.right(90)
        return n
    else:
        distance =0
        distance+=side(level-1,n/2)
        t.forward(centre*(level/2))
        distance+=side(level-1,n/2)
        t.backward(centre*(level/2))
        t.left(90)
        t.forward(centre*(level/2))
        distance+=side(level-1,n/2)
        t.backward(centre*(level/2))
        t.left(90)
        t.forward(centre*(level/2))
        distance+=side(level-1,n/2)
        t.backward(centre*(level/2))
        t.left(90)
        t.forward(centre*(level/2))
        distance+=side(level-1,n/2)
        t.backward(centre*(level/2))
        t.left(90)
        return distance



def main() -> None:
    userInput()
    turtle.mainloop()


if __name__ == '__main__':
    main()
