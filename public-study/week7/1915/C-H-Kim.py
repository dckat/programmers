import sys

n, m = map(int, sys.stdin.readline().split())
arr = []
for _ in range(n):
    arr.append(list(map(int, sys.stdin.readline().strip())))

dp = [[0] * m for _ in range(n)]
answer = -1

for i in range(n):
    for j in range(m):
        # 첫 행, 첫 열인 경우
        if i == 0 or j == 0:
            dp[i][j] = arr[i][j]
        # 값이 0인 경우 1로만 구성되어 있는 정사각형이라는 조건을 만족시키지 못하기에 0으로 초기화
        elif arr[i][j] == 0:
            dp[i][j] = 0
        # 그 외의 경우 i, j를 오른쪽 아래로 갖는 1로만 구성된 최대 정사각형의 길이를 구한다.
        else:
            dp[i][j] = min(dp[i -1][j - 1], dp[i - 1][j], dp[i][j - 1]) + 1

        answer = max(answer, dp[i][j])

print(answer ** 2)