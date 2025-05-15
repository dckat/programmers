from collections import deque
import sys
input = sys.stdin.readline

l = 0
endX, endY = 0, 0
dx = [-1, 1, -2, 2, -2, 2, -1, 1]
dy = [-2, -2, -1, -1, 1, 1, 2, 2]
board = [] # visited

def bfs(sx, sy):
    global board
    q = deque()
    q.append((sx, sy))
    board[sx][sy] = 0
    
    while q:
        x, y = q.popleft()
        if x == endX and y == endY:
            return
        for i in range(8):
            nx = x + dx[i]
            ny = y + dy[i]
            if 0 <= nx < l and 0 <= ny < l and board[nx][ny] == -1:
                board[nx][ny] = board[x][y] + 1
                q.append((nx, ny))


def solution():
    global l, endX, endY, board
    l = int(input())
    startX, startY = map(int, input().split())
    endX, endY = map(int, input().split())
    board = [[-1 for _ in range(l)] for _ in range(l)]
    
    bfs(startX, startY)
    print(board[endX][endY])
    
    
if __name__ == '__main__':
    tc = int(input())
    for _ in range(tc):
        solution()