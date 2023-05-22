from collections import namedtuple

def string_func():
    string1 = "Shreyas"
    print("Printing String",string1)
    print("Printing an index",string1[0])
    print("Printing an index",string1[-1])
    print("Printing it reverse",string1[::-1])
    print("Slicing the string",string1[3:6])

def list_func():
    list1 = [1,2,3,4,5,6]
    print("Printing the list",list1)
    print("Printing an element",list1[1])
    print("Print length of list",len(list1))
    list1.append(7)
    print("Print list after appending",list1)
    list1.insert(0,0)
    print("Printing after insertion",list1)
    list1.extend([7,8,9])
    print("Printing list after using extend",list1)
    list1.reverse()
    print("Printing list after reversing",list1)
    list1.reverse()
    print("Slicing a List",list1[2:5])
    print("Poping from a list",list1.pop())

def tuple_func():
    t1 = ()
    t1 = (1, 3, False, 6.5)
    print("Indexing function:",t1[0])
    print("Slicing operation",t1[0:2])
    print("Repetation of tuple",t1*2)
    print("Concatination  ",t1+('text',))

def set_func():
    s1 = set()
    s2 = set([1,2,3,4])
    print("Printing set ",s2)
    s2.add(5)
    print("Adding to set",s2)
    s2.remove(3)
    print("After Removing",s2)
    print("Printing empty set:",s1)

def dict_func():
    d1 = dict()
    d2 = {}
    d3 = {"apple":10,"banana":3}
    print("Printing a dictionary",d3)
    d3['pear'] = 5
    print("Printing after insertion",d3)
    d3['pear'] = 15
    print("Printing after updating",d3)
    print("Using lookup operation",d3["banana"])
    d3['potato'] = [1,2,3,4]
    print("Adding multiple elements ",d3)
    for key in d3:
        print(key)
    for key in d3:
        print(key,":",d3[key])
    
def namedTuple_func():
    Student = namedtuple('Student', ['name', 'age', 'DOB'])
    S = Student('Nandini', '19', '2541997')
    M = Student('Shreyas', '22', '331999' )
    print(S[1])
    print(M[0])

def main():
    string_func()
    print("===================================")
    list_func()
    print("===================================")
    tuple_func()
    print("===================================")
    set_func()
    print("===================================")
    dict_func()
    print("===================================")
    namedTuple_func()

main()