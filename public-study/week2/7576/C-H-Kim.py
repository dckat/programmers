import sys
from collections import deque

dr = [-1, 1, 0, 0]
dc = [0, 0, -1, 1]

def bfs():
    while queue:
        r, c, depth = queue.popleft()

        for i in range(4):
            nr = r + dr[i]
            nc = c + dc[i]

            if (0 <= nr < N) and (0 <= nc < M):
                if box[nr][nc] == 0:
                    box[nr][nc] = depth + 1
                    queue.append([nr, nc, depth + 1])


# N: 행, M: 열
M, N = map(int, sys.stdin.readline().split())
box = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]
queue = deque([])

for i in range(N):
    for j in range(M):
        if box[i][j] == 1:
            queue.append([i, j, 1])

bfs()

maxValue = max(map(max, box))

if any(0 in rows for rows in box):
    print(-1)
else:
    print(maxValue - 1)