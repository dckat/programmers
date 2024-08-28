#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int main() {
    int ans = -1;
    int N, S, M;
    cin >> N >> S >> M;

    vector<int> a(N+1);
    vector<vector<int>> d(N+1, vector<int>(M+1, 0));

    for (int i = 1; i <= N; i++) {
        cin >> a[i];
    }

    if (S + a[1] <= M) {
        d[1][S+a[1]] = 1;
    }
    if (S - a[1] >= 0) {
        d[1][S-a[1]] = 1;
    }

    for (int i = 2; i <= N; i++) {
        for (int j = 0; j <= M; j++) {
            if (d[i-1][j] == 1) {
                if (j + a[i] <= M) {
                    d[i][j+a[i]] = 1;
                }
                if (j - a[i] >= 0) {
                    d[i][j-a[i]] = 1;
                }
            }
        }
    }


    for (int i = 0; i <= M; i++) {
        if (d[N][i] == 1) {
            ans = i;
        }
    }

    cout << ans << '\n';
    return 0;
}