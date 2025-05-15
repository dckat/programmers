import sys
input = sys.stdin.readline

def solution():
    n, k = map(int, input().split())
    data = list(map(int, input().split()))
    
    # 인접 값끼리의 차를 구하고
    # 가장 큰 k-1개의 차를 제외하고 다 더하면 답이 됨
    
    subs = [0] * (n-1)
    for i in range(n-1):
        subs[i] = data[i+1] - data[i]
        
    subs.sort()
    print(sum(subs[:n-k]))


if __name__ == '__main__':
    solution()