import sys

N = int(sys.stdin.readline())

infoArr = []
for _ in range(N):
    info = list(map(int, sys.stdin.readline().strip().split()))
    infoArr.append(info)

infoArr.sort(key=lambda x: (x[1], x[0]))

answer = 0
currentEnd = 0
for start, end in infoArr:
    if currentEnd <= start:
        answer += 1
        currentEnd = end

print(answer)