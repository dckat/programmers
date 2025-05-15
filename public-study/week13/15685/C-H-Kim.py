import sys

dx = [0, -1, 0, 1]
dy = [1, 0, -1, 0]
coordinate = [[False] * 101 for _ in range(101)]

N = int(sys.stdin.readline())


def isDragonCurve(r, c):
    if coordinate[r][c] and coordinate[r + 1][c] and coordinate[r][c + 1] and coordinate[r + 1][c + 1]:
        return True

    return False


for _ in range(N):
    y, x, d, g = map(int, sys.stdin.readline().split())
    # 방문 처리
    coordinate[x][y] = True

    # 방향 설정
    direction = [d]
    for _ in range(g):
        # 이전 세대들의 방향을 역으로 참조하기 때문에 1씩 감소하면서 방향을 추가
        for i in range(len(direction) - 1, -1, -1):
            # 이전 세대 선의 방향에서 하나씩 증가
            direction.append((direction[i] + 1) % 4)

    # 드래곤 커브 만들기
    for i in range(len(direction)):
        # 이동할 좌표
        x += dx[direction[i]]
        y += dy[direction[i]]

        # 이동할 수 있는 좌표인지 검증
        if (x < 0 or x > 100) or (y < 0 or y > 100):
            continue

        # 이동할 수 있다면 방문 처리
        coordinate[x][y] = True

answer = 0
# 101이 아닌 이유 : +1, +1 좌표까지 탐색하기 때문
for i in range(100):
    for j in range(100):
        if isDragonCurve(i, j):
            answer += 1

print(answer)