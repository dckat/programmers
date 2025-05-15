#include <iostream>
#include <queue>
#include <vector>
using namespace std;

int n, m;
int a[50][50];
int d[50][50];
int dx[] = {-1, 1, 0, 0};
int dy[] = {0, 0, -1, 1};
vector<pair<int, int>> candi;
int ans = -1;

void init() {
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            d[i][j] = -1;
        }
    }
}

void bfs() {
    init();
    queue<pair<int, int>> q;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            if (a[i][j] == 3) {
                q.push({i, j});
                d[i][j] = 0;
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

            if (0 <= nx && nx < n && 0 <= ny && ny < n) {
                if (a[nx][ny] != 1 && d[nx][ny] == -1) {
                    d[nx][ny] = d[x][y] + 1;
                    q.push({nx, ny});
                }
            }
        }
    }
    
    int cur = 0;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            if (a[i][j] == 0) {
                if (d[i][j] == -1) {
                    return;
                }
                if (cur < d[i][j]) {
                    cur = d[i][j];
                }
            }
        }
    }

    if (ans == -1 || ans > cur) {
        ans = cur;
    }
}

void go(int index, int cnt) {
    if (index == candi.size()) {
        if (cnt == m) {
            bfs();
        }
    }
    else {
        int x = candi[index].first;
        int y = candi[index].second;
        a[x][y] = 3;
        go(index+1, cnt+1);
        a[x][y] = 2;
        go(index+1, cnt);
    }
}

int main() {
    cin >> n >> m;

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cin >> a[i][j];
            if (a[i][j] == 2) {
                candi.push_back({i, j});
            }
        }
    }
    go(0, 0);

    cout << ans << '\n';
    return 0;
}