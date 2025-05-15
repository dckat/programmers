import sys
input = sys.stdin.readline
sys.setrecursionlimit(10**6)

tree = []
memo = []
visited = []

def dp(idx):
    # 1: 얼리어답터 / 0: 아님
    global memo, visited
    
    visited[idx] = True
    
    if len(tree[idx]) == 0: # leaf node
        memo[idx][0] = 0
        memo[idx][1] = 1
        return
    
    for cdx in tree[idx]: # cdx: child index
        if visited[cdx] == 0:
            dp(cdx)
            # 부모가 무지랭이면 자식은 무조건 얼리어답터
            memo[idx][0] += memo[cdx][1]
            # 부모가 얼리면 자식은 얼리일수도 아닐수도, 하지만 최소치여야 함
            memo[idx][1] += min(memo[cdx][0], memo[cdx][1])
            
    memo[idx][1] += 1
        
    visited[idx] = False
    
    return
    

def solution():
    global tree, memo, visited
    
    n = int(input()) # 정점 개수 -> 간선 개수는 n-1
    tree = [[] for _ in range(n+1)]
    memo = [[0, 0] for _ in range(n+1)]
    visited = [False for i in range(n+1)]
    
    for _ in range(n-1):
        u, v = map(int, input().split())
        tree[u].append(v)
        tree[v].append(u)
    
    dp(1)
    print(min(memo[1][0], memo[1][1]))


if __name__ == '__main__':
    solution()