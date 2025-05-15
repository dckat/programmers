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


while True:
    # m : 집의 수, n : 길의 수
    m, n = map(int, sys.stdin.readline().split())

    if m == 0 and n == 0:
        break

    parent = [i for i in range(m + 1)]
    height = [1 for _ in range(m + 1)]

    edges = []
    for _ in range(n):
        x, y, z = map(int, sys.stdin.readline().split())

        edges.append([z, [x, y]])

    edges.sort()

    min_cost = 0
    total_cost = 0
    for edge in edges:
        cost, [start, end] = edge
        total_cost += cost

        if find(start) != find(end):
            union(start, end)
            min_cost += cost

    print(total_cost - min_cost)