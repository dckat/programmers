#include <iostream>
using namespace std;

long long mod = 1000000007;
long long d[5001];

long long solution(int length) {
    if (length == 0) {
        return 1;
    }
    if (d[length] > 0) {
        return d[length];
    }

    for (int i = 2; i <= length; i+=2) {
        d[length] += (solution(i-2) * solution(length-i));
        d[length] %= mod;
    }
    return d[length];
}

int main() {
    int T;
    cin >> T;

    while (T--) {
        int L;
        cin >> L;

        if (L % 2 != 0) {
            cout << 0 << '\n';
        }
        else {
            cout << solution(L) << '\n';
        }
    }
}