import sys
import heapq


def dijkstra(startNode):
    # 시작 노드에 대한 거리 값을 0으로 초기화
    distance[startNode] = 0

    queue = []
    heapq.heappush(queue, [distance[startNode], startNode])

    while queue:
        # 현재 노드까지의 최소거리, 현재 노드
        curDist, curNode = heapq.heappop(queue)

        # 현재 거리가 기존에 저장된 거리보다 클 경우 연산할 필요가 없음
        if distance[curNode] < curDist:
            continue

        for nextNode, nextDist in graph[curNode]:
            dist = curDist + nextDist           # 현재 노드까지의 최소거리 + 인접한 노드까지의 거리

            if dist < distance[nextNode]:       # 새로 계산된 거리가 인접한 노드가 가지고 있는 거리보다 작다면 갱신
                distance[nextNode] = dist
                heapq.heappush(queue, [dist, nextNode])


n, m, r = map(int, sys.stdin.readline().split())
items = [0] + list(map(int, sys.stdin.readline().split()))

graph = [[] for _ in range(n + 1)]
for _ in range(r):
    a, b, l = map(int, sys.stdin.readline().split())

    graph[a].append([b, l])
    graph[b].append([a, l])

maxItems = 0
for i in range(1, n + 1):
    distance = [float('inf') for _ in range(n + 1)]

    dijkstra(i)

    itemSum = 0
    for j in range(1, n + 1):
        if distance[j] <= m:
            itemSum += items[j]

    maxItems = max(maxItems, itemSum)

print(maxItems)