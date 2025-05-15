import sys
from collections import defaultdict
input = sys.stdin.readline

r, c, t = 0, 0, 0
tdx, bdx = (-1, -1), (-1, -1) # 공기청정기 위/아래 좌표
dy = [0, 1, 0, -1]
dx = [1, 0, -1, 0] # R B L T
room = []
pcnt = 0 # purified dust count

def diffuse():
    global dust
    dvMap = defaultdict(int) # diffuse value map
    
    for y in range(r):
        for x in range(c):
            if room[y][x] > 0:
                diffuseCnt = 0
                #y, x = dpos
                amount = room[y][x] // 5
                for i in range(4):
                    ny = y + dy[i]
                    nx = x + dx[i]
                    if 0 <= ny < r and 0 <= nx < c and ~room[ny][nx]:
                        dvMap[(ny, nx)] += amount
                        diffuseCnt += 1
                        
                room[y][x] = room[y][x] - amount * diffuseCnt
    
    # 확산은 동시에 일어나므로 확산지에 대한 모든 변화값을 합한 후 마지막에 합산
    for (y, x), v in dvMap.items():
        room[y][x] += v


def calc(y, x, d, ymn, ymx, isTop):
    ny = y + dy[d]
    nx = x + dx[d]
    
    # bound check
    if (not ymn <= ny < ymx) or (not 0 <= nx < c):
        d = (d-1 if isTop else d+1) % 4
        ny = y + dy[d]
        nx = x + dx[d]
    
    return (ny, nx, d)

def purify():
    global pcnt
    
    # top
    y, x = tdx
    ymn, ymx = 0, y+1
    d = 0 # direction index
    cur = 0
    
    while True:
        ny, nx, d = calc(y, x, d, ymn, ymx, True)
        nxt = room[ny][nx]
        if nxt == -1:
            pcnt += cur
            break
        else:
            room[ny][nx] = cur
        cur = nxt
        y, x = ny, nx
        
    # bottom
    y, x = bdx
    ymn, ymx = y, r
    d = 0 # direction index
    cur = 0
    
    while True:
        ny, nx, d = calc(y, x, d, ymn, ymx, False)
        nxt = room[ny][nx]
        if nxt == -1:
            pcnt += cur
            break
        else:
            room[ny][nx] = cur
        cur = nxt
        y, x = ny, nx
    

def solution():
    global r, c, tdx, bdx, room
    
    r, c, t = map(int, input().split())
    room = [[-2 for _ in range(c)] for _ in range(r)]
    
    isTop = True
    for i in range(r):
        data = list(map(int, input().split()))
        for j, v in enumerate(data):
            room[i][j] = v
            if v == -1: # 공기청정기면
                if isTop:
                    tdx = (i, j)
                    isTop = False
                else:
                    bdx = (i, j)
    
    # simulate
    total = sum(map(sum, room)) + 2 # 공기청정기 값 제외
    for _ in range(t):
        diffuse()
        purify()
    
    print(total - pcnt)
    

if __name__ == '__main__':
    solution()