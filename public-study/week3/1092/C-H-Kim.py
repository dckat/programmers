import sys

N = int(sys.stdin.readline())
cranes = list(map(int, sys.stdin.readline().split()))
M = int(sys.stdin.readline())
boxes = list(map(int, sys.stdin.readline().split()))

# 내림차순 정렬
cranes.sort(reverse=True)
boxes.sort(reverse=True)

# 가장 높은 무게를 옮길 수 있는 크레인이
# 가장 무거운 박스를 옮기지 못할 때 모든 박스를 옮길 수 없다.
if cranes[0] < boxes[0]:
    print(-1)
    exit()

time = 0

# box가 다 없어질 떄까지 진행
while boxes:
    for crane in cranes:
        # 현재 크레인이 제일 가벼운 박스조차
        # 들지 못한다면 굳이 순회하며 체크할 필요가 없음
        if boxes and crane < boxes[-1]:
            continue
        for box in boxes:
            if crane >= box:
                boxes.remove(box)
                # 모든 크레인은 동시에 움직이지만
                # 한 크레인 당 하나의 박스만 옮길 수 있으므로
                break
    time += 1

print(time)