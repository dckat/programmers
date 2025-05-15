import sys
from itertools import combinations
from math import inf
input = sys.stdin.readline

n, m = 0, 0
table = []
home_pos = []
chicken_pos = []

def solution():
    global n, m, table, home_pos, chicken_pos
    n, m = map(int, input().split())
    for i in range(n):
        data = list(map(int, input().split()))
        for j in range(n):
            if data[j] == 1:
                home_pos.append((i+1, j+1))
            elif data[j] == 2:
                chicken_pos.append((i+1, j+1))
        table.append(data)
        
    combs = list(combinations(chicken_pos, m))
    
    ans = inf
    for comb in combs: # 한 치킨집 위치 세트당
        total_mn = 0
        # 집 위치별 최소 치킨거리 계산
        for hpos in home_pos:
            mn = inf
            for cpos in comb:
                d = abs(hpos[0]-cpos[0]) + abs(hpos[1]-cpos[1])
                mn = min(mn, d)
            
            total_mn += mn
        
        #print(comb, total_mn)
        ans = min(ans, total_mn)
    
    print(ans)

if __name__ == '__main__':
    solution()