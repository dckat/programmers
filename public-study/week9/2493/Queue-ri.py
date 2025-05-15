import sys
input = sys.stdin.readline

def solution():
    n = int(input())
    tower = list(map(int, input().split()))
    st = [] # stack
    
    ans = [0] * n
    for idx in range(1, n+1): # tower # starts from 1
        
        while st:
            top = st[-1]
            if top[1] > tower[idx-1]: # top() height > tower[idx] height
                ans[idx-1] = top[0] # top() receives tower[idx] signal
                break
            else:
                st.pop() # update top()
                
        st.append((idx, tower[idx-1])) # tower(idx, height)
        
    print(*ans)


if __name__ == '__main__':
    solution()