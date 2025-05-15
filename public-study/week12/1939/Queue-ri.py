import sys
from collections import deque
input = sys.stdin.readline

n, m = 0, 0
start, end = 0, 0
graph = []
low, high = 0, 0 # binary search

def bfs(visited, mid):
    q = deque()
    q.append(start)
    while q:
        v = q.popleft()
        if v == end: # end 도착 -> 경로 있음!
            return True
        
        for (b, c) in graph[v]: # end 아니면 인접한 곳으로 이동
            # 중량 제한 안걸리고 방문한 적 없으면
            if mid <= c and visited[b] == False:
                visited[b] = True
                q.append(b) # 이동
    
    return False # 경로 없음


def bsearch():
    global low, high
    
    while (low <= high):
        # 최대중량후보를 mid로 설정하고,
        # bfs로 start -> end까지의 경로가 있는지 확인
        visited = [False for _ in range(n+1)]
        mid = (low + high) // 2
        exist = bfs(visited, mid)
        if exist:
            low = mid + 1
        else:
            high = mid - 1
    
    return high


def solution():
    global n, m, start, end, graph, high
    
    n, m = map(int, input().split())
    graph = [[] for _ in range(n+1)]
    
    for _ in range(m):
        a, b, c = map(int, input().split())
        graph[a].append((b, c))
        graph[b].append((a, c))
        high = max(high, c) # 다리 중 최대 중량
        
    start, end = map(int, input().split()) # 공장 위치
    
    print(bsearch())


if __name__ == '__main__':
    solution()