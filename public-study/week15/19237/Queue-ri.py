import sys
from copy import deepcopy
input = sys.stdin.readline

n, m, k = 0, 0, 0
field = [] # 상어 맵
smell = [] # 냄새 맵
s_dir = [] # 상어 방향 정보
s_pri = [] # 상어 이동 우선순위 정보
out = 0 # 쫓겨난 상어 카운트
dy = [-1, 1, 0, 0]
dx = [0, 0, -1, 1] # T B L R

def update_smell():
    global smell
    for y in range(n):
        for x in range(n):
            # 냄새 있으면 -1
            if smell[y][x][1] > 0:
                smell[y][x][1] -= 1
            # 상어의 위치면 [상어번호, k]로 갱신
            if field[y][x] != 0:
                smell[y][x] = [field[y][x], k]

def move():
    nfield = deepcopy(field) # 이동 기록용. n제곱 스캔때문에 분리해야 함.
    global s_dir, out
    for y in range(n):
        for x in range(n):
            if field[y][x] == 0: # existence check
                continue
            
            # 상어 있으면
            sdx = field[y][x] - 1 # shark index
            d = s_dir[sdx] # 1 ~ 4
            moved = False
            
            #print('sdx:', sdx, '/ d:', d)
            
            # 냄새 없는 곳 탐색
            # 4방향을 s_pri 우선순위에 따라 처리
            for nd in s_pri[sdx][d-1]: # 1 ~ 4
                ny = y + dy[nd-1]
                nx = x + dx[nd-1]
                #print('nd:', nd, '/ ny:', ny, '/ nx:', nx)
                
                if not (0 <= ny < n and 0 <= nx < n): # bound check
                    continue
                
                # 냄새 없는 경우
                if smell[ny][nx][1] == 0:
                    if nfield[ny][nx] == 0: # 상어도 없으면
                        nfield[ny][nx] = sdx + 1 # 바로 이동
                    else: # 상어 있으면
                        nfield[ny][nx] = min(nfield[ny][nx], sdx + 1) # 더 작은 상어 win
                        out += 1 # 상어 하나 out
                        #print('out')
                    
                    nfield[y][x] = 0 # 이동 전 위치 처리
                    s_dir[sdx] = nd # 이동 후 방향 갱신
                    moved = True
                    break
            
            if moved: # 다음 상어로 넘어가기
                continue
            
            # 아직 이동 못했으면 본인 냄새 탐색
            # 4방향을 s_pri 우선순위에 따라 처리
            for nd in s_pri[sdx][d-1]: # 1 ~ 4
                ny = y + dy[nd-1]
                nx = x + dx[nd-1]
                
                if not (0 <= ny < n and 0 <= nx < n):
                    continue
                
                if smell[ny][nx][0] == sdx + 1: # 자신의 냄새면
                    s_dir[sdx] = nd # 방향 업데이트
                    nfield[ny][nx] = field[y][x] # 이동
                    nfield[y][x] = 0 # 이동 전 위치 처리
                    break
    
    return nfield
                
def solution():
    global n, m, k, field, smell, s_dir, s_pri
    
    n, m, k = map(int, input().split())
    
    for _ in range(n):
        field.append(list(map(int, input().split())))
        smell.append([[0, 0] for _ in range(n)])
    
    s_dir = list(map(int, input().split()))
    
    for _ in range(m):
        lst = []
        for _ in range(4):
            lst.append(list(map(int, input().split())))
        s_pri.append(lst)
        
    # simulate
    for t in range(1, 1001):
        update_smell()
        
        # print(t)
        # for line in smell:
        #     print(line)
        
        field = move()
        
        #print()
        
        if out == m-1:
            print(t)
            return
    
    print(-1)


if __name__ == '__main__':
    solution()