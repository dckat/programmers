import sys
sys.setrecursionlimit(10 ** 6)

dr = [-1, 1, 0, 0]
dc = [0, 0, -1, 1]

def dfs(r, c):
    # 목표 지점에 도달했다면 경로 하나 추가
    if r == M - 1 and c == N - 1:
        return 1

    if dp[r][c] != -1:
        return dp[r][c]

    dp[r][c] = 0
    for i in range(4):
        nr = r + dr[i]
        nc = c + dc[i]

        # index 범위 체크 및 이동하려는 곳이 더 낮은 곳인지 체크
        if (0 <= nr < M) and (0 <= nc < N):
            if map[r][c] > map[nr][nc]:
                dp[r][c] += dfs(nr, nc)

    return dp[r][c]


M, N = map(int, sys.stdin.readline().split())
map = [list(map(int, sys.stdin.readline().split())) for _ in range(M)]
dp = [[-1] * N for _ in range(M)]

print(dfs(0, 0))