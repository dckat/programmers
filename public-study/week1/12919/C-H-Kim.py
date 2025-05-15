import sys

S = sys.stdin.readline().strip()
T = sys.stdin.readline().strip()

def sol(t):
    global answer

    if t == S:
        answer = 1
        return

    if len(t) == 0:
        return

    # T문자열 뒤에 A가 있으면
    if t[-1] == 'A':
        # 제거 후 재귀
        sol(t[:-1])

    # T문자열 앞에 B가 있으면
    if t[0] == 'B':
        # 제거 후 뒤집고 재귀
        sol(t[1:][::-1])

answer = 0
sol(T)
print(answer)