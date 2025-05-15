import sys

C, N = map(int, sys.stdin.readline().split())
cost_list = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]
dp = [int(1e5) for _ in range(C + 100)]
dp[0] = 0

for cost, people in cost_list:
    for i in range(people, C + 100):
        # "현재 i명 늘어날 때 드는 최소 비용"과
        # "지금까지 든 홍보 비용 + 현재 도시에 대한 홍보 비용" 중 최소값 선택
        dp[i] = min(dp[i], dp[i - people] + cost)

print(min(dp[C:]))