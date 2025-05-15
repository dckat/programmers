import sys
from math import sqrt
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
    
def calc_dist(s1, s2):
    return sqrt((s1[0]-s2[0]) ** 2 + (s1[1]-s2[1]) ** 2)

def solution():
    global parent
    n = int(input())
    parent = [i for i in range(n)]
    star = []
    graph = []
    for _ in range(n):
        x, y = map(float, input().split())
        star.append((x, y))
    
    # create star graph
    for i in range(n):
        for j in range(i+1, n):
            weight = calc_dist(star[i], star[j])
            graph.append((weight, i, j))
            
    # Kruskal
    ans = 0
    graph.sort()
    for data in graph:
        weight, a, b = data
        if find(a) != find(b):
            ans += weight
            union(a, b)
    
    print("{:.2f}".format(ans))


if __name__ == '__main__':
    solution()