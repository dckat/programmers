import sys
input = sys.stdin.readline
sys.setrecursionlimit(10**6)

data, visited = [], []
ours, theirs = 0, 0
n, m = 0, 0

def dfs(y, x, team):
    global visited
    
    if data[y][x] != team:
        return 0
    
    visited[y][x] = True
    
    cnt = 1 # self
    if y+1 < m and not visited[y+1][x]: # bottom
        cnt += dfs(y+1, x, team)
    if x+1 < n and not visited[y][x+1]: # right
        cnt += dfs(y, x+1, team)
    if y > 0 and not visited[y-1][x]: # top
        cnt += dfs(y-1, x, team)
    if x > 0 and not visited[y][x-1]: # left
        cnt += dfs(y, x-1, team)
    
    return cnt

def solution():
    global n, m, data, visited, ours, theirs
    n, m = map(int, input().split())
    for _ in range(m):
        visited.append([False] * (n))
        data.append(list(input().rstrip()))
        
    for i in range(m):
        for j in range(n):
            if not visited[i][j]:
                if data[i][j] == 'W':
                    ours += dfs(i, j, 'W') ** 2
                elif data[i][j] == 'B':
                    theirs += dfs(i, j, 'B') ** 2
                
    
    print(ours, theirs)


if __name__ == '__main__':
    solution()