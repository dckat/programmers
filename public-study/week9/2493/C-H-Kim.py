import sys

N = int(sys.stdin.readline())
towers = list(map(int, sys.stdin.readline().split()))

candidate = []      # 신호를 수신할 탑의 후보를 저장할 스택 / 인덱스로 저장
answer = []         # 신호를 수신하는 탑의 인덱스를 저장하는 배열

for i in range(N):
    # 후보를 순회하면서 현재 타워의 신호를 수신할 타워를 찾는다.
    while candidate:
        # 현재 타워보다 왼쪽에 있는 후보 타워 중 가장 오른쪽에 있는 타워를 현재 후보로 설정
        candiTowerIndex = candidate[-1]

        # 후보 타워가 현재 타워보다 더 작다면 후보 타워를 후보에서 제외
        if towers[candiTowerIndex] < towers[i]:
            candidate.pop()
        # 후보 타워가 현재 타워보다 크다는 것은 현재 타워의 신호를 수신할 수 있음을 의미
        else:
            answer.append(candiTowerIndex + 1)
            break

    # 스택이 비어있다는 것은 신호를 수신할 탑의 후보가 없다는 것을 의미
    # 즉 현재 타워의 왼쪽에 현재 타워보다 높은 타워가 없다는 것을 의미
    if not candidate:
        answer.append(0)

    # 신호를 수신할 타워를 찾는 작업이 끝났다면 현재 타워 또한 후보에 넣음
    candidate.append(i)

# print(candidate)
print(*answer)