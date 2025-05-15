import sys
input = sys.stdin.readline

def solution():
    n = int(input())
    data = []
    for _ in range(n):
        s, t = map(int, input().split())
        data.append((s, t))
    
    # 끝 시간 - 시작 시간 순 정렬
    # ex) 4 4 / 1 4 이렇게 입력되면 1 4 / 4 4 로 정렬하도록
    data.sort(key=lambda x: (x[1], x[0]))
    
    ans = 1
    st = data[0][1] # start time
    for i in range(1, n):
        if st <= data[i][0]:
            st = data[i][1]
            ans += 1
            
    print(ans)


if __name__ == '__main__':
    solution()