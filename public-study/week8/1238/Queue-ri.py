import sys
from math import inf
import heapq
input = sys.stdin.readline

n, m, x = 0, 0, 0
graph = []

def dijkstra(start, end):
    pq = []
    distance = [inf] * (n+1)
    heapq.heappush(pq, (0, start))
    distance[start] = 0
    
    while pq:
        d, cur = heapq.heappop(pq)
        if distance[cur] < d:
            continue
        
        for v, w in graph[cur]:
            cand = d + w
            if cand < distance[v]:
                heapq.heappush(pq, (cand, v))
                distance[v] = cand

    return distance[end]

def solution():
    global n, m, x, graph
    
    n, m, x = map(int, input().split())
    graph = [[] for _ in range(n+1)]
    
    for _ in range(m):
        v1, v2, w = map(int, input().split())
        graph[v1].append((v2, w)) # 단방향
        
    ans = 0
    for i in range(1, n+1):
        if i == x:
            continue
        ans = max(ans, dijkstra(i,x) + dijkstra(x,i))
    
    print(ans)


if __name__ == '__main__':
    solution()