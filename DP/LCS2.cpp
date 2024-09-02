#include <iostream>
#include <string>
#include <algorithm>
using namespace std;

int d[1001][1001];
int v[1001][1001];

int main() {
    string a, b;
    cin >> a >> b;

    int n = a.length();
    int m = b.length();

    a = " " + a;
    b = " " + b;

    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= m; j++) {
            if (a[i] == b[j]) {
                d[i][j] = d[i-1][j-1] + 1;
                v[i][j] = 1;
            }
            else {
                if (d[i-1][j] > d[i][j-1]) {
                    d[i][j] = d[i-1][j];
                    v[i][j] = 2;
                }
                else {
                    d[i][j] = d[i][j-1];
                    v[i][j] = 3;
                }
            }
        }
    }
    cout << d[n][m] << '\n';

    string ans = "";

    while (n > 0 && m > 0) {
        if (v[n][m] == 1) {
            ans += a[n];
            n--;
            m--;
        }
        else if (v[n][m] == 2) {
            n--;
        }
        else {
            m--;
        }
    }
    reverse(ans.begin(), ans.end());
    cout << ans << '\n';
    return 0;
}