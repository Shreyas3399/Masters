# """
# CSCI-603: Assignment (week 6)
# Author: Shreyas Belkune
# File: Rescue_mission.py
# This is a program to simulate Predator movie
# To understand how Stacks, Queues and OOP in Python
# """
import sys
from Chopper import Chopper
from Player import Player
from Player import Role 
import random
from Enemy_base import Enemy_base
from Bunker import Bunker

class Rescue_mission:
    __slots__ = "seed", "num_hostages", "num_soldiers", "num_guerrillas", "bunker", "chopper", "enemy_base", "rnd", "predator"

    def __init__(self, seed, num_hostages, num_soldiers, num_guerrillas):
        """
        Initialize all the variables to be used in the class
        :param variable: name of the all the variables
        """
        self.rnd = random.seed(seed)
        self.num_hostages = num_hostages
        self.num_soldiers = num_soldiers
        self.num_guerrillas = num_guerrillas
        self.bunker = Bunker(self.num_soldiers)
        self.chopper = Chopper()   
        self.enemy_base = Enemy_base(self.num_hostages, self.num_guerrillas, self.rnd)
        self.predator = Player(2, Role.PREDATOR)
        
    def run_simulation(self):
        """
        Rescue Mission simulation to start here and to go on until there are no more hostages left for the soldier to rescue
        """
        print("Get to the Choppa")
        print(self.enemy_base.get_num_hostages())
        while(self.bunker.get_num_soldiers() != 0 and self.enemy_base.get_num_hostages() != 0):   
            
            print("Statistics:  ",self.enemy_base.get_num_hostages()," hostage/s remain, ",self.bunker.get_num_soldiers(),"    soldier/s remain,",self.enemy_base.get_num_guerrillas(),"  guerrilla/s remain,  ",self.chopper.get_num_rescued(),"  rescued")
            soldier = self.bunker.deploy_next_soldier()
            hostage= self.enemy_base.rescue_hostage(soldier)
            if  hostage== None:
                pass
            else:
                print(f"{hostage} rescued from enemy base by soldier {soldier}")
                roll = random.randint(1, 100)
                print(f"{soldier} battles {self.predator} who rolls a #{roll}")
                if roll > 75:
                    soldier.print_victory_message(self.predator)
                    self.chopper.board_passenger(hostage)
                    self.bunker.fortify_soldiers(soldier)
                    pass
                else:
                    self.predator.print_victory_message(soldier)
                    print(f'{hostage} encounters predaztor')
                    roll = random.randint(1, 100)
                    if roll > 50:
                        hostage.print_victory_message(self.predator)
                        self.chopper.board_passenger(hostage)
                    else:
                        self.predator.print_victory_message(hostage)
        print("Statistics:  ",self.enemy_base.get_num_hostages()," hostage/s remain, ",self.bunker.get_num_soldiers(),"    soldier/s remain,",self.enemy_base.get_num_guerrillas(),"  guerrilla/s remain,  ",self.chopper.get_num_rescued(),"  rescued")
        while(self.bunker.get_num_soldiers()!=0 or self.enemy_base.get_num_hostages()!=0):
            soldier = self.bunker.deploy_next_soldier()
            hostage = self.enemy_base.get_hostage()
            self.chopper.rescue_passengers()          
        
        print("Statistics:  ",self.enemy_base.get_num_hostages()," hostage/s remain, ",self.bunker.get_num_soldiers(),"    soldier/s remain,",self.enemy_base.get_num_guerrillas(),"  guerrilla/s remain,  ",self.chopper.get_num_rescued(),"  rescued")


def main():
    """
    Read from the command line argument and pass the input values to run_simulation
    """
    input = sys.argv
    if len(input) == 5:
        r = Rescue_mission(int(input[1]),int(input[2]),int(input[3]),int(input[4]))
        r.run_simulation()
    else:
        print("Wrong Input")

if __name__ == "__main__":
    main()
