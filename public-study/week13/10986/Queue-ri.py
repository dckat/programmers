import sys
input = sys.stdin.readline

def solution():
    n, m = map(int, input().split())
    series = [-1] + list(map(int, input().split()))
    
    # hap[k] -> A1 ~ Ak 까지의 구간합
    hap_mod = [0] * (n+1)
    
    # hap_cnt[m-1] -> '구간합 % m'이 m-1인 경우의 수
    hap_mod_cnt = [0] * (m+1)
    
    # calc
    for i in range(1, n+1):
        hap_mod[i] = (hap_mod[i-1] + series[i]) % m
        hap_mod_cnt[hap_mod[i]] += 1
    
    # m으로 나눴을 때 나머지가 0인 경우 모두 합산    
    ans = hap_mod_cnt[0]
    
    ## xC2 = x(x-1) / 2
    for i in range(m+1):
        ans += (hap_mod_cnt[i] * (hap_mod_cnt[i]-1)) // 2
    
    print(ans)


if __name__ == '__main__':
    solution()