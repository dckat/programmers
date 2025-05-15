import sys
input = sys.stdin.readline

def solution():
    m, n = map(int, input().split())
    
    # 가로(m), 세로(n) 중 더 짧은 값을 기준으로 연산
    print(n*2-1 if m > n else m*2-2)
    
    # 좌표 연산
    if m == n: # 1:1 비율
        # 길이가 홀수면 정 가운데가 마지막 좌표
        # 길이가 짝수면 가운데 4칸 중 왼쪽 아래칸이 마지막 좌표
        print(*(m//2+1, n//2+1) if m & 1 else (m//2+1, n//2))
    elif m > n: # 아래로 길쭉
        # 더 짧은 길이인 n의 홀짝 여부에 따라 분기
        # 홀수면 아래로 이동하다가 마지막 좌표가 결정됨
        # 짝수면 위로 이동하다가 마지막 좌표가 결정됨
        print(*(n//2+1+(m-n), n//2+1) if n & 1 else (n//2+1, n//2))
    else: # 옆으로 길쭉
        # 더 짧은 길이인 m의 홀짝 여부에 따라 분기
        # 홀수면 우측으로 이동하다가 마지막 좌표가 결정됨
        # 짝수면 좌측으로 이동하다가 마지막 좌표가 결정됨
        print(*(m//2+1, m//2+1+(n-m)) if m & 1 else (m//2+1, m//2))
    

if __name__ == '__main__':
    solution()