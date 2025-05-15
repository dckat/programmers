import sys
import heapq as hq

# Prim MST
# def prim(start):
#     result = 0
#     visited = [False] * (V + 1)
#     priority_queue = [[0, start]]   # [가중치, 노드]
#
#     while priority_queue:
#         weight, node = hq.heappop(priority_queue)
#
#         if not visited[node]:
#             visited[node] = True
#             result += weight
#
#             for adjacent_node, cost in graph[node]:
#                 if not visited[adjacent_node]:
#                     hq.heappush(priority_queue, [cost, adjacent_node])
#
#     return result


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


V, E = map(int, sys.stdin.readline().split())
parent = [i for i in range(V + 1)]
height = [1 for _ in range(V + 1)]

# Prim MST
# graph = [[] for _ in range(V + 1)]
# for _ in range(E):
#     A, B, C = map(int, sys.stdin.readline().split())
#
#     graph[A].append([B, C])
#     graph[B].append([A, C])
#
# start_node = 1
# print(prim(start_node))

edges = []
for _ in range(E):
    # C : 가중치(cost)
    A, B, C = map(int, sys.stdin.readline().split())

    edges.append([C, [A, B]])

edges.sort()

result = 0
for edge in edges:
    cost, [start, end] = edge

    if find(start) != find(end):
        union(start, end)
        result += cost

print(result)