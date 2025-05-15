import sys
input = sys.stdin.readline

n, k = 0, 0
board = []
horse_data = []
horse_board = []
dy = [0, 0, -1, 1] # R L T B
dx = [1, -1, 0, 0]

def move(i):
    global horse_data, horse_board
    
    y, x, d = horse_data[i]
    ny = y + dy[d]
    nx = x + dx[d]
    
    # 1. bound / blue check
    if not (0 <= ny < n and 0 <= nx < n) or board[ny][nx] == 2:
        # change direction
        d = d-1 if d & 1 else d+1
        horse_data[i][2] = d
        
        # recheck
        ny = y + dy[d]
        nx = x + dx[d]
        if not (0 <= ny < n and 0 <= nx < n) or board[ny][nx] == 2:
            return False
            
    # get idx of target horse from horse stack
    idx = horse_board[y][x].index(i)
    movable = horse_board[y][x][idx:]
    horse_board[y][x] = horse_board[y][x][:idx]
    
    # 2. white check
    if board[ny][nx] == 0:
        horse_board[ny][nx].extend(movable)
    # 3. red check
    elif board[ny][nx] == 1:
        horse_board[ny][nx].extend(movable[::-1])
        
    # update moved horse data
    for hdx in movable:
        horse_data[hdx][0] = ny
        horse_data[hdx][1] = nx
        
    # end if stacked size gte 4
    if len(horse_board[ny][nx]) >= 4:
        return True
    
    return False

def solution():
    global n, k, board, horse_data, horse_board
    
    n, k = map(int, input().split())
    board = [list(map(int, input().split())) for _ in range(n)]
    horse_board = [[[] for _ in range(n)] for _ in range(n)]
    for i in range(k):
        y, x, d = map(int, input().split())
        horse_board[y-1][x-1].append(i)
        horse_data.append([y-1, x-1, d-1])
        
    # simulate
    for turn in range(1, 1001):
        for i in range(k):
            if move(i):
                print(turn)
                return
        
    print(-1)
    
    
if __name__ == '__main__':
    solution()