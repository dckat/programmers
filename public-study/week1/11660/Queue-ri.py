import sys
input = sys.stdin.readline

table = []
presum = []
n, m = 0, 0

def calc_presum():
    global table, presum
    for i in range(1, n+1):
        isum = 0
        for j in range(1, n+1):
            isum += table[i][j]
            presum[i][j] = presum[i-1][j] + isum

def solution():
    x1, y1, x2, y2 = map(int, input().split())
    ans = presum[x2][y2] - presum[x2][y1-1] - presum[x1-1][y2] + presum[x1-1][y1-1]
    print(ans)
    

if __name__ == '__main__':
    n, m = map(int, input().split())
    table.append([0] * (n+1))
    
    for y in range(1, n+1):
        table.append([0])
        table[y].extend(list(map(int, input().split())))
    
    presum = [[0 for _ in range(n+1)] for __ in range(n+1)]
    calc_presum()
    
    for tc in range(m):
        solution()
