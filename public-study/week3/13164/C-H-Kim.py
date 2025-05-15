import sys

N, K = map(int, sys.stdin.readline().split())
height = list(map(int, sys.stdin.readline().split()))

diff = []
for i in range(len(height) - 1):
    diff.append(height[i + 1] - height[i])

diff.sort()

print(sum(diff[:N-K]))
