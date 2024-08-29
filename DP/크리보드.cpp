#include <iostream>
#include <algorithm>
using namespace std;

long long d[101];

int main() {
    int n;

    cin >> n;

    d[0] = 0;
    for (int i = 1; i <= n; i++) {
        d[i] = d[i-1] + 1;

        for (int j = 1; j <= i-2; j++) {
            d[i] = max(d[i], d[i-(j+2)]*(j+1));
        }
    }
    cout << d[n] << '\n';
    return 0;
}