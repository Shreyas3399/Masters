from enum import Enum

class Player:
    """
    """
    __slots__ = "id", "role"
    def __init__(self,id,role,):
        self.id = id
        self.role = role

    def __str__(self):
        return (f"{self.role.name} #{self.id}")
    
    def print_victory_message(self,opponent):
        print(f"{self} defeats {opponent}")

class Role(Enum):
    GUERIILA = 4
    HOSTAGE = 3
    PREDATOR = 2
    SOLDIER = 1