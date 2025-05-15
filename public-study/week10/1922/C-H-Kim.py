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


N = int(sys.stdin.readline())
M = int(sys.stdin.readline())
parent = [i for i in range(N + 1)]
height = [1 for _ in range(N + 1)]

edges = []
for _ in range(M):
    a, b, c = map(int, sys.stdin.readline().split())

    edges.append([c, [a, b]])

edges.sort()

result = 0
for edge in edges:
    cost, [start, end] = edge

    if find(start) != find(end):
        union(start, end)
        result += cost

print(result)