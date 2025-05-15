import sys
from math import inf
import heapq
input = sys.stdin.readline

n, m = 0, 0
graph, distance = [], []

def dijkstra(idx):
    global distance
    
    pq = []
    heapq.heappush(pq, (0, idx))
    distance[idx] = 0
    
    while pq:
        d, cur = heapq.heappop(pq)
        if distance[cur] < d:
            continue
        for v, w in graph[cur]:
            cand = d + w
            if cand < distance[v]:
                distance[v] = cand
                heapq.heappush(pq, (cand, v))

def solution():
    global n, m, graph, distance
    n, m = map(int, input().split())
    graph = [[] for _ in range(n+1)]
    distance = [inf] * (n+1)
    
    for _ in range(m):
        v1, v2, w = map(int, input().split())
        graph[v1].append((v2, w))
        graph[v2].append((v1, w))
        
    dijkstra(1)
    print(distance[n])


if __name__ == '__main__':
    solution()