import sys
from collections import deque
input = sys.stdin.readline
sys.setrecursionlimit(10**6)

n = 0
region = 2
world = []
d = [(-1, 0), (0, -1), (0, 1), (1, 0)]

def mark_land(i, j): # dfs but named
    global region, world
    
    # base case
    if i < 0 or n <= i or j < 0 or n <= j:
        return False
    
    # recursive
    if world[i][j] == 1:
        world[i][j] = region
        for (y, x) in d:
            ny = y + i
            nx = x + j
            mark_land(ny, nx)
        return True

def bfs(k):
    count = [[-1 for _ in range(n)] for _ in range(n)]
    q = deque()
    
    for i in range(n):
        for j in range(n):
            if world[i][j] == k:
                q.append((i, j))
                count[i][j] = 0
    
    ans = 999
    while q:
        y, x = q.popleft()
        
        for (i, j) in d:
            ny = y + i
            nx = x + j
            # bound check
            if ny < 0 or n <= ny or nx < 0 or n <= nx:
                continue
            # 다른 섬에 닿은 경우
            if 0 < world[ny][nx] and world[ny][nx] != k:
                ans = min(ans, count[y][x])
                break
            # 바다이면서 방문 이력 없음
            if world[ny][nx] == 0 and count[ny][nx] == -1:
                count[ny][nx] = count[y][x] + 1
                q.append((ny, nx))
    
    return ans 

def solution():
    global n, region, world
    n = int(input())
    for _ in range(n):
        world.append(list(map(int, input().split())))
    
    # calculate
    for i in range(n):
        for j in range(n):
            if mark_land(i, j) == True:
                region += 1
    
    ans = 999
    for i in range(2, region):
        ans = min(ans, bfs(i))
        
    print(ans)


if __name__ == '__main__':
    solution()