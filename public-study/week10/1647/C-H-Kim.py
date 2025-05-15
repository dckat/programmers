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


N, M = map(int, sys.stdin.readline().split())
parent = [i for i in range(N + 1)]
height = [1 for _ in range(N + 1)]

edges = []
for _ in range(M):
    A, B, C = map(int, sys.stdin.readline().split())

    edges.append([C, [A, B]])

edges.sort()

result_cost = []
for edge in edges:
    cost, [start, end] = edge

    if find(start) != find(end):
        union(start, end)
        result_cost.append(cost)

result_cost.sort()
print(sum(result_cost[:-1]))