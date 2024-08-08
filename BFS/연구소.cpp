#include <iostream>
#include <queue>
using namespace std;

int ans = 0;
int n, m;
int a[8][8];
int b[8][8];
int dx[] = {-1, 1, 0, 0};
int dy[] = {0, 0, -1, 1};

void bfs() {
    queue<pair<int, int>> q;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            b[i][j] = a[i][j];
            if (b[i][j] == 2) {
                q.push(make_pair(i, j));
            }
        }
    }

    while (!q.empty()) {
        int x = q.front().first;
        int y = q.front().second;
        q.pop();

        for (int k = 0; k < 4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];

            if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                if (b[nx][ny] == 0) {
                    b[nx][ny] = 2;
                    q.push(make_pair(nx, ny));
                }
            }
        }
    }

    int cnt = 0;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            if (b[i][j] == 0) {
                cnt++;
            }
        }
    }

    if (ans < cnt) {
        ans = cnt;
    }
}

void solution(int num) {
    if (num == 3) {
        bfs();
        return;
    }

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            if (a[i][j] == 0) {
                a[i][j] = 1;
                solution(num+1);
                a[i][j] = 0;
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

    solution(0);
    cout << ans << '\n';
    return 0;
}