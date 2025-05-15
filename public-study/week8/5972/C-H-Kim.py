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

        for nextNode, nextDist in graph[curNode]:
            dist = curDist + nextDist           # 현재 노드까지의 최소거리 + 인접한 노드까지의 거리
            if dist < distance[nextNode]:       # 새로 계산된 거리가 인접한 노드가 가지고 있는 거리 정보보다 작다면 갱신
                distance[nextNode] = dist
                heapq.heappush(queue, [dist, nextNode])


N, M = map(int, sys.stdin.readline().split())

graph = [[] for _ in range(N + 1)]

for _ in range(M):
    fromNode, toNode, cow = map(int, sys.stdin.readline().split())

    graph[fromNode].append([toNode, cow])
    graph[toNode].append([fromNode, cow])

# 0번 노드가 있기 때문에 N + 1 개의 노드에 대한 거리 배열을 infinity로 초기화
distance = [float('inf') for _ in range(N + 1)]
dijkstra(1)

print(distance[-1])