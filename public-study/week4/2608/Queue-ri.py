import sys
input = sys.stdin.readline

data_1w = dict({'I':1, 'V':5, 'X':10, 'L':50, 'C':100, 'D':500, 'M':1000})
data_2w = dict({'IV':4, 'IX':9, 'XL':40, 'XC':90, 'CD':400, 'CM':900})

def decode(a):
    i, res = 0, 0
    while i < len(a)-1:
        key = a[i] + a[i+1]
        if key in data_2w:
            res += data_2w[key]
            i += 1
        else:
            res += data_1w[a[i]]
            
        i += 1
    
    if i != len(a):
        res += data_1w[a[-1]]
    return res

def encode(b):
    res = ''
    while b > 0:
        if b >= 1000:
            res += 'M'
            b -= 1000
        elif b >= 900:
            res += 'CM'
            b -= 900
        elif b >= 500:
            res += 'D'
            b -= 500
        elif b >= 400:
            res += 'CD'
            b -= 400
        elif b >= 100:
            res += 'C'
            b -= 100
        elif b >= 90:
            res += 'XC'
            b -= 90
        elif b >= 50:
            res += 'L'
            b -= 50
        elif b >= 40:
            res += 'XL'
            b -= 40
        elif b >= 10:
            res += 'X'
            b -= 10
        elif b >= 9:
            res += 'IX'
            b -= 9
        elif b >= 5:
            res += 'V'
            b -= 5
        elif b >= 4:
            res += 'IV'
            b -= 4
        elif b >= 1:
            res += 'I'
            b -= 1
    return res

def solution():
    global data_key, data_val
    a = input().rstrip()
    b = input().rstrip()
    
    res1 = decode(a) + decode(b)
    print(res1)
    print(encode(res1))


if __name__ == '__main__':
    solution()