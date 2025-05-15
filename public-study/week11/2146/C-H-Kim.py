import sys
from collections import deque

dr = [-1, 0, 0, 1]
dc = [0, -1, 1, 0]

# 섬마다 번호를 부여하는 역할
def numbering(row, col):
    queue = deque([[row, col]])
    visited[row][col] = True
    chart[row][col] = island_number

    while queue:
        cr, cc = queue.popleft()

        for i in range(4):
            nr = cr + dr[i]
            nc = cc + dc[i]

            if (0 <= nr < N) and (0 <= nc < N):
                if not visited[nr][nc] and chart[nr][nc] != 0:
                    visited[nr][nc] = True
                    chart[nr][nc] = island_number
                    queue.append([nr, nc])


def makeBridge(number):
    queue = deque()
    distance = [[-1] * N for _ in range(N)]

    for i in range(N):
        for j in range(N):
            if chart[i][j] == number:
                distance[i][j] = 0
                queue.append([i, j])

    while queue:
        row, col = queue.popleft()

        for i in range(4):
            nr = row + dr[i]
            nc = col + dc[i]

            if (0 <= nr < N) and (0 <= nc < N):
                # 다른 섬을 만났을 경우
                if chart[nr][nc] != 0 and chart[nr][nc] != number:
                    return distance[row][col]
                # 물을 만났고 아직 다리가 없는 경우
                elif chart[nr][nc] == 0 and distance[nr][nc] == -1:
                    distance[nr][nc] = distance[row][col] + 1
                    queue.append([nr, nc])

    return int(1e9)


N = int(sys.stdin.readline())
chart = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]

island_number = 1
visited = [[False] * N for _ in range(N)]
for i in range(N):
    for j in range(N):
        if chart[i][j] == 1 and not visited[i][j]:
            numbering(i, j)
            island_number += 1

result = int(1e9)
for num in range(1, island_number):
    result = min(result, makeBridge(num))

print(result)