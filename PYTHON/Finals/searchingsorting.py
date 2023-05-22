def linear_search(data, val):
    for i in range(len(data)):
        if val == data[i]:
            return True
    return False

def __binarySearch(data, val, left, right):
    if left > right:
        return -1
    mid = (left + right)//2
    if data[mid] == val:
        return mid
    if data[mid] > val:
        return __binarySearch(data, val, left, mid-1)
    else:
        return __binarySearch(data, val, mid+1, right)

def binarySearch(data, val):
    return __binarySearch(data, val, 0, len(data)-1)

def _findMidIndex(data, mark):
    minIndex = mark
    for mark in range(mark+1,len(data)):
        if data[mark] < data[minIndex]:
            minIndex = mark
    return minIndex

def selectionSort(data):
    for mark in range(len(data)-1):
        midIndex = _findMidIndex(data, mark)
        data[midIndex], data[mark] = data[mark], data[midIndex]
    return data

def msort(data):
    if (len(data) == 1):
        return data
    mid = len(data) // 2
    left = msort(data[0:mid])
    right = msort(data[mid:len(data)])
    return _merge(left,right)

def _merge(left, right):
    result = []
    leftIndex, rightIndex = 0, 0
    
    while (leftIndex < len(left) and (rightIndex < len(right))):
        if left[leftIndex] <= right[rightIndex]:
            result.append(left[leftIndex])
            leftIndex += 1
        else:
            result.append(right[rightIndex])
            rightIndex += 1

    if leftIndex < len(left):
        result.extend(left[leftIndex:])
    elif rightIndex < len(right):
        result.extend(right[rightIndex:])
    return result


def main():
    data = [5,1,7,2,8,50,6]
    val = 6
    print("======================================")
    print("Linear Search:")
    print(linear_search(data,val))
    data1 = [1,2,3,6,8,15,20]
    print("======================================")
    print("Binary Search:")
    print(binarySearch(data1,val))
    print("======================================")
    print("Selection Sort:")
    print("Unsorted Array",data)
    print("Sorted Array",selectionSort(data))
    print("======================================")
    print("Merge Sort:")
    print("Unsorted Array",data)
    print("Sorted Array",msort(data))
    print("======================================")

if __name__ == '__main__':
    main()
