def sum_list(num_list):
    if len(num_list) == 1:
        return num_list[0]
    else:
        return num_list[0] + sum_list(num_list[1:])

def gcd(a,b):
    if b == 0:
        return a
    else:
        return gcd(b,a%b)

def calc(n):
    if n == 0:
        return 1
    elif n == 1:
        return 3
    else:
        result = 2*calc(n-1)+ 5*calc(n-2)
    return result

def sum(n):
    if n==1:
        return 1
    else:
        return n + sum(n-1)

def sumT(n, acc):
    if n == 1:
        return acc
    else:
        return sumT(n-1, acc+n)

def sumI(n):
    acc = 0
    for i in range(1, n+1):
        acc += i
    return acc

def fact(n):
    if n == 0:
        return 1
    else:
        return n * fact(n-1)

def fibo(n):
    if n == 0:
        return 0
    elif n == 1:
        return 1
    else:
        return fibo(n-1) + fibo(n-2)

def sum_of_digits(n):
    if n == 0:
        return 0
    else:
        return int(n%10) + sum_of_digits(n//10)

def pow(n,s):
    if s == 0:
        return 1
    elif s == 1:
        return n
    else:
        return n * pow(n,s-1)

def LCM(a, b):
  t = a % b
  if t == 0:
        return a
  return int(a * LCM(b, t) / t)

def strcount(ch, str1, acc, i):
    if len(str1) == i:
        return acc
    else:
        if str1[i] == ch:
            acc+=1
    i+=1
    return strcount(ch, str1, acc, i)

def call_strcount():
    ch = "s"
    str1 = "sssss"
    result = strcount(ch, str1, 0, 0)
    return result

def main():
    num_list = [1,2,3,4,5]
    print("Sum of lists:",sum_list(num_list))
    print("Greatest common Divisor:",gcd(48,72))
    print("Calculation:",calc(3))
    print("Sum of numbers:",sum(5))
    print("Sum of numbers tail recursion:",sumT(5,1))
    print("Sum of numbers iterative:",sumI(5))
    print("Factorial:",fact(5))
    print("Fibonacci:",fibo(7))
    print("Sum of digits:",sum_of_digits(175))
    print("Power of a number:",pow(2,5))
    print("LCM:",LCM(2,5))
    print("Tail recursion for mid term:",call_strcount())


main()