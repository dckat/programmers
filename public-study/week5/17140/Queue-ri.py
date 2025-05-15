import sys
from collections import Counter
input = sys.stdin.readline

def calc(matrix): # R연산
    mx = 0
    for i in range(len(matrix)):
        res = sorted(Counter(matrix[i]).most_common(), key=lambda x:(x[1], x[0]))
        tmp = []
        for v, cnt in res:
            if v == 0: continue
            tmp.append(v)
            tmp.append(cnt)
        matrix[i] = tmp
        mx = max(mx, len(tmp))
    
    # zero padding
    for i in range(len(matrix)):
        if len(matrix[i]) < mx:
            matrix[i].extend([0] * (mx-len(matrix[i])))
    
    return matrix

def solution():
    r, c, k = map(int, input().split())
    r, c = r - 1, c - 1
    A = [0] * 3
    for i in range(3):
        A[i] = list(map(int,input().split()))
    
    for t in range(101):
        rlen = len(A)
        clen = len(A[0])
        if r < rlen and c < clen and A[r][c] == k:
            print(t)
            return
        if rlen >= clen: # R연산
            A = calc(A)
        else: # C연산
            A = calc(list(zip(*A)))
            A = list(zip(*A))
            
    print(-1)


if __name__ == '__main__':
    solution()