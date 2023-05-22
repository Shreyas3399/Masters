# """
# CSCI-603: Assignment (week 6)
# Author: Shreyas Belkune
# File: Bunker.py
# To store and deploy soldiers in the Bunker
# """
from Player import Player
import queue
from Player import Role

class Bunker:

    __slots__ = "num_soldiers", "q"

    def __init__(self, num_soldiers):
        """
        Initialize values of the variables and add soldiers to the queue
        :param num_soldiers: Number of soldiers
        """
        self.num_soldiers = num_soldiers
        self.q = queue.Queue()
        for i in range(1, num_soldiers+1):
            self.q.enqueue(Player(i, Role.SOLDIER))

    def deploy_next_soldier(self):
        """
        Dequeue soldier from the queue and send it to enemy base
        """
        if self.q.is_empty():
            return False
        else:
            soldier=self.q.peek()
            self.q.dequeue()
            self.num_soldiers -= 1
            return soldier

    def fortify_soldiers(self, soldier):
        """
        Return the soldier back to the bunker after successfully saving Hostage
        :param soldier: Soldier to be enqueued
        """
        self.num_soldiers += 1
        self.q.enqueue(soldier)

    def get_num_soldiers(self):
        """
        Return: Number of soldiers
        """
        return self.num_soldiers

    def has_soldiers(self):
        """
        Return: Checking if soldiers left or not
        """
        return self.get_num_soldiers()>0
            
