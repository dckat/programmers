#include <iostream>
#include <queue>
#include <tuple>
using namespace std;

int n, m;
int dx[] = {0, 0, -1, 1, -1, 1, -1, 1};
int dy[] = {-1, 1, 0, 0, -1, -1, 1, 1};
int a[50][50];
int d[50][50];
bool check[50][50];

void initCheck() {
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            check[i][j] = false;
        }
    }
}

void bfs(int r, int c) {
    initCheck();
    queue<tuple<int, int, int>> q;
    q.push(make_tuple(r, c, 0));
    check[r][c] = true;

    while (!q.empty()) {
        int x, y, dist;
        tie(x, y, dist) = q.front();
        q.pop();

        if (a[x][y] == 1) {
            d[r][c] = dist;
            return;
        }

        for (int k = 0; k < 8; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];
            int ndist = dist + 1;

            if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                if (!check[nx][ny]) {
                    check[nx][ny] = true;
                    q.push(make_tuple(nx, ny, ndist));
                }
            }
        }
    }
}

int main() {
    cin >> n >> m;

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            cin >> a[i][j];
        }
    }

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            if (a[i][j] == 0) {
                bfs(i, j);
            }
        }
    }

    int ans = -1;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            if (a[i][j] == 1) {
                continue;
            }
            if (ans < d[i][j]) {
                ans = d[i][j];
            }
        }
    }
    cout << ans << '\n';
    return 0;
}