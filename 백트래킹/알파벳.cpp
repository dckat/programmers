#include <iostream>
using namespace std;

char arr[20][20];
int dx[] = {-1, 1, 0, 0};
int dy[] = {0, 0, -1, 1};
bool visited[26];
int r, c;
int ans = 0;

bool canMove(int x, int y) {
    if (0 <= x && x < r && 0 <= y && y < c) {
        return true;
    }
    return false;
}

void dfs(int x, int y, int cnt) {
    bool canGo = false;
    for (int k = 0; k < 4; k++) {
        int nx = x + dx[k];
        int ny = y + dy[k];

        if (canMove(nx, ny)) {
            if (!visited[arr[nx][ny] - 'A']) {
                canGo = true;
                visited[arr[nx][ny] - 'A'] = true;
                dfs(nx, ny, cnt+1);
                visited[arr[nx][ny] - 'A'] = false;
            }
        }
    }

    if (!canGo) {
        if (ans < cnt) {
            ans = cnt;
            return;
        }
    }
}

int main() {
    cin >> r >> c;

    for (int i = 0; i < r; i++) {
        for (int j = 0; j < c; j++) {
            cin >> arr[i][j];
        }
    }
    visited[arr[0][0] - 'A'] = true;
    dfs(0, 0, 1);

    cout << ans << '\n';
    return 0;
}