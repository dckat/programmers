import sys

inputIP = list(sys.stdin.readline().strip().split(":"))

cnt = inputIP.count('')
numToFill = 8 - len(inputIP)    # 채워야 할 '0000:'의 개수
# ''의 개수가 1개라면 중간에 ::가 있는 것을 의미
if cnt == 1:
    inputIP[inputIP.index('')] = '0000:' * numToFill + '0000'
# ''의 개수가 2개라면 시작 또는 끝에 ::가 있는 것을 의미
elif cnt == 2:
    inputIP[inputIP.index('')] = '0000:' * (numToFill + 1) + '0000'
    inputIP.remove('')

for i, ip in enumerate(inputIP):
    if len(ip) < 4:
        inputIP[i] = ip.zfill(4)

print(":".join(inputIP))

