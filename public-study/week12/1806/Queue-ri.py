import sys
input = sys.stdin.readline

def solution():
    n, s = map(int, input().split())
    series = list(map(int, input().split()))
    
    l, r = 0, 0
    sum = 0
    ans = 1e6
    
    while True:
        if sum >= s: # 합 조건 충족 -> 길이값 갱신 시도
            ans = min(ans, r-l)
            sum -= series[l]
            l += 1
        elif r >= n: # 끝 도달 -> 종료
            break
        else: # 원소 추가
            sum += series[r]
            r += 1
            
    if ans == 1e6:
        print(0)
    else:
        print(ans)


if __name__ == '__main__':
    solution()