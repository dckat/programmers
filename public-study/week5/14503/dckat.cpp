#include <iostream>
#include <queue>
#include <tuple>
using namespace std;

int a[50][50];
bool check[50][50];
int dx[] = {-1, 0, 1, 0};
int dy[] = {0, 1, 0, -1};
int n, m;

// 범위를 벗어났는지 여부 확인
bool checkBoard(int x, int y) {
    if (0 <= x && x < n && 0 <= y && y < m) {
        return true;
    }
    return false;
}

int bfs(int r, int c, int d) {
    int answer = 0;
    queue<tuple<int, int, int>> q;
    q.push({r, c, d});

    while (!q.empty()) {
        bool flag = false;
        int x, y, z;
        tie(x, y, z) = q.front();
        q.pop();

        if (!check[x][y]) {
            answer++;
            check[x][y] = true;
        }

        // 현재 칸에서 주변 4칸과 방향전환
        int nz = z;
        for (int k = 0; k < 4; k++) {
            if (nz == 0) {
                nz = 3;
            }
            else {
                nz--;
            }
            int nx = x + dx[nz];
            int ny = y + dy[nz];

            // 청소할 칸이 있다면
            if (checkBoard(nx, ny)) {
                if (a[nx][ny] == 0 && !check[nx][ny]) {
                    q.push({nx, ny, nz});
                    flag = true;
                    break;
                }
            }
        }
        // 청소할 칸이 존재하지 않음 (3번)
        if (!flag) {
            int nz = (z + 2) % 4;
            int bx = x + dx[nz];
            int by = y + dy[nz];
            if (checkBoard(bx, by)) {
                if (a[bx][by] == 1) {
                    return answer;
                }
                else {
                    q.push({bx, by, z});
                    continue;
                }
            }
        }
    }
    return answer;
}

int main() {
    int r, c, d;
    cin >> n >> m;
    cin >> r >> c >> d;

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            cin >> a[i][j];
        }
    }
    int ans = bfs(r, c, d);
    cout << ans << '\n';
    return 0;
}