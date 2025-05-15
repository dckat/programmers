import sys

N, S = map(int, sys.stdin.readline().split())
num_list = list(map(int, sys.stdin.readline().split()))

left_pointer, right_pointer = 0, 0
sum = 0
min_result = 100000

while True:
    # 현재까지의 합이 S 이상이라면 최소 길이 최신화, 현재 합에서 하나 씩 빼보면서
    # 여전히 합이 S 이상인지 체크(left_pointer를 옆으로 한 칸 이동)
    if sum >= S:
        min_result = min(min_result, (right_pointer - left_pointer))
        sum -= num_list[left_pointer]
        left_pointer += 1
    # right_pointer가 N이라면 더 이상 더할 숫자가 없다는 것을 의미
    elif right_pointer == N:
        break
    # 현재까지의 합이 S 이상이 아니고 아직 더할 숫자가 남아있다면
    # 현재 right_pointer가 가르키는 숫자를 더하고 옆으로 한 칸 이동
    else:
        sum += num_list[right_pointer]
        right_pointer += 1

# 수열을 다 순회했는데도 최대 길이 값이라면 연속된 합이 S 이상인 것이 없다는 것을 의미
# 불가능하다면 0을 출력
if min_result == 100000:
    print(0)
else:
    print(min_result)

"""
현재까지의 합이 S이상인지 체크하는 것을 제일 먼저 체크하는 이유
->  가장 먼저 체크하지 않는다면 right_pointer가 수열의 끝에 다다랐을 때
    길이를 더 줄일 수 있음에도 불구하고 반복문을 탈출하기 때문이다.
"""