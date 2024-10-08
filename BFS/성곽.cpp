#include <iostream>
#include <queue>
using namespace std;

int n, m;
int a[50][50];
int d[50][50];
int room[2500];
int dx[] = {0, -1, 0, 1};
int dy[] = {-1, 0, 1, 0};

int bfs(int x, int y, int rooms) {
    queue<pair<int, int>> q;
    q.push({x, y});
    d[x][y] = rooms;
    int cnt = 0;

    while (!q.empty()) {
        x = q.front().first;
        y = q.front().second;
        q.pop();
        cnt++;

        for (int k = 0; k < 4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];

            if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                if (d[nx][ny] == 0 && !(a[x][y] & (1 << k))) {
                    q.push({nx, ny});
                    d[nx][ny] = rooms;
                }
            }
        }
    }

    return cnt;
}

int main() {
    cin >> m >> n;

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            cin >> a[i][j];
        }
    }
    int rooms = 0;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            if (d[i][j] == 0) {
                rooms++;
                room[rooms] = bfs(i, j, rooms);
            }
        }
    }
    cout << rooms << '\n';

    int ans = 0;
    for (int i = 1; i <= rooms; i++) {
        if (ans < room[i]) {
            ans = room[i];
        }
    }
    cout << ans << '\n';

    ans = 0;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            int x = i;
            int y = j;

            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }
                if (d[nx][ny] == d[x][y]) {
                    continue;
                }
                if (a[x][y] & (1 << k)) {
                    if (ans < room[d[x][y]] + room[d[nx][ny]]) {
                        ans = room[d[x][y]] + room[d[nx][ny]];
                    }
                }
            }
        }
    }
    cout << ans << '\n';
    return 0;
}