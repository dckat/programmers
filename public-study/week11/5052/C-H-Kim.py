import sys

t = int(sys.stdin.readline())
numbers = []

for _ in range(t):
    n = int(sys.stdin.readline())

    for _ in range(n):
        numbers.append(sys.stdin.readline().rstrip())

    numbers.sort()

    flag = True
    for i in range(len(numbers) - 1):
        if numbers[i] in numbers[i + 1][0:len(numbers[i])]:
            flag = False
            break

    if flag:
        print("YES")
    else:
        print("NO")

    numbers.clear()