#include <iostream>
#include <vector>
#include <cmath>
using namespace std;

int c, n;
vector<pair<int, int>> cities(20);
int dp[1101];    // C명 늘릴때 드는 비용의 최솟값

void input() {
    cin >> c >> n;

    for (int i = 0; i < n; i++) {
        cin >> cities[i].first >> cities[i].second;
    }
}

void solution() {
    for (int i = 0; i < n; i++) {
        int cost = cities[i].first;
        int people = cities[i].second;
        for (int j = 1; j <= c + 100; j++) {
            for (int k = 1;;k++) {
                int tmp = 0;

                // index 초과
                if (j + k*people > 1100) {
                    break;
                }
                // 단위 비용 계산
                if (j-k*people >= 0) {
                    tmp = dp[j-k*people];
                }

                // 최솟값 갱신
                if (dp[j] != 0) {
                    dp[j] = min(dp[j], tmp + k*cost);
                }
                else {
                    dp[j] = tmp + k*cost;
                    break;
                }
            }
        }
    }

    // C명 이상이므로 이 이후에 더 큰 값이 나올수 있음
    int result = dp[c];
    for (int i = c + 1; i <= c+100; i++) {
        if (dp[i] != 0 && result > dp[i]) {
            result = dp[i];
        }
    }
    cout << result << '\n';
}

int main() {
    input();
    solution();
    return 0;
}