import sys
input = sys.stdin.readline

# 크레인과 박스를 내림차순 정렬하고
# batch(=크레인 개수) 단위로 큰 박스부터 처리

def solution():
    n = int(input())
    crane = list(map(int, input().split()))
    m = int(input())
    box = list(map(int, input().split()))
    
    crane.sort(reverse=True)
    box.sort(reverse=True)
    
    # corner case
    if crane[0] < box[0]:
        print(-1)
        return
    
    ans = 0
    remained = m
    done = [False] * m
    while remained > 0:
        last_cdx = 0
        for bdx in range(m):
            if last_cdx > n-1:
                break
            
            if not done[bdx]:
                # check crane availability
                if box[bdx] <= crane[last_cdx]:
                    done[bdx] = True
                    last_cdx += 1
                    remained -= 1
        
        ans += 1
    
    print(ans)
    
    
if __name__ == '__main__':
    solution()