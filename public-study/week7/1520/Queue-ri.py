# ACed at: 17:21
import sys
input = sys.stdin.readline
sys.setrecursionlimit(10**6)

y, x = 0, 0
table, memo, visited = [], [], []

def dp(i, j):
    if i == y-1 and j == x-1:
        return 1
        
    if ~memo[i][j]:
        return memo[i][j]
    
    res = 0
    cur_val = table[i][j]
    visited[i][j] = True
    
    if j+1 < x and cur_val > table[i][j+1] and not visited[i][j+1]:
        res += dp(i, j+1)
    if i+1 < y and cur_val > table[i+1][j] and not visited[i+1][j]:
        res += dp(i+1, j)
    if i-1 > -1 and cur_val > table[i-1][j] and not visited[i-1][j]:
        res += dp(i-1, j)
    if j-1 > -1 and cur_val > table[i][j-1] and not visited[i][j-1]:
        res += dp(i, j-1)
    
    visited[i][j] = False
    
    memo[i][j] = res
    return res
    

def solution():
    global y, x, table, memo, visited
    y, x = map(int, input().split())
    for _ in range(y):
        table.append(list(map(int, input().split())))
    
    memo = [[-1 for _ in range(x)] for _ in range(y)]
    visited = [[False for _ in range(x)] for _ in range(y)]
    
    print(dp(0, 0))


if __name__ == '__main__':
    solution()