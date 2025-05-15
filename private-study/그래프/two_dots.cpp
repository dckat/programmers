#include <iostream>
using namespace std;

int n, m;
bool check[50][50];
char a[50][50];
int dist[50][50];
int dx[] = {0, 0, 1, -1};
int dy[] = {1, -1, 0, 0};

void initCheck() {
    for (int i = 0; i < 50; i++) {
        for (int j = 0; j < 50; j++) {
            check[i][j] = false;
        }
    }
}

bool go(int x, int y, int cnt, char color) {
    if (check[x][y]) {
        return cnt - dist[x][y] >= 4;
    }

    check[x][y] = true;
    dist[x][y] = cnt;

    for (int i = 0; i < 4; i++) {
        int nx = x + dx[i];
        int ny = y + dy[i];

        if (0 <= nx && nx < n && 0 <= ny && ny < m){
            if (a[nx][ny] == color) {
                if (go(nx, ny, cnt+1, color)) {
                    return true;
                }
            }
        }
    }
    return false;
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
            initCheck();
            if (go(i, j, 0, a[i][j])) {
                cout << "Yes" << '\n';
                return 0;
            }
        }
    }
    cout << "No" << '\n';
    return 0;
}