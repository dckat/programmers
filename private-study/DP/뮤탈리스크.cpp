#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int d[61][61][61];

int solution(int i, int j, int k) {
    if (i < 0) {
        return solution(0, j, k);
    }
    if (j < 0) {
        return solution(i, 0, k);
    }
    if (k < 0) {
        return solution(i, j, 0);
    }
    if (i == 0 && j == 0 && k == 0) {
        return 0;
    }

    int& ans = d[i][j][k];
    if (ans != -1) {
        return ans;
    }
    ans = 10000000;
    vector<int> p = {1, 3, 9};

    do {
        if (ans > solution(i-p[0], j-p[1], k-p[2])) {
            ans = solution(i-p[0], j-p[1], k-p[2]);
        }
    } while (next_permutation(p.begin(), p.end()));
    ans++;
    return ans;
}

void init() {
    for (int i = 0; i < 61; i++) {
        for (int j = 0; j < 61; j++) {
            for (int k = 0; k < 61; k++) {
                d[i][j][k] = -1;
            }
        }
    }
}

int main() {
    int n;
    int scv[3] = {0, 0, 0};

    cin >> n;

    for (int i = 0; i < n; i++) {
        cin >> scv[i];
    }
    init();
    cout << solution(scv[0], scv[1], scv[2]) << '\n';

    return 0;
}