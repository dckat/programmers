#include <iostream>
#include <vector>
#include <queue>
#include <stack>
using namespace std;

int dx[] = {-1, 1, 0, 0};
int dy[] = {0, 0, -1, 1};

bool bfs(vector<vector<int>>& a, int l, int r) {
    int n = a.size();
    vector<vector<bool>> visited(n, vector<bool>(n, false));
    bool ok = false;

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            if (!visited[i][j]) {
                int sum = a[i][j];
                queue<pair<int, int>> q;
                stack<pair<int, int>> s;
                q.push({i, j});
                s.push({i, j});
                visited[i][j] = true;

                while (!q.empty()) {
                    pair<int, int> p = q.front();
                    q.pop();

                    int x = p.first;
                    int y = p.second;

                    for (int k = 0; k < 4; k++) {
                        int nx = x + dx[k];
                        int ny = y + dy[k];

                        if (0 <= nx && nx < n && 0 <= ny && ny < n) {
                            if (!visited[nx][ny] && abs(a[x][y] - a[nx][ny]) >= l &&
                                abs(a[x][y] - a[nx][ny]) <= r) {
                                    q.push({nx, ny});
                                    s.push({nx, ny});
                                    sum += a[nx][ny];
                                    visited[nx][ny] = true;
                                    ok = true;
                            }
                        }
                    }
                }
                int avg = sum / s.size();
                while (!s.empty()) {
                    int x = s.top().first;
                    int y = s.top().second;
                    s.pop();
                    a[x][y] = avg;
                }
            }
        }
    }

    return ok;
}

int main() {
    int n, l, r;
    int answer = 0;
    cin >> n >> l >> r;

    vector<vector<int>> a(n, vector<int>(n));

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cin >> a[i][j];
        }
    }

    while (true) {
        if (bfs(a, l, r)) {
            answer++;
        }
        else {
            break;
        }
    }
    cout << answer << '\n';
    return 0;
}