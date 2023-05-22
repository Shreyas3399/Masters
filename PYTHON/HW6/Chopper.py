# """
# CSCI-603: Assignment (week 6)
# Author: Shreyas Belkune
# File: Chopper.py
# To add passengers to the Chopper and rescue them
# """
import stack
class Chopper:
    Max_occupancy = 6

    __slots__ = "chopper", "s", "count", "counter"
    def __init__(self) -> None:
        """
        Initialize values of the variables and add soldiers to the queue
        :param num_soldiers: Number of soldiers
        """
        self.s = stack.Stack()
        self.count = 0
        self.counter = 0
    
    def board_passenger(self,player):
        """
        Add Hostage/soldier to the chopper
        """
    
        self.s.push(player)
        print(f"{player} boards the chopper")
        self.count+=1
        if self.is_full():
            self.rescue_passengers()     

    def get_num_rescued(self):
        """
        Return: Number of passengers rescued
        """
        return self.counter

    def is_empty(self):
        """
        Return: Chopper is empty or not
        """
        if self.s.is_empty() == True:
            return True
        return False
    
    def is_full(self):
        """
        Return: If chopper is full
        """
        if self.count == self.Max_occupancy:
            return True
        else: 
            return False
    
    def rescue_passengers(self):
        """
        Rescue whoever is in the chopper by flying away
        """
        while not self.is_empty():
            player = self.s.peek()
            self.count-=1
            print(f"Chopper Transported {player} to safety")
            self.counter+=1
            self.s.pop()

            
