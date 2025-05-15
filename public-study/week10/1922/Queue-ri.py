import sys
input = sys.stdin.readline
sys.setrecursionlimit(10**6)

parent = []

def union(a, b):
    global parent
    
    pa = find(a)
    pb = find(b)
    
    if pa < pb:
        parent[pb] = pa
    else:
        parent[pa] = pb
    

def find(x):
    global parent
    
    if parent[x] != x:
        parent[x] = find(parent[x])
    return parent[x]


def solution():
    global parent
    
    n = int(input())
    m = int(input())
    parent = [x for x in range(n+1)]
    tree = []
    for _ in range(m):
        a, b, weight = map(int, input().split())
        tree.append((weight, a, b))
        
    # Kruskal
    ans = 0
    tree.sort()
    for data in tree:
        weight, a, b = data
        if find(a) != find(b):
            ans += weight
            union(a, b)
            
    print(ans)


if __name__ == '__main__':
    solution()