import sys
import heapq


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
heap = []

for _ in range(M):
    A, B, C = map(int, sys.stdin.readline().split())
    heapq.heappush(heap, [-C, A, B])    # 중량이 큰 것부터 체크하기 위해 최대힙으로 구현

start, end = map(int, sys.stdin.readline().split())

while heap:
    C, A, B = heapq.heappop(heap)
    C = -C

    union(A, B)

    if find(start) == find(end):
        print(C)
        break