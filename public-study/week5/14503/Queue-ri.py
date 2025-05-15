import sys
input = sys.stdin.readline

dy = [-1, 0, 1, 0] # 북 동 남 서
dx = [0, 1, 0, -1]

def solution():
    n, m = map(int, input().split())
    r, c, d = map(int, input().split())
    
    room = [0] * (n)
    for i in range(n):
        room[i] = list(map(int, input().split()))
        
    ans = 1 # 맨 처음 위치를 청소하고 시작
    room[r][c] = -1
    y, x = r, c
    
    while True:
        cleaned = False
        
        for _ in range(4):
            d = (d + 3) % 4 # 반시계 회전
            ny = y + dy[d]
            nx = x + dx[d]
            
            if room[ny][nx] == 0: # 3. 청소 안되어있음
                room[ny][nx] = -1
                ans += 1 # 청소하고
                y, x = ny, nx
                cleaned = True
                break # 이동 후 다시 4방향 탐색
            
        if not cleaned: # 2. 4방향 다 청소할 곳 없음
            ty = y - dy[d]
            tx = x - dx[d]
            if room[ty][tx] == 1: # 후진했는데 벽일 경우
                return ans
            else:
                y, x = ty, tx


if __name__ == '__main__':
    print(solution())