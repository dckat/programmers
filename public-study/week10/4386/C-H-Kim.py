import math
import sys


def find(num):
    if num == parent[num]:
        return num

    parent[num] = find(parent[num])

    return parent[num]


def union(x, y):
    x = find(x)
    y = find(y)

    if height[x] < height[y]:
        x, y = y, x

    parent[y] = x

    if height[x] == height[y]:
        height[x] += 1


n = int(sys.stdin.readline())
parent = [i for i in range(n + 1)]
height = [1 for _ in range(n + 1)]

stars = []
for _ in range(n):
    x, y = map(float, sys.stdin.readline().split())
    stars.append([x, y])

edges = []
for i in range(len(stars)):
    for j in range(i + 1, len(stars)):
        distance = math.sqrt((stars[j][0] - stars[i][0]) ** 2 + (stars[j][1] - stars[i][1]) ** 2)
        edges.append([distance, [i, j]])

edges.sort()

result = 0
for edge in edges:
    cost, [start, end] = edge

    if find(start) != find(end):
        union(start, end)
        result += cost

print(round(result, 2))