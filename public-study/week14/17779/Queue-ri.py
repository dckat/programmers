import sys
from math import inf
input = sys.stdin.readline

n = 0
data = []

def calc(x, y, d1, d2):
    dist = [[0 for _ in range(n)] for _ in range(n)]

    # draw borders
    for i in range(d1+1):
        dist[x+i][y-i] = 5
        dist[x+d2+i][y+d2-i] = 5
    for i in range(d2+1):
        dist[x+i][y+i] = 5
        dist[x+d1+i][y-d1+i] = 5

    # district 1
    for r in range(x+d1):
        for c in range(y+1):
            if dist[r][c] == 5:
                break
            dist[r][c] = 1
    
    # district 2
    for r in range(x+d2+1):
        for c in range(n-1, y, -1):
            if dist[r][c] == 5:
                break
            dist[r][c] = 2
    
    # district 3
    for r in range(x+d1, n):
        for c in range(y-d1+d2):
            if dist[r][c] == 5:
                break
            dist[r][c] = 3
    
    # district 4
    for r in range(x+d2+1, n):
        for c in range(n-1, y-d1+d2-1, -1):
            if dist[r][c] == 5:
                break
            dist[r][c] = 4
    
    # district 5
    for r in range(n):
        for c in range(n):
            if dist[r][c] == 0:
                dist[r][c] = 5

    # calculate sum
    cnt = [0] * 5
    for r in range(n):
        for c in range(n):
            cnt[dist[r][c]-1] += data[r][c]

    return max(cnt) - min(cnt)
        
def solution():
    global n, data
    
    n = int(input())
    for _ in range(n):
        data.append(list(map(int, input().split())))
    
    ans = inf
    for x in range(n):
        for y in range(n):
            for d1 in range(1, n):
                for d2 in range(1, n):
                    if x + d1 + d2 >= n:
                        continue
                    if y - d1 < 0 or y + d2 >= n:
                        continue
                    cand = calc(x, y, d1, d2)
                    ans = min(ans, cand)

    print(ans)


if __name__ == '__main__':
    solution()