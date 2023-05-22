from mobile_node import Node
from abc import abstractmethod, ABCMeta
import turtle
class Ball(Node):
    __slots__ = "name", "cord_length", "radius", "weight"

    def __init__(self,name : any , cord_length : int, radius : int, weight : int) -> None:
        self.name = name
        self.cord_length = cord_length
        self.radius = radius
        self.weight = weight
    
    def __str__(self) -> str:
        return f"Ball(name={self.name}, cord={self.cord_length}, radius={self.radius}, weight={self.weight})"

    def get_name(self) -> str:
        """
        Get the node's name.
        This is a non-abstract method.
        :return: the name
        """
        return self.name
        
    def get_weight(self) -> int:
        """
        Get the node's weight
        :return: the weight
        """
        return int(self.weight)

    def get_height(self) -> int:
        """
        Get the node's height
        :return: the height
        """
        return int(self.cord_length) + int(self.radius)

    def get_width(self) -> int:
        """
        Get the total node's width
        :return: the total width
        """
        return int(2*(int(self.get_left_width())+int(self.get_right_width())))

    def get_left_width(self) -> int:
        """
        Get the total width of the left child
        :return: the left width
        """
        return int(self.radius)

    def get_right_width(self) -> int:
        """
        Get the total width of the right child
        :return: the right width
        """
        return int(self.radius)

    def is_balanced(self) -> bool:
        """
        Whether the node is balanced
        :return:
        """
        return True

    def get_imbalance(self):
        """
        Get the node's amount of imbalance.
        Imbalance is the different between its left and right torques.
        :return: the node's imbalance.
        """
        return 0

    def infix(self) -> str:
        """
        Get a infix string representation of the node
        :return: the infix string
        """
        if self.name!= None:
            return "("+self.name+")"

    def print_pretty(self, tabs: str) -> str:
        """
        Get a preorder string representation of the node
        :param tabs: the indentation level
        :return: the preorder string
        """
        if self.name!= None:
            return self.name
            
    def draw(self, t: turtle) -> None:
        """
        Draw the node using Turtle.
        :pre: pen down, facing south, position: top of the node's cord
        :post: pen down, facing south, position: top of the node's cord
        :param t: the turtle's instance
        :return: None
        """
        t.color("green")
        t.forward(int(self.cord_length)//2)
        t.up()
        t.left(90)
        t.forward(10)
        t.write("L"+self.cord_length)
        t.back(10)
        t.down()
        t.right(90)
        t.forward(int(self.cord_length)//2)
        t.up()
        t.forward(int(self.radius))
        t.forward(int(self.radius))
        t.forward(10)
        t.write(self.name)
        t.back(10)
        t.back(int(self.radius))
        t.left(90)
        t.write("R"+self.radius)
        t.down()
        t.forward(int(self.radius))
        t.color("red")
        t.up()
        t.forward(10)
        t.write("W"+self.weight)
        t.back(10)
        t.down()
        t.color("green")
        t.right(270)
        t.circle(int(self.radius))
        t.up()
        t.left(270)
        t.back(int(self.radius))
        t.right(90)
        t.back(int(self.radius))
        t.back(int(self.cord_length))
        t.color("black")

        

    def find(self, name: str) -> 'Node':
        """
        Find a node by name
        :param name: the node's name
        :return: the node or None
        """
        if self.name != name:
            return None
        return self