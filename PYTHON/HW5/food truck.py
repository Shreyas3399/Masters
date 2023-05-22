# """
# CSCI-603: Assignment (week 5)
# Author: Shreyas Belkune
# File: food_truck.py
# This is a program to solve the food truck problem
# To understand of quick and quiclselect sort work
# """
import sys
import random
import time

def readFile(file_name):
    """
    Read from the file and store the integer values in a list
    :param file_name: name of the file to be read
    :return distances: list with every buidling distance
    """
    distances = []
    with open(file=file_name) as f:
        lines = f.readlines()
        for line in lines:
            line = line.strip()
            arr = line.split(' ')
            distances.append(int(arr[1]))
    return distances

def sumofDistance(aList , median):
    """
    Calculating the total distance of every building from the median position of the food truck
    :param aList,median : The distances of the building and the position of the food truck
    :return distance: Returning the total of the distances
    """
    distance = 0
    for i in range(len(aList)):
        distance = distance + abs(median - aList[i])
    return distance

    
def partition_elements(aList , pivot):
    """
    Divides the list according to the pivot
    :param aList, pivot: List to be sorted and position to be considered
    return lists: List containing values smaller, equal and higher than the pivot
    """
    i = 0
    equal_list = []
    greater_list = []
    less_list = []
    while(i<len(aList)):
        if aList[i]>aList[pivot]:
            greater_list.append(aList[i])
        elif aList[i]==aList[pivot]:
            equal_list.append(aList[i])
        elif aList[i] < aList[pivot]:
            less_list.append(aList[i])
        i+=1
    return less_list,equal_list,greater_list    


def quickSort(aList):
    """
    Implements quickSort
    :param aList: Accepts list to be sorted
    return lists: returns the sorted list
    """
    if len(aList)==0:
        list = []
        return list
    else: 
        pivot = random.randint(0, len(aList)-1)
        less_list,equal_list,greater_list = partition_elements(aList, pivot)
        return quickSort(less_list) + equal_list + quickSort(greater_list)

def quickSelect(k,aList):
    """
    Implements quickSelect Sort
    :param k, aList: k is the median position and list to be sorted in order to find k
    return lists: returns the value of k
    """
    pivot = random.randint(0, len(aList)-1)
    less_list,equal_list,greater_list = partition_elements(aList, pivot)
    m = len(less_list)
    equal = len(equal_list)
    if m<=k<(m+equal):
        return aList[pivot]
    elif m>k:
        return quickSelect(k,less_list)
    else:
        return quickSelect(k-m-equal,greater_list)

def checkquicksort(distances):
    """
    Runs quick sort and prints the relevant outputs 
    :param aList: Accepts list to be sorted
    """
    print("Using QuickSort")
    start = time.time()
    sorted = quickSort(distances)
    end = time.time()
    print("The time it took to perform the search, in seconds: ",end-start)
    median = len(sorted)//2
    print("The food truck Location at median : ",sorted[median])
    print("The sum of the distances from the optimal location, to all the other buildings: ",sumofDistance(sorted , sorted[median])
)


def checkquickSelect(distances):
    """
    Runs quick sort and prints the relevant outputs and calculates k required for quickSelect.
    :param aList: Accepts list to be sorted
    """
    print("Using Quick Select ")
    k = len(distances)//2
    start = time.time()
    median = quickSelect(k,distances)  
    end = time.time()
    print("The time it took to perform the search, in seconds: ",end-start)
    print("The food truck Location at median : ",median)
    print("The sum of the distances from the optimal location, to all the other buildings: ",sumofDistance(distances , median))



def main():
    """
    Accepts file from CLI and calls necessary functions to generate relevant output 
    """
    try:
        file_name = sys.argv[1]
        distances = readFile(file_name=file_name)
        checkquicksort(distances)
        checkquickSelect(distances)    
    except Exception as e:
        print("Some exception occured", e.with_traceback())


if __name__ == "__main__":
    main()