import sys
sys.setrecursionlimit(10 ** 9)

N = int(sys.stdin.readline())
edges = [list(map(int, sys.stdin.readline().split())) for _ in range(N - 1)]

arr = [[] for _ in range(N + 1)]
for i, j in edges:
    arr[i].append(j)
    arr[j].append(i)

# dp[노드][0] : 노드가 얼리어답터가 아닐 때 최소값
# dp[노드][1] : 노드가 얼리어답터일 때 최소값
dp = [[0, 0] for _ in range(N + 1)]

visited = [False] * (N + 1)

def dfs(node):
    visited[node] = True
    dp[node][1] = 1

    for adjacent_node in arr[node]:
        if not visited[adjacent_node]:
            dfs(adjacent_node)
            dp[node][0] += dp[adjacent_node][1]     # 현재 노드를 얼리 어답터가 아닐 때
            dp[node][1] += min(dp[adjacent_node][0], dp[adjacent_node][1])    # 현재 노드를 얼리 어답터일 때

dfs(1)

print(min(dp[1][0], dp[1][1]))