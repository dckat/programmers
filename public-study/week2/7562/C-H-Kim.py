import sys
from collections import deque

dr = [-2, -1, 1, 2, 2, 1, -1, -2]
dc = [1, 2, 2, 1, -1, -2, -2, -1]


def bfs(row, col):
    queue = deque([[row, col, 0]])
    visited[row][col] = True

    while queue:
        r, c, depth = queue.popleft()

        if (r == destX) and (c == destY):
            return depth

        for i in range(8):
            nr = r + dr[i]
            nc = c + dc[i]

            if (0 <= nr < I) and (0 <= nc < I):
                if not visited[nr][nc]:
                    visited[nr][nc] = True
                    queue.append([nr, nc, depth + 1])


T = int(sys.stdin.readline().strip())

for _ in range(T):
    I = int(sys.stdin.readline().strip())

    startX, startY = map(int, sys.stdin.readline().split())
    destX, destY = map(int, sys.stdin.readline().split())

    # 시작 좌표와 목표 좌표가 같다면 연산 X
    if (startX == destX) and (startY == destY):
        print(0)
        continue

    visited = [[False] * I for _ in range(I)]

    print(bfs(startX, startY))
