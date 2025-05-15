import sys
import heapq

N, M = map(int, sys.stdin.readline().split())

graph = [[] for _ in range(N + 1)]
inDegree = [0 for _ in range(N + 1)]

for i in range(M):
    A, B = map(int, sys.stdin.readline().split())
    graph[A].append(B)
    inDegree[B] += 1

queue = []
for i in range(1, N + 1):
    # 진입 차수가 0이라면 queue에 넣음
    if inDegree[i] == 0:
        heapq.heappush(queue, i)

answer = []
while queue:
    now = heapq.heappop(queue)
    answer.append(now)

    for adjacent in graph[now]:
        # 현재 queue에서 꺼낸 정점과 연결되어 있는 정점들의 진입 차수를 하나 감소
        inDegree[adjacent] -= 1

        # 하나를 감소시켰는데 진입 차수가 0이라면 queue에 push
        if inDegree[adjacent] == 0:
            heapq.heappush(queue, adjacent)

print(" ".join(map(str, answer)))