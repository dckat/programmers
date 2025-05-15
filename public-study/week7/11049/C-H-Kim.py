import sys

N = int(sys.stdin.readline())
matrix = []
for _ in range(N):
    matrix.append(list(map(int, sys.stdin.readline().split())))

dp = [[0] * N for _ in range(N)]

# 각 행렬 간의 거리 간격
for interval in range(1, N):
    for i in range(N - interval):
        # i는 곱하는 행렬들 중 첫번째 행렬을 의미
        # j는 곱하는 행렬들 중 마지막 행렬을 의미
        j = i + interval

        # 행렬 간의 간격이 1이라는 것은 바로 옆에 있는 행렬임을 의미
        # 바로 옆에 있는 행렬은 단순히 곱하면 그것이 최솟값이다.
        if interval == 1:
            dp[i][j] = matrix[i][0] * matrix[i][1] * matrix[j][1]
            continue

        dp[i][j] = 2 ** 31

        # k는 i행렬부터 몇 번째 행렬까지 그룹을 지을지를 의미
        for k in range(i, j):
            dp[i][j] = min(dp[i][j], dp[i][k] + dp[k + 1][j] + (matrix[i][0] * matrix[k][1] * matrix[j][1]))

print(dp[0][-1])