# """
# CSCI-603: Assignment (week 7)
# Author: Shreyas Belkune
# File: linkedhashedset.py
# This program creates a linklist with three node links
# To understand how linkedlist, hashing and OOP in Python
# """
from typing import Any
from set import SetType

MIN_BUCKETS = 10
__abstractmethods__ = frozenset()   
class ChainNode:
    __slots__ = "obj", "prev", "next", "fwd"

    def __init__(self, obj: Any, prev: 'ChainNode' = None, next: 'ChainNode' = None, fwd: 'ChainNode' = None):
        """
        Initialize all the variables to be used in the class
        :param variable: name of the all the variables
        """
        self.obj = obj
        self.prev = prev
        self.next = next
        self.fwd = fwd
    
    def __repr__(self) -> str:
        """
        Return a string with the string representation of the object
        in this node and the object of the node to which it is linked.
        """
        return str(self.obj)+ " ->" +repr(self.fwd)

    def __str__(self) -> str:
        """
        Return the string representation of the object in this node
        """
        return str(self.obj)

class LinkedHashSet(SetType):
    MIN_BUCKETS = 10
    __slots__ = "initial_num_buckets", "load_limit", "capacity", "hash_table", "front", "back"

    def __init__(self, initial_num_buckets: int = 10, load_limit: float = 0.75):
        """
        Initialize all the variables to be used in the class
        :param variable: name of the all the variables
        """
        SetType.__init__(self)
        self.initial_num_buckets = initial_num_buckets
        self.load_limit = load_limit
        self.capacity = max(initial_num_buckets, MIN_BUCKETS)
        self.hash_table = [None for i in range(0, self.capacity)]
        self.front = None
        self.back = None

    def __iter__( self ):
        """
        Build an Iterator
        :return: an iterator for the current elements in the set
        """
        i = self.front
        while i!= None:
            yield i
            i = i.next

    def __len__(self):
        """
        Return the number of elements in this set.
        :return: the number of elements in the set.
        """
        return self.size
    
    def __repr__(self) -> str:
        """
        Return a string with the content of the hash table and information about the hash table such as
        the table's capacity, size, current load factor and load limit.
        """
        print("Output")
        print("-------")
        print (f"Capacity: {self.capacity}, Size: {self.size}, Load Factor: {self.size/self.capacity}, Load Limit: {self.load_limit}\n\n")
        space = "    "
        print("    Hash Table")
        print("    -----------")
        s = ""
        for i in range(0,len(self.hash_table)):
            s += space +str(i)+": "+repr(self.hash_table[i])+"\n"
        return s

    def __str__(self) -> str:
        """
        Return a string representation of the objects added to this set sorted by insertion order.

        """
        print("This is str")        
        s = "{"
        node = self.front
        while(node!=None):
            s+= str(node.val)+", "
            node = node.next
        s += s[:-2] + "}"
        return s
    
    def add(self, obj):
        """
        Insert a new object into the hash table and remember when it was added
        relative to other calls to this method. However, if the object is
        added multiple times, the hash table is left unchanged, including the
        fact that this object's location in the insertion order does not change.
        Double the size of the table if its load_factor exceeds the load_limit.
        :param obj: the object to add
        :return: True if obj has been added to this set
        """
        key = self.hash_function(obj)
        key = key % self.capacity
        if self.contains(obj):
            return False
        if self.size == 0:
            self.hash_table[key] = ChainNode(obj, prev = self.back)
            self.front=self.hash_table[key]
            self.back = self.hash_table[key]
            self.size +=1
            return True
        if self.hash_table[key] == None:
            self.hash_table[key] = ChainNode(obj, prev = self.back)
            self.size +=1
            self.back.next = self.hash_table[key]
            self.back = self.hash_table[key]
        else:
            n = self.hash_table[key]
            while n.fwd != None:
                n = n.fwd
            n.fwd = ChainNode(obj,prev = self.back)
            self.back.next = n.fwd
            self.back = n.fwd

        if self.size/self.capacity > self.load_limit:
            # print("rehash")
            ptr = self.front
            self.size = 0
            self.capacity *= 2
            self.hash_table.clear()
            for i in range(self.capacity):
                self.hash_table.append(None)
            self.front = None
            self.back = None
            while ptr != None:
                self.add(ptr.obj)
                ptr = ptr.next



    def contains(self, obj):
        """
        Is the given obj in the set?
        :return: True iff obj or its equivalent has been added to this set
        """
        key = self.hash_function(obj)
        key = key % self.capacity
        if self.hash_table[int(key)] == None:
            return False
        else:
            n = self.hash_table[key]
            while n!= None and n.obj != obj:
                n = n.fwd
            if n is None:
                return False
            else: 
                return True

    
    def remove(self, obj):
        """
        Remove an object from the hash table (and from the insertion order).
        Resize the table if its size has dropped below
        (1-load_factor)*current_size.
        :param obj: the value to remove; assumes hashing and equality work
        :return: True iff the obj has been remove from this set
        """
        key = self.hash_function(obj)
        key = key % self.capacity
        if self.contains(obj):
            if self.front.obj == obj:
                if self.front.fwd:
                    self.hash_table[key] = self.front.fwd
                else:
                    self.hash_table[key] = None
                self.front = self.front.next
            elif self.back.obj == obj:
                print("true")
                if self.hash_table[key] == self.back:
                    self.back = self.back.prev
                    self.hash_table[key] = None
                else:
                    d = self.hash_table[key]
                    while d.fwd.obj != obj:
                        d = d.fwd
                    self.back = d
                    d.fwd = None
            else:
                d = self.hash_table[key]
                if str(d) is str(obj):
                    self.hash_table[key] = None
                else:
                    currNode= self.hash_table[key]
                    prevNode = None
                    while currNode is not None and currNode.obj is not obj:
                        prevNode = currNode
                        currNode = currNode.fwd
                    prevNode.fwd = currNode.fwd
                    currNode.prev.next = currNode.next
            if self.size < (1 - self.load_limit)*self.capacity:
                ptr = self.front
                self.size = 0
                if self.capacity/2 >=10:
                    self.capacity = self.capacity//2
                    self.hash_table.clear()
                    for i in range(int(self.capacity)):
                        self.hash_table.append(None)
                    self.front = None
                    self.back = None
                    while ptr != None:
                        self.add(ptr.obj)
                        ptr = ptr.next
                else:
                    return False

            
    def hash_function(self,str):
        """
        Returns a hashvalue which is used to calculate the key
        :param variable: String 
        :return: hashvalue computed
        """
        ans = 0
        exp = 1
        for i in range(len(str)):
            ans = ans + ord(str[i])*exp
            exp = exp * 31
        return ans

def main():
    table = LinkedHashSet(100)
    table.add("a")
    table.add("b")
    table.add("c")
    table.add("d")
    table.add("e")
    table.remove("a")
    print(table.__len__())

    print(repr(table))

if __name__ == '__main__':
    main()