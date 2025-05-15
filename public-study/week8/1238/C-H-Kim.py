import sys
import heapq


def dijkstra(startNode):
    distance = [float('inf') for _ in range(N + 1)]
    distance[startNode] = 0

    queue = []
    heapq.heappush(queue, [distance[startNode], startNode])

    while queue:
        curDist, curNode = heapq.heappop(queue)

        if distance[curNode] < curDist:
            continue

        for nextNode, nextDist in graph[curNode]:
            dist = curDist + nextDist           # 현재 노드까지의 최소거리 + 인접한 노드까지의 거리

            if dist < distance[nextNode]:       # 새로 계산된 거리가 인접한 노드가 가지고 있는 거리 정보보다 작다면 갱신
                distance[nextNode] = dist
                heapq.heappush(queue, [dist, nextNode])

    return distance


N, M, X = map(int, sys.stdin.readline().split())

graph = [[] for _ in range(N + 1)]
for _ in range(M):
    S, E, T = map(int, sys.stdin.readline().split())

    graph[S].append([E, T])

# 시작노드가 X노드인 경우
back = dijkstra(X)

answer = 0
for i in range(1, N + 1):
    if i == X:          # 시작노드가 X노드라면 이미 수행하였기 때문에 pass
        continue

    go = dijkstra(i)    # 각 노드가 시작노드인 경우

    totalDist = go[X] + back[i]     # 한 노드에서 X노드로 가는 최단거리 + X노드에서 다시 그 노드로 돌아가는 최단거리
    answer = max(answer, totalDist)

print(answer)