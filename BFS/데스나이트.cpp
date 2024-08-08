#include <iostream>
#include <queue>
using namespace std;

int n;
int r1, c1, r2, c2;
int dx[] = {-2, -2, 0, 0, 2, 2};
int dy[] = {-1, 1, -2, 2, -1, 1};
bool visited[200][200];

struct Chess {
    int x;
    int y;
    int cnt;
};

int bfs() {
    int cnt = 0;
    queue<Chess> q;
    q.push({r1, c1, 0});
    visited[r1][c1] = true;

    while (!q.empty()) {
        Chess c = q.front();
        q.pop();

        int x = c.x;
        int y = c.y;
        int cnt = c.cnt;

        if (x == r2 && y == c2) {
            return cnt;
        }

        for (int k = 0; k < 6; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];

            if (0 <= nx && nx < n && 0 <= ny && ny < n) {
                if (!visited[nx][ny]) {
                    q.push({nx, ny, cnt+1});
                    visited[nx][ny] = true;
                }
            }
        }
    }
    return -1;
}

int main() {
    cin >> n;

    cin >> r1 >> c1 >> r2 >> c2;

    cout << bfs() << '\n';
    return 0;
}