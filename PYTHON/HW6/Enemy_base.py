# """
# CSCI-603: Assignment (week 6)
# Author: Shreyas Belkune
# File: Enemy_base.py
# To store and deploy guerrilla and hostage according to the situation
# """
from Bunker import Bunker
from Player import Player
from Player import Role
from Chopper import Chopper
import stack, queue
import random

class Enemy_base:
    __slots__ = "num_hostages", "num_guerillas", "rnd", "q", "s"
    
    def __init__(self, num_hostages, num_guerillas, rnd) -> None:
        """
        Initialize values of the variables and add soldiers to the queue
        :param num_soldiers: Number of soldiers
        """
        self.num_hostages = num_hostages
        print(self.num_hostages)
        self.num_guerillas = num_guerillas
        self.rnd = rnd
        self.q = queue.Queue()
        self.s = stack.Stack()
        for i in range(1,num_hostages+1):
            self.s.push(Player(i,Role.HOSTAGE))
        for i in range(1,num_guerillas+1):
            self.q.enqueue(Player(i,Role.GUERIILA))


    def add_guerrilla(self, Player):
        """
        Enqueue Guerrilla to the queue after defeating soldier
        """
        self.num_guerillas += 1
        self.q.enqueue(Player)

    def add_hostage(self, Player):
        """
        Enqueue hostage to the stack after soldier fails against guerrilla
        """        
        self.num_hostages += 1
        self.s.push(Player)
    
    def get_guerrilla(self):
        """
        Dequeue guerrilla from the queue and send it to fight soldier
        """
        if self.q.is_empty == True:
            return False
        else:
            guerrilla = self.q.peek()
            self.num_guerillas -= 1
            self.q.dequeue()
            return guerrilla

    def get_hostage(self):
        """
        Pop hostage from the stack and send hold it until outcome of the fight
        """
        if self.s.is_empty == True:
            return "No hostages"
        else:
            hostage = self.s.peek()
            self.num_hostages -= 1
            self.s.pop()
            return hostage

    def get_num_guerrillas(self):
        """
        Return: Number of Guerrilla
        """       
        return self.num_guerillas
        
    def get_num_hostages(self):
        """
        Return: Number of hostages
        """
        return self.num_hostages

    def rescue_hostage(self, soldier):
        """
        Simulate fight between Soldier and Guerrilla
        Return: hostage if saved or else none
        """
        print(f"{soldier} enters enemy base...")
        hostage = self.get_hostage()        
        if self.q.is_empty() == True:
            return hostage
        else:
            guerrilla = self.get_guerrilla()
            roll = random.randint(1, 100)
            print(f"{soldier} battles {guerrilla} who rolls a #{roll}")
            if roll>20:
                soldier.print_victory_message(guerrilla)
                return hostage
            guerrilla.print_victory_message(soldier)
            self.add_hostage(hostage)
            self.add_guerrilla(guerrilla)
    