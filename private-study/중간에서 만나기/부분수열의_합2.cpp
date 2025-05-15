#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int main() {
    int N, S;
    cin >> N >> S;

    vector<int> arr(N);

    for (int i = 0; i < N; i++) {
        cin >> arr[i];
    }

    int n = N/2;
    int m = N - n;

    vector<int> a(1 << n);
    vector<int> b(1 << m);

    for (int i = 0; i < (1 << n); i++) {
        for (int k = 0; k < n; k++) {
            if (i & (1 << k)) {
                a[i] += arr[k];
            }
        }
    }

    for (int i = 0; i < (1 << m); i++) {
        for (int k = 0; k < m; k++) {
            if (i & (1 << k)) {
                b[i] += arr[k+n];
            }
        }
    }
    sort(a.begin(), a.end());
    sort(b.begin(), b.end());
    reverse(b.begin(), b.end());

    n = (1 << n);
    m = (1 << m);
    int i = 0;
    int j = 0;
    long long ans = 0;

    while (i < n && j < m) {
        if (a[i] + b[j] == S) {
            long long c1 = 1;
            long long c2 = 1;
            i++;
            j++;

            while (i < n && a[i] == a[i-1]) {
                c1++;
                i++;
            }
            while (j < m && b[j] == b[j-1]) {
                c2++;
                j++;
            }
            ans += c1 * c2;
        }
        else if (a[i] + b[j] > S) {
            j++;
        }
        else  {
            i++;
        }
    }
    if (S == 0) {
        ans--;
    }
    cout << ans << '\n';
    return 0;
}