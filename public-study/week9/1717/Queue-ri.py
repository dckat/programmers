import sys
input = sys.stdin.readline
sys.setrecursionlimit(10**6)

n, m = 0, 0
parent = []

def find(t): # find t's root (t: target)
    global parent
    
    if t == parent[t]: # base case
        return t
    
    root = find(parent[t])
    parent[t] = root
    
    return root

def union(t1, t2):
    global parent
    
    r1 = find(t1)
    r2 = find(t2)
    
    if r1 == r2:
        return # same set
    elif r1 < r2:
        parent[r2] = r1
    else:
        parent[r1] = r2

def solution():
    global n, m, parent
    
    n, m = map(int, input().split())
    parent = [i for i in range(n+1)] # init
    
    for _ in range(m):
        op, t1, t2 = map(int, input().split())
        
        if op == 0:
            union(t1, t2)
        elif op == 1:
            print('YES' if find(t1) == find(t2) else 'NO')
    

if __name__ == '__main__':
    solution()