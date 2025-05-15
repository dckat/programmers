import sys
sys.setrecursionlimit(10 ** 6)


dr = [-1, 0, 1, 0]
dc = [0, 1, 0, -1]


def cleanUp(row, col, dir):  # cd : current direction
    global count

    # 현재 칸이 청소되지 않았다면 현재 칸을 청소한다.
    if room[row][col] == 0:
        room[row][col] = -1
        count += 1

    # 현재 칸 주변 4칸 중 청소되지 않은 빈 칸이 있는지 탐색
    for i in range(4):
        dir = (dir + 3) % 4     # 다음 방향
        nr = row + dr[dir]      # 다음 탐색할 칸의 행
        nc = col + dc[dir]      # 다음 탐색할 칸의 열

        if (0 <= nr < N) and (0 <= nc < M):
            if room[nr][nc] == 0:   # 다음 칸이 청소되지 않았다면
                cleanUp(nr, nc, dir)

    # 현재 칸 주변 4칸을 다 탐색 했는데도 빈 칸이 없다면 후진
    nd = (dir + 2) % 4
    nr = row + dr[nd]
    nc = col + dc[nd]

    if (0 <= nr < N) and (0 <= nc < M):
        if room[nr][nc] != 1:
            cleanUp(nr, nc, dir)    # 바라보는 방향 유지한채로 후진
        else:   # 후진하려는 곳이 벽이면 더는 갈 곳이 없음을 의미
            print(count)
            exit(0)


# N : 행, M : 열
N, M = map(int, sys.stdin.readline().split())
r, c, d = map(int, sys.stdin.readline().split())
room = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]

count = 0
cleanUp(r, c, d)
