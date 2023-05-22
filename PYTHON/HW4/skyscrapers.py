# """
# CSCI-603: Assignment (week 4)
# Author: Shreyas Belkune

# This is a program to solve the SkyScrapers Puzzle
# the usage of various Python lists, strings, file handling, dictionaries
# """

from collections import namedtuple


def readFile():
    """
    Reading text file and using namedTuple to store the information and returning it so that other functions can make use for computation
    """
    
    with open('practice.txt') as f:

        input =  f.read().split('\n')
        dimension = input[0]
        dimension = int(dimension)
        RowList = []
        i = 0
        for i in range(dimension):
            str1 = "Row"+ str(i)
            RowList.append(str1)
        
        # print(RowList)
        # print(input[])
        
        # print(input)
        dimension = input[0]
        data = namedtuple('data',['Dimension','Top', 'Right', 'Bottom', 'Left'] + RowList)
        c = data._make(input)
        # print(c)
        return c

def checkRow():
    """
    Extracting the required data and checking for duplicates and invalid cases in Rows.
    """

    dataEntry = readFile()
    dimension = int(dataEntry[0])
    for i in range(dimension):
        # print(dataEntry[i+dimension+1])
        row = dataEntry[i+dimension+1]
        row = row.replace(" ","")
        # print(row)
        for j in range (dimension):
            if int(row[j]) > dimension:
                print("Invalid Case")

        row = set(row)
        if len(row) == dimension:
            break;
        else:
            print("Error in row ",i)
            break



def checkColumn():
    """
    Extracting the required data and checking for duplicates and invalid cases in Columns.
    """
    dataEntry = readFile()
    dimension = int(dataEntry[0])
    column = (dataEntry[5:])
    k = 0
    for l in range(dimension):
        str1 = ""

        for i in range(dimension):
            column = column[i]
            column = column.replace(" ","")
            for j in range(1):
                str1 = str1 + column[k]
            
            column = (dataEntry[5:])
        # print(str1)
        for j in range (dimension):
            if int(str1[j]) > dimension:
                print("Invalid Case")

        str1 = set(str1)
        if len(str1) == dimension :
            break
        else:
            print("Error in Column",l )
        k+=1


def checkClues():
    """
    Function running all the test cases for clues.
    """
    checkTop()
    checkBottom()
    checkLeft()
    checkRight()

def checkTop():
    """
    Extracting the required data and checking if the visibility and clue are similar.
    """
    dataEntry = readFile()
    dimension = int(dataEntry[0])
    column = (dataEntry[5:])
    Top = (dataEntry[1])
    Top = Top.replace(" ","")
    # print(Top)
    k = 0
    for l in range(dimension):
        str1 = ""
        for i in range(dimension):
            column = column[i]
            column = column.replace(" ","")
            for j in range(1):
                str1 = str1 + column[k]
            
            column = (dataEntry[5:])
        # print(str1)
        highest = 0
        visibility = 0
        for j in range(dimension):
            if highest < int(str1[j]):
                highest = int(str1[j])
                visibility+=1
        
        if visibility == int(Top[l]):
            break
        else:
            print(Top[l],"==",visibility)

        k+=1


def checkLeft():
    """
    Extracting the required data and checking if the visibility and clue are similar.
    """
    dataEntry = readFile()
    dimension = int(dataEntry[0])
    Left = dataEntry[4]
    Left = Left.replace(" ","")
    # print(Left)
    for i in range(dimension):
        # print(dataEntry[i+dimension+1])
        row = dataEntry[i+dimension+1]
        row = row.replace(" ","")
        # print(row)  
        highest = 0
        visibility = 0
        for j in range(dimension):
            if highest < int(row[j]):
                highest = int(row[j])
                visibility+=1
        
        if visibility == int(Left[i]):
            break
        else:
            print("Error in checkLeft")
        # print(visibility)

def checkRight():
    """
    Extracting the required data and checking if the visibility and clue are similar.
    """
    dataEntry = readFile()
    dimension = int(dataEntry[0])
    Right = dataEntry[2]
    Right = Right.replace(" ","")
    # print(Left)
    for i in range(dimension-1,-1,-1):
        # print(dataEntry[i+dimension+1])
        row = dataEntry[i+dimension+1]
        row = row.replace(" ","")
        # print(row)  
        highest = 0
        visibility = 0
        for j in range(dimension-1,-1,-1):
            if highest < int(row[j]):
                highest = int(row[j])
                visibility+=1
        
        if visibility == int(Right[i]):
            break
        else:
            print("Error in CheckRight")

def checkBottom():
    """
    Extracting the required data and checking if the visibility and clue are similar.
    """
    dataEntry = readFile()
    dimension = int(dataEntry[0])
    column = (dataEntry[5:])
    Bottom = (dataEntry[3])
    Bottom = Bottom.replace(" ","")
    # print(Bottom)
    k=0
    for l in range(dimension):
        str1 = ""
        for i in range(dimension):
            column = column[i]
            column = column.replace(" ","")
            for j in range(1):
                str1 = str1 + column[k]
            
            column = (dataEntry[5:])

        highest = 0
        visibility = 0
        for j in range(dimension-1,-1,-1):
            if highest < int(str1[j]):
                highest = int(str1[j])
                visibility+=1
        
        if visibility == int(Bottom[l]):
            break
        else:
            print("Error in Column ",l)        
        k+=1

def printPuzzle():
    """
    Extracting the required data and printing them in the given format
    """
    dataEntry = readFile()
    dimension = int(dataEntry[0])
    Left = dataEntry[4]
    Right = dataEntry[2]
    print("   ",dataEntry[1])
    k=0
    for i in range(dimension):
        row =  dataEntry[i+dimension]
        print(" ")
        print(Left[k],"|",row,"|",Right[k])
        k+=2

    print("")
    print("   ",dataEntry[3])

def puzzle():
    """
    Running the functions in sequence to print and then display if any issues present
    """
    printPuzzle()
    checkRow()
    checkColumn()
    checkClues()

def main():
    """
    Main method to start the run.
    """
    puzzle()

if __name__ == '__main__':
    main()