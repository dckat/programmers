#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int main() {
    int N, K;
    cin >> N >> K;

    vector<int> w(N+1);
    vector<int> v(N+1);
    vector<vector<int>> d(N+1, vector<int>(K+1, 0));

    for (int i = 1; i <= N; i++) {
        cin >> w[i] >> v[i];
    }

    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= K; j++) {
            d[i][j] = d[i-1][j];

            if (j-w[i] >= 0) {
                d[i][j] = max(d[i][j], d[i-1][j-w[i]] + v[i]);
            }
        }
    }
    cout << d[N][K] << '\n';
    return 0;
}