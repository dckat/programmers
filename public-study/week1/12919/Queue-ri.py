import sys
input = sys.stdin.readline
sys.setrecursionlimit(10**6)

s = ''

def dp(t):
    global memo
    #print(t)
    if t == s:
        print(1)
        exit(0)
    
    if len(t) <= len(s):
        return
    
    # 이전 문자열 구하기
    if t[0] == 'B':
        prev = (t[1:])[::-1]
        dp(prev)
    if t[-1] == 'A':
        prev = t[:-1]
        dp(prev)
    
    return

def solution():
    global s
    s = input().rstrip()
    t = input().rstrip()
    dp(t)
    print(0)

if __name__ == '__main__':
    solution()