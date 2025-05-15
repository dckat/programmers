#include <iostream>
#include <cmath>
using namespace std;

int dx[] = {-1, 1, 0, 0};
int dy[] = {0, 0, -1, 1};
int v[501][501];        // 입력 배열
int d[501][501];        // 경로 저장 배열 (dp)
int m, n;               // 가로. 세로 크기

// 범위 확인
bool canMove(int& x, int& y) {
    if (1 <= x && x <= m && 1 <= y && y <= n) {
        return true;
    }
    return false;
}

int dfs(int x, int y) {
    if (x == m && y == n) {
        return 1;
    }

    if (d[x][y] == -1) {
        d[x][y] = 0;
        for (int k = 0; k < 4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];

            if (canMove(nx, ny)) {
                if (v[x][y] > v[nx][ny]) {
                    d[x][y] += dfs(nx, ny);
                }
            }
        }
    }
    return d[x][y];
}


int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> m >> n;

    for (int i = 1; i <= m; i++) {
        for (int j = 1; j <= n; j++) {
            cin >> v[i][j];
            d[i][j] = -1;
        }
    }

    cout << dfs(1, 1) << '\n';
    return 0;
}