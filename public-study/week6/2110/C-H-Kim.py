import sys

N, C = map(int, sys.stdin.readline().split())
house = []

for _ in range(N):
    house.append(int(sys.stdin.readline()))
house.sort()

# 이진 탐색을 할 때 사용할 시작값과 끝값 초기화
start = 1
end = house[-1] - house[0]  # 공유기 간의 최대 간격은 양 끝 좌표의 간격
answer = 0

while start <= end:
    install = 1                 # 공유기 설치 개수, 맨 앞집은 무조건 설치한다는 가정
    dist = (start + end) // 2   # 설정한 공유기 간의 거리
    current = house[0]          # 가장 최근에 공유기를 설치한 집

    for i in range(1, len(house)):
        # 집 사이의 거리가 임의로 설정한 공유기 간의 최소 거리(dist) 이상일 때 공유기 설치 가능
        if house[i] - current >= dist:
            install += 1
            current = house[i]

    if install >= C:    # 공유기 간의 거리가 좁다는 것을 의미 -> dist를 증가시켜야 하기에 start 값을 증가
        start = dist + 1
        answer = dist
    else:               # 공유기 간의 거리가 넓다는 것을 의미 -> dist를 감소시켜야 하기에 end 값을 감소
        end = dist - 1

print(answer)