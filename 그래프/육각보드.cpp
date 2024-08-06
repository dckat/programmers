#include <iostream>
#include <string>
#include <vector>
using namespace std;

int n;
int ans;
int dx[] = {-1, 0, 1, 1, 0, -1};
int dy[] = {0, -1, -1, 0, 1, 1};
int color[50][50];
char a[50][50];

void dfs(int x, int y, int c) {
    color[x][y] = c;
    ans = max(ans, 1);

    for (int k = 0; k < 6; k++) {
        int nx = x + dx[k];
        int ny = y + dy[k];
        if (0 <= nx && nx < n && 0 <= ny && ny < n) {
            if (a[nx][ny] == 'X') {
                if (color[nx][ny] == -1) {
                    dfs(nx, ny, 1-c);
                    ans = max(ans, 2);
                }
            }
            if (color[nx][ny] == c) {
                ans = max(ans, 3);
            }
        }
    }
}

void initColor(int n) {
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            color[i][j] = -1;
        }
    }
}

int main() {
    cin >> n;

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cin >> a[i][j];
        }
    }
    initColor(n);

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            if (a[i][j] == 'X' && color[i][j] == -1) {
                dfs(i, j, 0);
            }
        }
    }
    cout << ans << '\n';
    return 0;
}