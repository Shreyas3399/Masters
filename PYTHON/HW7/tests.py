""" 
file: tests.py
description: Verify the LinkedHashSet class implementation
"""

__author__ = "YOUR NAME HERE"

from linkedhashset import LinkedHashSet


def print_set(a_set):
    for word in a_set:  # uses the iter method
        print(word, end=" ")
    print()


def test0():
    table = LinkedHashSet(100)
    table.add("to")
    table.add("do")
    table.add("is")
    table.add("to")
    table.add("be")
    table.remove("be")

    print_set(table)
    print(repr(table))

    table1 = LinkedHashSet(10)
    table1.add("a")
    table1.add("b")
    table1.add("c")
    table1.add("d")
    table1.add("e")
    table1.remove("a")
    print_set(table1) 
    print(repr(table1))

    table2 = LinkedHashSet(10)
    table2.add("Batman")
    table2.add("has")
    table2.add("lots")
    table2.add("of")
    table2.add("gizmos")
    table2.add("on")
    table2.add("in")
    table2.add("his")
    table2.add("Belt")
    table2.remove("in")
    print_set(table2)
    print(repr(table2))


if __name__ == '__main__':
    test0()
