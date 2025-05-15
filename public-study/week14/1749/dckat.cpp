#include <iostream>
#include <vector>
#include <climits>
#include <cmath>
#include <algorithm>
using namespace std;

int n, m;
int arr[201][201];
int dp[201][201];

void input() {
    cin >> n >> m;

    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= m; j++) {
            cin >> arr[i][j];
        }
    }
}

void solution() {
    int result = INT_MIN;
    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= m; j++) {
            dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + arr[i][j];
        }
    }

    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= m; j++) {
            for (int k = i; k <= n; k++) {
                for (int l = j; l <= m; l++) {
                    int tmp = dp[k][l] - dp[k][j-1] - dp[i-1][l] + dp[i-1][j-1];
                    result = max(result, tmp);
                }
            }
        }
    }

    cout << result << '\n';
}

int main() {
    input();
    solution();
    return 0;
}