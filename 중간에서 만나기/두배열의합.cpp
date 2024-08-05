#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int main() {
    int T, n, m;
    cin >> T;

    cin >> n;
    vector<int> a(n);
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }

    cin >> m;
    vector<int> b(m);
    for (int i = 0; i < m; i++) {
        cin >> b[i];
    }
    
    vector<int> sumA;
    for (int i = 0; i < n; i++) {
        int sum = 0;
        for (int j = i; j < n; j++) {
            sum += a[j];
            sumA.push_back(sum);
        }
    }

    vector<int> sumB;
    for (int i = 0; i < m; i++) {
        int sum = 0;
        for (int j = i; j < m; j++) {
            sum += b[j];
            sumB.push_back(sum);
        }
    }
    sort(sumA.begin(), sumA.end());
    sort(sumB.begin(), sumB.end());
    long long ans = 0;

    for (int i = 0; i < sumA.size(); i++) {
        auto p = equal_range(sumB.begin(), sumB.end(), T-sumA[i]);
        ans += p.second - p.first;
    }


    cout << ans << '\n';
    return 0;
}