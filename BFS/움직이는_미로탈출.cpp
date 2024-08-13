#include <iostream>
#include <vector>
#include <queue>
#include <tuple>
using namespace std;

bool check[8][8][9];
int dx[] = {0, 0, -1, 1, 1, -1, -1, 1, 0};
int dy[] = {-1, 1, 0, 0, -1, -1, 1, 1, 0};

int main() {
    int n = 8;
    vector<string> a(n);

    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }

    queue<tuple<int, int, int>> q;
    check[7][0][0] = true;
    q.push(make_tuple(7, 0, 0));

    bool ans = false;

    while (!q.empty()) {
        int x, y, t;
        tie(x, y, t) = q.front();
        q.pop();

        for (int i = 0; i < 9; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            int nt = min(t+1, 8);

            if (x == 0 && y == 7) {
                ans = true;
                break;
            }

            if (0 <= nx && nx < 8 && 0 <= ny && ny < 8) {
                if (nx-t >= 0 && a[nx-t][ny] == '#') {
                    continue;
                }
                if (nx-t-1 >= 0 && a[nx-t-1][ny] == '#') {
                    continue;
                }
                if (check[nx][ny][nt] == false) {
                    check[nx][ny][nt] = true;
                    q.push(make_tuple(nx, ny, nt));
                }
            }
        }
    }

    cout << (ans ? 1 : 0) << '\n';
    return 0;
}