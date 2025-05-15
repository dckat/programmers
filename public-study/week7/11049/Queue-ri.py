# PyPy3로 채점 필요
import sys
input = sys.stdin.readline

def solution():
    n = int(input())
    p = []
    y, x = map(int, input().split())
    p.append(y)
    p.append(x)
    for i in range(n-1):
        y, x = map(int, input().split())
        p.append(x)
    
    dp = [[0 for _ in range(n+1)] for _ in range(n+1)]
    
    # init: i==j 인 경우 곱셈횟수는 0
    # 근데 이미 0으로 초기화 했어서 생략함
    # for i in range(n+1):
    #     dp[i][i] = 0
        
    # bottom up
    # A0 ~ An-1 --> p[i-1]에서 logic error
    for j in range(1, n+1):
        for i in range(j-1, 0, -1):
            res = 2**31
            for k in range(i, j):
                res = min(res, dp[i][k] + dp[k+1][j] + (p[i-1] * p[k] * p[j]))
            
            dp[i][j] = res
    
    #print(dp)
    print(dp[1][n])
    

if __name__ == '__main__':
    solution()