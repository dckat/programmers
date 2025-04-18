#include <iostream>
using namespace std;

int n;
long long x;
long long b[51];
long long p[51];

void input() {
    cin >> n >> x;

    b[0] = 1;
    p[0] = 1;
    for (int i = 1; i <= n; i++) {
        b[i] = 2*b[i-1] + 3;
        p[i] = 2*p[i-1] + 1;
    }
}

long long go(int n, long long x) {
    if (n == 0) {
        if (x == 0) {
            return 0;
        }
        else {
            return 1;
        }
    }
    else if (x == 1) {
        return 0;
    }
    else if (x > 1 && x <= b[n-1] + 1) {
        return go(n-1, x-1);
    }
    else if (x == 2 + b[n-1]) {
        return p[n-1] + 1;
    }
    else if (x > 2 + b[n-1] && x <= 2*b[n-1] + 2) {
        return p[n-1] + 1 + go(n-1, x-2-b[n-1]);
    }
    else {
        return 2*p[n-1] + 1;
    }
}

void solution() {
    long long answer = go(n, x);
    cout << answer << '\n';
}

int main() {
    input();
    solution();
    return 0;
}