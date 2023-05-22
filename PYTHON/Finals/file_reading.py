def readfile(filename):
    try:
        with open(filename) as f:
            for line in f:
                # line = f.readline()
                print(line.readline())
    except FileNotFoundError as e:
            print("File not found: ")

def main():
    readfile("test.txt")

main()