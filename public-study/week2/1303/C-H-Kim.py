import sys
from collections import deque
sys.setrecursionlimit(10 ** 6)

# M: 행, N: 열
N, M = map(int, sys.stdin.readline().split())
battleGround = [list(sys.stdin.readline().strip()) for _ in range(M)]
visited = [[False for _ in range(N)] for _ in range(M)]
whiteSum = 0
blueSum = 0

dr = [-1, 1, 0, 0]
dc = [0, 0, -1, 1]

def dfs(row, col, team):
    global whiteSubSum, blueSubSum
    visited[row][col] = True

    for i in range(4):
        nr = row + dr[i]
        nc = col + dc[i]

        if (0 <= nr < M) and (0 <= nc < N):
            if not visited[nr][nc] and battleGround[nr][nc] == team:
                dfs(nr, nc, team)
                if team == "W":
                    whiteSubSum += 1
                elif team == "B":
                    blueSubSum += 1


def bfs(row, col, team):
    global whiteSubSum, blueSubSum
    queue = deque([[row, col]])
    visited[row][col] = True

    while queue:
        r, c = queue.popleft()

        for i in range(4):
            nr = r + dr[i]
            nc = c + dc[i]

            if (0 <= nr < M) and (0 <= nc < N):
                if not visited[nr][nc] and battleGround[nr][nc] == team:
                    visited[nr][nc] = True
                    queue.append([nr, nc])
                    if team == "W":
                        whiteSubSum += 1
                    elif team == "B":
                        blueSubSum += 1


for i in range(M):
    for j in range(N):
        if battleGround[i][j] == "W" and not visited[i][j]:
            whiteSubSum = 1
            dfs(i, j, "W")
            # bfs(i, j, "W")
            whiteSum += (whiteSubSum ** 2)
        elif battleGround[i][j] == "B" and not visited[i][j]:
            blueSubSum = 1
            dfs(i, j, "B")
            # bfs(i, j, "B")
            blueSum += (blueSubSum ** 2)


print(whiteSum, blueSum)
