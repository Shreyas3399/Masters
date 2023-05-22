from mobile_node import Node
from abc import abstractmethod, ABCMeta
import turtle
class Rod(Node):
    __slots__ = "name", "cord_length", "left_arm","left_child", "right_arm","right_child"

    def __init__(self,name,cord_length : int,left_arm : int,right_arm : int, left_child = None, right_child = None) -> None:
        self.name = name
        self.cord_length = cord_length
        self.left_arm = left_arm
        self.left_child = left_child
        self.right_arm = right_arm
        self.right_child = right_child

    def get_name(self) -> str:
        """
        Get the node's name.
        This is a non-abstract method.
        :return: the name
        """
        return self.name

    def __str__(self) -> str:
        return f"Rod(name={self.name}, cord_length = {self.cord_length}, left_arm = {self.left_arm}, left_child={self.left_child}, right_arm={self.right_arm}, right_child={self.right_child}"

    def get_weight(self) -> int:
        """
        Get the node's weight
        :return: the weight
        """
        return int(self.left_child.get_weight()) + int(self.right_child.get_weight())

    def get_height(self) -> int:
        """
        Get the node's height
        :return: the height
        """
        return int(self.cord_length) + int(max(self.left_child.get_height(), self.right_child.get_height()))

    def get_width(self) -> int:
        """
        Get the total node's width
        :return: the total width
        """
        return int(int(self.get_left_width())+ int(self.get_right_width()))

    def get_left_width(self) -> int:
        """
        Get the total width of the left child
        :return: the left width
        """
        return int(max(int(self.left_arm) + int(self.left_child.get_left_width()), int(self.right_child.get_left_width()) - int(self.right_arm)))

    def get_right_width(self) -> int:
        """
        Get the total width of the right child
        :return: the right width
        """
        return int(max(int(self.right_arm) + int(self.right_child.get_right_width()), int(self.left_child.get_right_width()) -int(self.left_arm)))

    def is_balanced(self) -> bool:
        """
        Whether the node is balanced
        :return:
        """
        leftT = int(int(self.left_arm) * int(self.left_child.get_weight()))
        rightT = int(int(self.right_arm) * int(self.right_child.get_weight()))
        if leftT == rightT:
            return True
        return False

    def get_imbalance(self):
        """
        Get the node's amount of imbalance.
        Imbalance is the different between its left and right torques.
        :return: the node's imbalance.
        """
        leftT = int(int(self.left_arm) * int(self.left_child.get_weight()))
        rightT = int(int(self.right_arm) * int(self.right_child.get_weight()))
        if leftT != rightT:
            return abs(leftT - rightT)
        return 0

    def infix(self) -> str:
        """
        Get a infix string representation of the node
        :return: the infix string
        """
        if self.name == None:
            return ""
        else:   
            return "("+self.left_child.infix()+" "+self.name+" "+self.right_child.infix()+")" 


    def print_pretty(self, tabs: str) -> str:
        """
        Get a preorder string representation of the node
        :param tabs: the indentation level
        :return: the preorder string
        """
        if self.name == None:
            return ""
        else:
            opt = self.name+"\n" 
            tabs += "    "         
            opt+= tabs+""+self.left_child.print_pretty(tabs)+"\n"
            opt+= tabs+""+self.right_child.print_pretty(tabs)+"\n"
        return opt

    def draw(self, t: turtle) -> None:
        """
        Draw the node using Turtle.
        :pre: pen down, facing south, position: top of the node's cord
        :post: pen down, facing south, position: top of the node's cord
        :param t: the turtle's instance
        :return: None
        """
        t.down()
        t.forward(int(self.cord_length)//2)
        t.up()
        t.left(90)
        t.forward(20)
        t.write("L"+self.cord_length)        
        t.back(20)
        t.back(30)
        t.write(self.name)
        t.forward(30)
        t.right(90)
        t.down()
        t.forward(int(self.cord_length)//2)
        t.right(90)
        t.forward(int(self.left_arm))
        t.left(90)
        t.write("L"+self.left_arm)
        self.left_child.draw(t)
        t.up()
        t.right(90)
        t.back(int(self.left_arm))
        t.down()
        t.right(180)
        t.forward(int(self.right_arm))
        t.right(90)
        t.write("L"+self.right_arm)
        self.right_child.draw(t)
        t.left(90)
        t.back(int(self.right_arm))
        t.right(90)
        t.back(int(self.cord_length))


    def find(self, name: str) -> 'Node':
        """
        Find a node by name
        :param name: the node's name
        :return: the node or None
        """
        if self.name != name:
            oupt = self.left_child.find(name)
            if oupt == None:
                oupt = self.right_child.find(name)
            return oupt 
        return self