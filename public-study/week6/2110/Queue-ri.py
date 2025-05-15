import sys
input = sys.stdin.readline

n, c = 0, 0
data = []

def is_able(d): # 공유기를 해당 거리로 둘 수 있는지 판단
    # 거리가 d가 아니면 pass
    ldx, rdx = 0, 1 # --> data[0], data[1]
    res = 0
    while rdx < len(data):
        cur_d = data[rdx] - data[ldx]
        if cur_d >= d:
            res += 1
            ldx = rdx
            
        rdx += 1
    
    #print("/ res c:", res, c)
    return res >= (c-1) # ex) 3번 True일 시 --> 4개의 집에 설치됨

def bsearch(start, end):
    ans = -1
    while start <= end:
        mid = (start + end) // 2
        #print("start mid end:", start, mid, end, end=" ")
        #print("/ is_able:", is_able(mid))
        
        if is_able(mid):
            ans = mid
            start = mid + 1
        else:
            end = mid - 1
            
    return ans

def solution():
    global n, c, data
    n, c = map(int, input().split())
    data = [0] * n
    
    for i in range(n):
        data[i] = int(input())
        
    data = sorted(data)
    mxd = data[-1]
    #print("data mxd:", data, mxd)
    #print("c:", c)
    #print("--------------------")
    print(bsearch(1, mxd))


if __name__ == '__main__':
    solution()