#include <iostream>
#include <vector>
using namespace std;

long long d[101][21];

int main() {
    int n;
    cin >> n;

    vector<int> a(n+1);

    for (int i = 1; i <= n; i++) {
        cin >> a[i];
    }

    d[1][a[1]] = 1;

    for (int i = 2; i <= n; i++) {
        for (int j = 0; j <= 20; j++) {
            if (d[i-1][j] != 0) {
                if (j+a[i] <= 20) {
                    d[i][j+a[i]] += d[i-1][j];
                }
                if (j-a[i] >= 0) {
                    d[i][j-a[i]] += d[i-1][j];
                }
            }
        }
    }   

    cout << d[n-1][a[n]] << '\n';
    return 0;
}