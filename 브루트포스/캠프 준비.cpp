#include <iostream>
using namespace std;

int n, l, r, x;
int a[15];
bool check[15];

void input() {
    cin >> n >> l >> r >> x;

    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }
}

int go(int index) {
    if (index == n) {
        int cnt = 0;
        int sum = 0;
        int min = 1000000;
        int max = 0;

        for (int i = 0; i < n; i++) {
            if (check[i]) {
                cnt++;
                sum += a[i];

                if (min > a[i]) {
                    min = a[i];
                }
                if (max < a[i]) {
                    max = a[i];
                }
            }
        }
        
        if (cnt >= 2 && l <= sum && sum <= r && max-min >= x) {
            return 1;
        }
        else {
            return 0;
        }
    }

    check[index] = true;
    int cnt1 = go(index+1);
    check[index] = false;
    int cnt2 = go(index+1);

    return cnt1 + cnt2;
}

void solution() {
    int answer = go(0);
    cout << answer << '\n';
}

int main() {
    input();
    solution();
    return 0;
}