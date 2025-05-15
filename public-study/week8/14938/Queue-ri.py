import sys
from math import inf
import heapq
input = sys.stdin.readline

n, m, r = 0, 0, 0
item, graph = [], []

def dijkstra(idx):
    distance = [inf] * (n+1)
    
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
                heapq.heappush(pq, (cand, v))
                distance[v] = cand
    
    res = 0
    for i, d in enumerate(distance):
        if d <= m:
            res += item[i-1]
    
    return res

def solution():
    global n, m, r, item, graph
    
    n, m, r = map(int, input().split())
    item = list(map(int, input().split()))
    graph = [[] for _ in range(n+1)]
    
    for _ in range(r):
        v1, v2, w = map(int,input().split())
        graph[v1].append((v2, w))
        graph[v2].append((v1, w))
    
    ans = 0
    for idx in range(1, n+1):
        ans = max(ans, dijkstra(idx))
    
    print(ans)


if __name__ == '__main__':
    solution()