#include <iostream>
#include <vector>
#include <queue>
#include <string>
using namespace std;

int dx[] = {-1, 1, 0, 0};
int dy[] = {0, 0, -1, 1};

bool can(bool blind, char a, char b) {
    if (a == b) {
        return true;
    }

    if (blind) {
        if (a == 'R' && b == 'G') {
            return true;
        }
        if (a == 'G' && b == 'R') {
            return true;
        }
    }
    return false;
}

int solution(vector<string>& a, bool blind=false) {
    int n = a.size();
    vector<vector<bool>> check(n, vector<bool>(n, false));
    int ans = 0;

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            if (check[i][j] == false) {
                ans++;
                queue<pair<int, int>> q;
                q.push({i, j});
                check[i][j] = true;

                while (!q.empty()) {
                    int x = q.front().first;
                    int y = q.front().second;
                    q.pop();

                    for (int k = 0; k < 4; k++) {
                        int nx = x + dx[k];
                        int ny = y + dy[k];

                        if (0 <= nx && nx < n && 0 <= ny && ny < n) {
                            if (check[nx][ny]) {
                                continue;
                            }
                            if (can(blind, a[x][y], a[nx][ny])) {
                                check[nx][ny] = true;
                                q.push({nx, ny});
                            }
                        }
                    }
                }
            }
        }
    }
    return ans;
}

int main() {
    int n;
    cin >> n;
    vector<string> a(n);

    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }
    cout << solution(a) << ' ' << solution(a, true) << '\n';

    return 0;
}