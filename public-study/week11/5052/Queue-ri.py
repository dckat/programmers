import sys
input = sys.stdin.readline

def solution():
    n = int(input())
    phone_book = []
    for _ in range(n):
        phone_book.append(input().rstrip())
    
    phone_book.sort()
    
    for i in range(n-1):
        if phone_book[i] == phone_book[i+1][:len(phone_book[i])]:
            print('NO')
            return
        
    print('YES')
        

if __name__ == '__main__':
    t = int(input())
    for _ in range(t):
        solution()