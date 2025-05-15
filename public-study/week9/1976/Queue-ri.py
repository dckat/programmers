import sys
from collections import deque
input = sys.stdin.readline

n, m = 0, 0
adj, plan = [], []
visited = []

def bfs(i):
    q = deque()
    q.append(i)
    visited[i] = True
    
    while q:
        cur = q.pop()
        for nxt in range(n):
            if adj[cur][nxt] and not visited[nxt]:
                q.append(nxt)
                visited[nxt] = True


def solution():
    global n, m, adj, plan, visited
    
    n = int(input())
    m = int(input())
    
    for _ in range(n):
        adj.append(list(map(int, input().split())))
        
    plan = list(map(int, input().split()))
    visited = [False] * n
    
    bfs(plan[0]-1)
    
    for city in plan:
        if visited[city-1] == False:
            print("NO")
            return
        
    print("YES")


if __name__ == '__main__':
    solution()