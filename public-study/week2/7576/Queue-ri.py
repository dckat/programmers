import sys
from collections import deque
input = sys.stdin.readline

tomato = []
m, n = 0, 0
total = 0
dy = [-1, 0, 0, 1]
dx = [0, -1, 1, 0]

def bfs(q):
    global tomato
    cnt = 0 # 익힌 토마토 개수
    ans = 0
    while q:
        y, x, day = q.popleft()
        ans = max(ans, day)
        for i in range(4):
            ny = y + dy[i]
            nx = x + dx[i]
            if 0 <= ny < n and 0 <= nx < m and tomato[ny][nx] == 0:
                q.append((ny, nx, day+1))
                tomato[ny][nx] = 1 # visited
                cnt += 1 # 익힌 개수 증가
                
    if total == cnt:
        print(ans)
    else:
        print(-1)
    

def solution():
    global tomato, m, n, total
    m, n = map(int, input().split())
    q = deque()
    
    for i in range(n):
        data = list(map(int, input().split()))
        for j in range(m):
            if data[j] == 0:
                total += 1 # 익혀야 할 토마토 개수
            elif data[j] == 1:
                q.append((i, j, 0))
        tomato.append(data)
        
    bfs(q)


if __name__ == '__main__':
    solution()