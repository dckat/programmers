#include <iostream>
#include <queue>
using namespace std;

char a[100][100];
bool check[100][100];

int dx[] = {-1, 1, 0, 0};
int dy[] = {0, 0, -1, 1};
int n, m;

int bfs(int x, int y, char c) {
    int cnt = 1;
    queue<pair<int, int>> q;
    q.push({x, y});
    check[x][y] = true;

    while (!q.empty()) {
        pair<int, int> p = q.front();
        q.pop();

        for (int k = 0; k < 4; k++) {
            int nx = p.first + dx[k];
            int ny = p.second + dy[k];

            if (0 <= nx && nx < m && 0 <= ny && ny < n) {
                if (a[nx][ny] == c && !check[nx][ny]) {
                    q.push({nx, ny});
                    check[nx][ny] = true;
                    cnt++;
                }
            }
        }
    }

    return cnt*cnt;
}

int main() {
    int sum1 = 0;
    int sum2 = 0;
    cin >> n >> m;

    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            cin >> a[i][j];
        }
    }

    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            if (!check[i][j] && a[i][j] == 'W') {
                sum1 += bfs(i, j, 'W');
            }
            else if (!check[i][j] && a[i][j] == 'B') {
                sum2 += bfs(i, j, 'B');
            }
        }
    }
    cout << sum1 << ' ' << sum2 << '\n';
    return 0;
}