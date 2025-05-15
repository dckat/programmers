tetro = [[(0,1), (0,2), (0,3)], [(1,0), (2,0), (3,0)], # 1x4
        [(0,1), (1,0), (1,1)], # 2x2
        [(1,0),(1,1),(2,1)], [(0,-1), (1,-1), (1,-2)], # ㄹ(회전)
        [(1,0), (1,-1), (2,-1)], [(0,1), (1,1), (1,2)], # ㄹ(대칭)
        [(1,0), (2,0), (2,1)], [(0,1), (0,2), (1,0)], # ㄴ(회전)
        [(0,1),(1,1), (2,1)], [(0,1), (0,2), (-1,2)],
        [(1,0),(2,0),(2,-1)], [(0,1),(0,2),(1,2)], # ㄴ(대칭)
        [(1,0),(2,0),(0,1)], [(1,0),(1,1),(1,2)],
        [(1,0),(1,1),(1,-1)], [(1,0),(1,1),(2,0)], # ㅗ(회전)
        [(0,-1),(1,0),(0,1)], [(0,1),(-1,1),(1,1)] 
]
table = []
n, m = 0, 0

def calc(i, j, t):
    global table
    
    res = table[i][j] # 시작 위치
    for (di, dj) in t:
        # bound check
        ni = i + di
        nj = j + dj
        if 0 <= ni < n and 0 <= nj < m:
            res += table[ni][nj]
        else:
            return 0
    
    return res

def solution():
    global n, m, table
    n, m = map(int, input().split())
    for i in range(n):
        table.append(list(map(int, input().split())))
    
    ans = 0
    for i in range(n):
        for j in range(m):
            for t in tetro:
                res = calc(i, j, t)
                ans = max(res, ans)
    
    print(ans)


if __name__ == '__main__':
    solution()