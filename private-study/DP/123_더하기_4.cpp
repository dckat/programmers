#include <iostream>
#include <vector>
using namespace std;

int main() {
    cin.tie(NULL);
    cout.tie(NULL);
    ios_base::sync_with_stdio(false);

    int T;
    cin >> T;

    int m = 3;
    int nums[3] = {1, 2, 3};

    while (T--) {
        int n;
        cin >> n;

        vector<int> d(n+1);
        d[0] = 1;

        for (int j = 0; j < m; j++) {
            for (int i = 1; i <= n; i++) {
                if (i - nums[j] >= 0) {
                    d[i] += d[i-nums[j]];
                }
            }
        }
        cout << d[n] << '\n';
    }

    return 0;
}