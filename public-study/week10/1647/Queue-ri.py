import sys
input = sys.stdin.readline

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
    
    n, m = map(int, input().split())
    parent = [x for x in range(n+1)]
    graph = []
    for _ in range(m):
        a, b, weight = map(int, input().split())
        graph.append((weight, a, b))
        
    path = []
    graph.sort()
    for data in graph:
        weight, a, b = data
        if find(a) != find(b):
            union(a, b)
            path.append(weight)
    
    print(sum(path) - max(path))


if __name__ == '__main__':
    solution()