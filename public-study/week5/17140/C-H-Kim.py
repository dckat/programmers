import sys


def operR():
    global A
    sorted_matrix = []
    max_count = 0

    # 행의 개수만큼 실행
    for i in range(len(A)):
        atomic = list(set(A[i]))    # 행에 있는 수를 카운트 하기 위해 만든 list, 중복이 제거되어 있음
        if 0 in atomic:
            atomic.remove(0)        # 0은 정렬에서 무시
        row = []

        for num in atomic:
            row.append([num, A[i].count(num)])

        row.sort(key=lambda x: [x[1], x[0]])
        temp = sum(row, [])         # 2차원 리스트를 1차원 리스트로 변환
        max_count = max(max_count, len(temp))   # 가장 긴 행의 길이로 0을 맞춰주기 위해 max값을 구함
        sorted_matrix.append(temp)  # 정렬된 값들을 append

    for sorted_row in sorted_matrix:
        sorted_row += [0] * (max_count - len(sorted_row))   # 가장 긴 행의 길이만큼 0을 채워줌
        if len(sorted_row) > 100:   # 행의 크기가 100이 넘어가면 처음 100개만 저장
            sorted_row = sorted_row[:100]

    return sorted_matrix


def operC():
    global A
    sorted_matrix = []
    max_count = 0

    # 열의 개수만큼 실행
    for i in range(len(A[0])):
        col = [column[i] for column in A]   # 하나의 열의 값들이 저장되어 있는 list
        atomic = list(set(col))             # 열에 있는 수를 카운트 하기 위해 만든 list
        if 0 in atomic:
            atomic.remove(0)                # 0은 정렬에서 무시
        row = []

        for num in atomic:
            row.append([num, col.count(num)])

        row.sort(key=lambda x: [x[1], x[0]])
        temp = sum(row, [])  # 2차원 리스트를 1차원 리스트로 변환
        max_count = max(max_count, len(temp))  # 가장 긴 행의 길이로 0을 맞춰주기 위해 max값을 구함
        sorted_matrix.append(temp)  # 정렬된 값들을 append

    for sorted_row in sorted_matrix:
        sorted_row += [0] * (max_count - len(sorted_row))   # 가장 긴 행의 길이만큼 0을 채워줌
        if len(sorted_row) > 100:   # 행의 크기가 100이 넘어가면 처음 100개만 저장
            sorted_row = sorted_row[:100]

    return list(zip(*sorted_matrix))


r, c, k = map(int, sys.stdin.readline().split())
A = [list(map(int, sys.stdin.readline().split())) for _ in range(3)]
r = r - 1
c = c - 1

time = 0
result = -1

while time <= 100:
    if r < len(A) and c < len(A[0]):
        if A[r][c] == k:
            result = time
            break

    if len(A) >= len(A[0]):
        A = operR()
    else:
        A = operC()

    time += 1

print(result)