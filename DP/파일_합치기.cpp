#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

#define INF 1000000000

int main() {
    cin.tie(0);
    cout.tie(0);
    ios_base::sync_with_stdio(0);

    int T;
    cin >> T;

    while (T--) {
        int K;
        cin >> K;

        vector<int> a(K+1);
        vector<int> sum(K+1, 0);
        vector<vector<int>> d(K+1, vector<int>(K+1, 0));
    
        for (int i = 1; i <= K; i++) {
            cin >> a[i];
            sum[i] = sum[i-1] + a[i];
        }

        for (int i = 1; i <= K; i++) {
            for (int j = 1; j <= K - i; j++) {
                d[j][i+j] = INF;
                for (int k = j; k < i + j; k++) {
                    d[j][i+j] = min(d[j][i+j], d[j][k] + d[k+1][i+j] + sum[i+j] - sum[j-1]);
                }
            }
        }

        cout << d[1][K] << '\n';
    }
    return 0;
}