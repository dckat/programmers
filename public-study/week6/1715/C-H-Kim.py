import sys
from queue import PriorityQueue

queue = PriorityQueue()

N = int(sys.stdin.readline().strip())
for _ in range(N):
    queue.put(int(sys.stdin.readline().strip()))

answer = 0
while queue.qsize() >= 2:
    first = queue.get()
    second = queue.get()

    subsum = first + second
    queue.put(subsum)

    answer += subsum

print(answer)