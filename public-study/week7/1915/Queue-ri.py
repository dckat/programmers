import sys
input = sys.stdin.readline
sys.setrecursionlimit(10**6)

n, m = 0, 0
table, memo = [], []
def dp(i, j):
    global memo
    
    if table[i][j] == 0:
        return 0
        
    if ~memo[i][j]:
        return memo[i][j]
    
    a, b, c = 0, 0, 0
    
    if j+1 < m:
        a = dp(i, j+1)
    if i+1 < n:
        b = dp(i+1, j)
    if i+1 < n and j+1 < m:
        c = dp(i+1, j+1)
    
    res = min(a, b, c) + 1
    
    memo[i][j] = res
    return res
    

def solution():
    global n, m, table, memo
    
    n, m = map(int, input().split())
    for _ in range(n):
        table.append([int(x) for x in list(input().rstrip())])
        
    memo = [[-1 for _ in range(m)] for _ in range(n)]
    
    ans = 0
    for i in range(n):
        for j in range(m):
            if table[i][j] == 1:
                ans = max(ans, dp(i, j))
                
    print(ans*ans)

if __name__ == '__main__':
    solution()