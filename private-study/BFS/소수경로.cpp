#include <iostream>
#include <queue>
using namespace std;

bool prime[10001];
bool check[10001];
int d[10001];

void init() {
    for (int i = 0; i <= 10000; i++) {
        check[i] = false;
        d[i] = -1;
    }
}

int change(int num, int index, int digit) {
    if (index == 0 && digit == 0) {
        return -1;
    }
    string s = to_string(num);
    s[index] = digit + '0';
    return stoi(s);
}

int main() {
    for (int i = 2; i <= 10000; i++) {
        if (!prime[i]) {
            for (int j = i*i; j <= 10000; j+=i) {
                prime[j] = true;
            }
        }
    }

    for (int i = 0; i <= 10000; i++) {
        prime[i] = !prime[i];
    }

    int T;
    cin >> T;

    while (T--) {
        int n, m;
        cin >> n >> m;
        init();

        d[n] = 0;
        check[n] = true;
        queue<int> q;
        q.push(n);

        while (!q.empty()) {
            int num = q.front();
            q.pop();

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j <= 9; j++) {
                    int next = change(num, i, j);
                    if (next != -1) {
                        if (prime[next] && !check[next]) {
                            check[next] = true;
                            d[next] = d[num] + 1;
                            q.push(next);
                        }
                    }
                }
            }
        }
        if (d[m] != -1) {
            cout << d[m] << '\n';
        }
        else {
            cout << "Impossible" << '\n';
        }
    }
    return 0;
}