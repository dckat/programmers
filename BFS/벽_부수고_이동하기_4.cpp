#include <iostream>
#include <queue>
#include <cstdio>
#include <set>
using namespace std;

int n, m;
int a[1000][1000];
int group[1000][1000];
bool check[1000][1000];
int dx[] = {-1, 1, 0, 0};
int dy[] = {0, 0, -1, 1};
vector<int> group_size;

void bfs(int x, int y) {
    int group_num = group_size.size();
    queue<pair<int, int>> q;
    q.push({x, y});
    check[x][y] = true;
    group[x][y] = group_num;
    int cnt = 1;

    while (!q.empty()) {
        pair<int, int> p = q.front();
        q.pop();

        for (int k = 0; k < 4; k++) {
            int nx = p.first + dx[k];
            int ny = p.second + dy[k];

            if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                if (a[nx][ny] == 0 && !check[nx][ny]) {
                    check[nx][ny] = true;
                    group[nx][ny] = group_num;
                    q.push({nx, ny});
                    cnt++;
                }
            }
        }
    }
    group_size.push_back(cnt);
}

int main() {
    cin >> n >> m;

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            scanf("%1d", &a[i][j]);
        }
    }

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            if (a[i][j] == 0 && !check[i][j]) {
                bfs(i, j);
            }
        }
    }

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            if (a[i][j] == 1) {
                int cnt = 1;
                set<int> group_set;
                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                        if (a[nx][ny] == 0) {
                            group_set.insert(group[nx][ny]);
                        }
                    }
                }

                for (int num: group_set) {
                    cnt += group_size[num];
                }
                cout << cnt%10;
            }
            else {
                cout << a[i][j];
            }
        }
        cout << '\n';
    }

    return 0;
}