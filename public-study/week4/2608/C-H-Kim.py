import sys

one = {'I': 1, 'V': 5, 'X': 10, 'L': 50, 'C': 100, 'D': 500, 'M': 1000}
two = {'IV': 4, 'IX': 9, 'XL': 40, 'XC': 90, 'CD': 400, 'CM': 900}
numStr = {1000: 'M', 900: 'CM', 500: 'D', 400: 'CD', 100: 'C', 90: 'XC', 50: 'L', 40: 'XL', 10: 'X', 9: 'IX', 5: 'V', 4: 'IV', 1: 'I'}


def roma2num(s):
    num = 0
    l = len(s)
    visited = [False] * l

    for i in range(l):
        if not visited[i]:
            if i + 1 < l and s[i:i+2] in two:
                visited[i], visited[i+1] = True, True
                num += two[s[i:i+2]]
            else:
                visited[i] = True
                num += one[s[i]]
    return num


def num2roma(n):
    string = ""
    for key, value in numStr.items():
        while n >= key:
            string += value
            n -= key
    return string


str1 = sys.stdin.readline().strip()
str2 = sys.stdin.readline().strip()

result = roma2num(str1) + roma2num(str2)
answer = num2roma(result)

print(result)
print(answer)
