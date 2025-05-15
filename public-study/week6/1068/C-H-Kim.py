import sys

def remove(target):
    global parentNodeList

    # 제거할 노드의 부모 노드번호를 -2로 변경(방문처리)
    parentNodeList[target] = -2

    for i in range(len(parentNodeList)):
        if parentNodeList[i] == target:     # 지울 노드번호를 부모로 하는 노드가 있다면 그 노드도 제거
            remove(i)


N = int(sys.stdin.readline().strip())
parentNodeList = list(map(int, sys.stdin.readline().split()))
removeNode = int(sys.stdin.readline().strip())

remove(removeNode)

count = 0
for i in range(len(parentNodeList)):
    if parentNodeList[i] != -2 and i not in parentNodeList:
        count += 1

print(count)
