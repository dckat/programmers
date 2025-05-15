import sys


def find(num):
    if num == parent[num]:
        return num

    parent[num] = find(parent[num])

    return parent[num]


def union(x, y):
    x = find(x)
    y = find(y)

    # y 트리의 높이가 x 트리의 높이보다 큰 경우 x와 y를 swap
    if height[x] < height[y]:
        x, y = y, x

    # y의 부모 노드를 x로 설정
    parent[y] = x

    # 합치고자 하는 두 트리의 높이가 같은 경우 기준 트리의 높이를 한 층 증가
    if height[x] == height[y]:
        height[x] += 1


N = int(sys.stdin.readline())
M = int(sys.stdin.readline())

parent = [i for i in range(N)]
height = [1 for _ in range(N)]

for i in range(N):
    temp = list(map(int, sys.stdin.readline().split()))
    for j in range(N):
        if temp[j] == 1:
            union(i, j)

plan = list(map(int, sys.stdin.readline().split()))
plan = list(set(plan))      # 계획한 여행지 중복 제거

answer = "YES"
pivot = parent[plan[0] - 1]
length = len(plan)
for i in range(1, length):
    # 첫 번째 여행지가 속한 그룹과 다르다면 갈 수 없음을 의미
    if parent[plan[i] - 1] != pivot:
        answer = "NO"
        break

print(answer)