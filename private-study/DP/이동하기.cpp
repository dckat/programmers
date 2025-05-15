#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;


int main() {
    int n, m;
    cin >> n >> m;

    vector<vector<int>> a(n+1, vector<int>(m+1));
    vector<vector<int>> d(n+1, vector<int>(m+1, 0));

    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= m; j++) {
            cin >> a[i][j];
        }
    }

    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= m; j++) {
            d[i][j] = max(d[i-1][j], d[i][j-1])+a[i][j];
        }
    }

    cout << d[n][m] << '\n';
    return 0;
}