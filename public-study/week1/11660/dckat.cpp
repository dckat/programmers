#include <iostream>
#include <vector>
using namespace std;

int main() {
    // 입출력 속도 최적화
    ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

    int n, t;
    cin >> n >> t;

    vector<vector<int>> v(n+1, vector<int>(n+1));       // 입력값 저장 vector
    vector<vector<int>> dp(n+1, vector<int>(n+1, 0));   // dp를 위한 vector
    
    // 입력 후 2차원 dp에 저장
    for (int i = 1; i <= n; i++) {
        for(int j = 1; j <= n; j++) {
            cin >> v[i][j];
            dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + v[i][j];
        }
    }

    while (t--) {
        int x1, y1, x2, y2;
        cin >> x1 >> y1 >> x2 >> y2;

        int sum = dp[x2][y2] - dp[x2][y1-1] - dp[x1-1][y2] + dp[x1-1][y1-1];
        cout << sum << '\n';
    }
    return 0;
}