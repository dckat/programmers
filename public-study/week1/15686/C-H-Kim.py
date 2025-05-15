import sys
from itertools import combinations

N, M = map(int, sys.stdin.readline().split())
city = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]

house = []
chicken = []
answer = float("inf")

for i in range(N):
    for j in range(N):
        if city[i][j] == 1:
            house.append([i, j])
        elif city[i][j] == 2:
            chicken.append([i, j])

chickenCombi = list(combinations(chicken, M))
for combi in chickenCombi:
    total_distance = 0
    for h in house:
        # distance : 각 집에서 치킨집까지의 거리
        distance = float("inf")
        for i in range(M):
            # h[0], combi[i][0] : 행
            # h[1], combi[i][1] : 열
            distance = min(distance, (abs(h[0] - combi[i][0]) + abs(h[1] - combi[i][1])))
        total_distance += distance
    answer = min(answer, total_distance)

print(answer)
