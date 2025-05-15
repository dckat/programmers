#include <iostream>
#include <string>
using namespace std;

int d[1001][1001];

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
            }
            else {
                d[i][j] = max(d[i-1][j], d[i][j-1]);
            }
        }
    }
    cout << d[n][m] << '\n';
    return 0;
}