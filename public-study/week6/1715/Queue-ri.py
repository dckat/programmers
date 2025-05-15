import sys
import heapq
input = sys.stdin.readline

n = 0
heap = []

def solution():
    global n, heap
    n = int(input())
    for i in range(n):
        heapq.heappush(heap, int(input()))
    
    ans = 0
    
    # edge case
    if n == 1:
        print(0) # no need to compare
        return
    
    # else
    while True:
        lhs = heapq.heappop(heap)
        try:
            rhs = heapq.heappop(heap)
        except:
            break
        
        # merge
        k = lhs + rhs
        heapq.heappush(heap, k)
        ans += k
        
    print(ans)
    

if __name__ == '__main__':
    solution()