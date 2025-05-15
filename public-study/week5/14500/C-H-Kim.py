import sys
sys.setrecursionlimit(10 ** 6)

dr = [-1, 1, 0, 0]
dc = [0, 0, -1, 1]

def otherModel(row, col, sum, cnt):
    global maxValue

    if cnt == 4:
        maxValue = max(maxValue, sum)
        return

    for i in range(4):
        nr = row + dr[i]
        nc = col + dc[i]

        if (0 <= nr < N) and (0 <= nc < M):
            if not visited[nr][nc]:
                visited[nr][nc] = True
                otherModel(nr, nc, sum + board[nr][nc], cnt + 1)
                visited[nr][nc] = False


def tModel(row, col):
    global maxValue

    for i in range(4):
        temp = board[row][col]
        for j in range(3):

            idx = (i + j) % 4
            nr = row + dr[idx]
            nc = col + dc[idx]

            if not (0 <= nr < N and 0 <= nc < M):
                temp = 0
                break

            temp += board[nr][nc]

        maxValue = max(maxValue, temp)

# N : 행, M : 열
N, M = map(int, sys.stdin.readline().split())
board = []
for _ in range(N):
    board.append(list(map(int, sys.stdin.readline().split())))

visited = [[False] * M for _ in range(N)]

maxValue = 0

for i in range(N):
    for j in range(M):
        visited[i][j] = True
        otherModel(i, j, board[i][j], 1)
        visited[i][j] = False
        tModel(i, j)

print(maxValue)