import sys

N, M = map(int, sys.stdin.readline().split())
num_arr = list(map(int, sys.stdin.readline().split()))

remain = [0] * M
sum = 0
for num in num_arr:
    sum += num
    remain[sum % M] += 1

answer = remain[0]  # 첫 번째 수부터 해당 수까지의 합 자체가 M으로 나누어 떨어지는 경우
# 같은 나머지끼리의 부분합 중 2개를 뽑아 M으로 나누어 떨어지게 하는 경우
for i in range(M):
    answer += remain[i] * (remain[i] - 1) // 2

print(answer)