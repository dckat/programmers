import sys
input = sys.stdin.readline

def solution():
    addr = input().rstrip().split(':')
    #print('addr:', addr)
    ans = []

    found = False
    for e in addr:
        if e == '':
            if found:
                addr.remove(e)
            else:
                found = True
    
    # count after process
    zcnt = 0
    if found:
        zcnt = 8 - len(addr) + 1 # include ''
        
    zlist = ['0000'] * zcnt
    #print(zlist)
    
    for i, e in enumerate(addr):
        if e == '':
            ans.extend(zlist)
        else:
            ez = e.zfill(4)
            ans.append(ez)
        
    print(':'.join(x for x in ans))

if __name__ == '__main__':
    solution()