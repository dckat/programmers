#include <iostream>
#include <vector>
#include <string>
#include <tuple>
using namespace std;

int n, m;
bool check[100][100];

bool isBound(int x, int y, int k) {
    if (0 <= x-k && x+k < n && 0 <= y-k && y+k < m) {
        return true;
    }
    return false;
}

bool canMake(vector<string>& v, int x, int y, int k) {
    if (isBound(x, y, k)) {
        if (v[x-k][y] == '*' && v[x+k][y] == '*' && v[x][y-k] == '*' && v[x][y+k] == '*') {
            return true;
        }
    }
    return false;
}

void solution(vector<string>& v, int n, int m) {
    vector<tuple<int, int, int>> answer;

    // 십자가를 만들 수 있는지 확인
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            if (v[i][j] == '*'){
                int l = 0;
                for (int k = 1;;k++) {
                    if (canMake(v, i, j, k)) {
                        l = k;
                    } else {
                        break;
                    }
                }
                if (l > 0) {
                    answer.push_back({i+1, j+1, l});
                    check[i][j] = true;
                    for (int k = 1; k <= l; k++) {
                        check[i-k][j] = true;
                        check[i+k][j] = true;
                        check[i][j-k] = true;
                        check[i][j+k] = true;
                    }
                }
            }
        }
    }

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            if (v[i][j] == '*' && !check[i][j]) {
                cout << -1 << '\n';
                return;
            }
        }
    }

    cout << answer.size() << '\n';

    for (auto& p: answer) {
        int x, y, len;
        tie(x, y, len) = p;

        cout << x << ' ' << y << ' ' << len << '\n';
    }
}

int main() {

    cin >> n >> m;
    
    vector<string> v(n);
    for (int i = 0; i < n; i++) {
        cin >> v[i];
    }

    solution(v, n, m);
    return 0;
}