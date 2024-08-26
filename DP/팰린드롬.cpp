#include <iostream>
#include <vector>
using namespace std;

int a[2001];
int d[2001][2001];

int main() {
    cin.tie(NULL);
    cout.tie(NULL);
    ios_base::sync_with_stdio(false);

    int n, m;
    cin >> n;

    for (int i = 1; i <= n; i++) {
        cin >> a[i];
    }

    for (int i = 1; i <= n; i++) {
        d[i][i] = 1;
    }
    for (int i = 1; i <= n-1; i++) {
        if (a[i] == a[i+1]) {
            d[i][i+1] = 1;
        }
    }

    for (int i = n - 1; i >= 1; i--) {
        for (int j = i+2; j <= n; j++) {
            if (a[i] == a[j] && d[i+1][j-1] == 1) {
                d[i][j] = 1;
            }
        }
    }

    cin >> m;
    for (int i = 0; i < m; i++) {
        int s, e;
        cin >> s >> e;
        cout << d[s][e] << '\n';
    }

    return 0;
}