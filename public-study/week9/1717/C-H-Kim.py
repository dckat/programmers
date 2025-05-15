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


n, m = map(int, sys.stdin.readline().split())
parent = [i for i in range(n + 1)]
height = [1 for _ in range(n + 1)]

for _ in range(m):
    opt, a, b = map(int, sys.stdin.readline().split())

    if opt == 0:
        union(a, b)
    else:
        if find(a) == find(b):
            print("YES")
        else:
            print("NO")