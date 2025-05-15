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
    
    while True:
        m, n = map(int, input().split())
        if m == n == 0: break
        
        parent = [i for i in range(m)]
        tree = []
        zsum = 0
        for _ in range(n):
            x, y, z = map(int, input().split())
            zsum += z
            tree.append((z, x, y))
            
        # Kruskal
        mst_value = 0
        tree.sort()
        
        for data in tree:
            weight, a, b = data
            if find(a) != find(b):
                mst_value += weight
                union(a, b)
        
        print(zsum - mst_value)


if __name__ == '__main__':
    solution()