#include <iostream>
#include <algorithm>
using namespace std;

long long n, m;

void input() {
    cin >> n >> m;
}

void solution() {
    // layer당 4번의 꺾임 발생
    // layer당 가로.세로 전체는 2씩 감소
    long long d = (min(n, m) - 1) / 2;
    long long result = 4 * d;

    // layer당 시작점 좌표는 오른쪽 대각선 만큼 증가
    long long x = 1 + d;
    long long y = 1 + d;

    n -= 2*d;
    m -= 2*d;

    // 남은 열 또는 행에 따른 좌표와 꺾임 횟수 구하기
    if (n == 1) {
        y += (m - 1);
    }
    else if (m == 1) {
        result++;
        x += (n - 1);
    }
    else if (n == 2) {
        result += 2;
        x++;
    }
    else {
        result += 3;
        x++;
    }

    cout << result << '\n';
    cout << x << ' ' << y;
}

int main() {
    input();
    solution();
    return 0;
}