# PyPy3	통과 코드
import sys
from math import inf
input = sys.stdin.readline

n, m = 0, 0
prefix_sum = []

def solution():
    global n, m, prefix_sum
    
    n, m = map(int, input().split())
    prefix_sum = [[0 for _ in range(m+1)] for _ in range(n+1)]
    
    # calculate prefix sum
    for i in range(n):
        data = list(map(int, input().split()))
        for j, k in enumerate(data):
            prefix_sum[i+1][j+1] = prefix_sum[i][j+1] + prefix_sum[i+1][j] - prefix_sum[i][j] + k
            
    # brute force
    mx = -inf
    for y1 in range(1, n+1):
        for x1 in range(1, m+1):
            for y2 in range(y1, n+1):
                for x2 in range(x1, m+1):
                    res = prefix_sum[y2][x2] - prefix_sum[y1-1][x2] - prefix_sum[y2][x1-1] + prefix_sum[y1-1][x1-1]
                    mx = max(mx, res)
    
    print(mx)


if __name__ == '__main__':
    solution()