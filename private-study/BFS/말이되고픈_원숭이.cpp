#include <iostream>
#include <tuple>
#include <queue>
using namespace std;

int dx[] = {0, 0, -1, 1, -1, 1, 2, -2, -2, 2, -1, 1};
int dy[] = {-1, 1, 0, 0, -2, -2, -1, -1, 1, 1, 2, 2};
int cost[] = {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1};
int a[200][200];
int d[200][200][31];

void init(int n, int m, int l) {
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            for (int k = 0; k <= l; k++) {
                d[i][j][k] = -1;
            }
        }
    }
}

int main() {
    int l, n, m;
    cin >> l;

    cin >> m >> n;

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            cin >> a[i][j];
        }
    }
    init(n, m, l);

    queue<tuple<int, int, int>> q;
    q.push(make_tuple(0, 0, 0));
    d[0][0][0] = 0;

    while (!q.empty()) {
        int x, y, c;
        tie(x, y, c) = q.front();
        q.pop();

        for (int k = 0; k < 12; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];
            int nc = c + cost[k];

            if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                if (a[nx][ny] == 1) {
                    continue;
                }
                if (nc <= l) {
                    if (d[nx][ny][nc] == -1) {
                        d[nx][ny][nc] = d[x][y][c] + 1;
                        q.push(make_tuple(nx, ny, nc));
                    }
                }
            }
        }
    }

    int ans = -1;

    for (int i = 0; i <= l; i++) {
        if (d[n-1][m-1][i] != -1) {
            if (ans == -1) {
                ans = d[n-1][m-1][i];
            }
            else if (ans > d[n-1][m-1][i]) {
                ans = d[n-1][m-1][i];
            }
        }
    }
    cout << ans << '\n';
    return 0;
}