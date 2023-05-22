import sys
import searchAlgos
from graph import Graph

def file():
    """
    Reading text file and using List to store the information 
    and returning it so that other functions can make use for computation
    """
    data = []
    if len(sys.argv) == 0:
        print("Usage:")
    else:
        try:
            with open(sys.argv[1]) as f:
                for line in f:
                    line = line.strip("\n").split(" ")
                    if(line == ""):
                        break
                    data.append(line)
            print("Welcome to Ice Maze!")
            print(sys.argv[1]+" loaded and parsed!")          
            exacm = parse(data)
            return data
        except FileNotFoundError as e:
            print("File not found: "+sys.argv[1])

def parse(data):
    """
    Using the read file in the list to push and create 
    relevant objects for computation
    """
    param = data[0]
    height = int(param[0])
    data.pop(0)
    maze = []
    for i in range(height):
        maze.append(data[i])
    path(maze, param)
    return maze, param

def create_graph(maze, param):
    """
    Creating the connections Row wise 
    using maze and param as the lists
    """
    graph = Graph()

    height = int(param[0])
    width = int(param[1])
    exit = int(param[2])
    for i in range(height):
        if "*" in maze[i]:
            rock = maze[i].index("*")
            for j in range(width):
                if maze[i][j] == "*":
                    continue
                else:
                    if j<rock:
                        graph.addEdge(str(i)+str(j),str(i)+str(rock - 1))
                        graph.addEdge(str(i)+str(j),str(i)+str(0))
                    elif j>rock:
                        if i == exit:
                            graph.addEdge(str(i)+str(j),"exit")
                            graph.addEdge(str(i)+str(j),str(i)+str(rock+1))
                        else:
                            graph.addEdge(str(i)+str(j),str(i)+str(width-1))
                            graph.addEdge(str(i)+str(j),str(i)+str(rock + 1))
        else: 
            for j in range(width):
                if i == exit:
                    graph.addEdge(str(i)+str(j),"exit")
                    graph.addEdge(str(i)+str(j),str(i)+str(0))
                else:
                    graph.addEdge(str(i)+str(j),str(i)+str(width-1))
                    graph.addEdge(str(i)+str(j),str(i)+str(0))
    return graph

def create_column(maze,param):
    """
    Creating the connections Column wise
    using maze and param as the lists
    """
    graph = create_graph(maze,param)
    maze = column(maze,param)
    height = int(param[0])
    width = int(param[1])
    exit = int(param[2])
    for i in range(width):
        if "*" in maze[i]:
            rock = maze[i].index("*")
            for j in range(height):
                if maze[i][j] == "*":
                    continue
                else:
                    if j<rock:
                        graph.addEdge(str(j)+str(i),str(rock-1)+str(i))
                        graph.addEdge(str(j)+str(i),str(0)+str(i))
                    elif j>rock:
                        graph.addEdge(str(j)+str(i),str(height-1)+str(i))
                        graph.addEdge(str(j)+str(i),str(rock+1)+str(i))
        else: 
            for j in range(height):
                graph.addEdge(str(j)+str(i),str(height-1)+str(i))
                graph.addEdge(str(j)+str(i),str(i)+str(0))
    return graph

def path(maze,param):
    """
    Checking for all the possible paths to the exit and printing it using dictionary 
    using maze and param as the lists
    """
    graph = create_column(maze,param)
    height = int(param[0])
    width = int(param[1])
    dic = {"None":[], "1":[], "2":[], "3":[], "4":[], "5":[], "6":[]}
    for i in range(height):
        for j in range(width):
            startVertex = graph.getVertex(str(i)+str(j))
            endVertex = graph.getVertex("exit")
            if startVertex != None:
                path = searchAlgos.findShortestPath(startVertex, endVertex)
                if path == None:
                    dic["None"].append("("+str(j)+str(i)+")")
                elif path != None:
                    if len(path) == 2:
                        dic["1"].append("("+str(j)+str(i)+")")
                    elif len(path) == 3:
                        dic["2"].append("("+str(j)+str(i)+")")
                    elif len(path) == 4:
                        dic["3"].append("("+str(j)+str(i)+")")
                    elif len(path) == 5:
                        dic["4"].append("("+str(j)+str(i)+")")
                    elif len(path) == 6:
                        dic["5"].append("("+str(j)+str(i)+")")
                    elif len(path) == 7:
                        dic["6"].append("("+str(j)+str(i)+")")
    for key, value in dic.items():
        print(key, ' : ', value)


def column(maze,param):
    """
    Creating the columns to help making the graph
    using maze and param as the lists
    """
    height = int(param[0])
    width = int(param[1])
    column = []
    for i in range(width):
        temp_column = []
        for j in range(height):
            temp_column.append(maze[j][i]) 
        column.append(temp_column)
    return column

def main():
    """
    Main function to run the code
    """
    file()


if __name__ == '__main__':
    main()
