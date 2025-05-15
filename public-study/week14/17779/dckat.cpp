#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;

int n;
int dx[] = {-1, 1, 0, 0};
int dy[] = {0, 0, -1, 1};
int board[20][20];
int area[20][20];

// 입력
void input() {
    cin >> n;

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cin >> board[i][j];
        }
    }

}

// 구역 초기화 (0으로)
void initArea() {
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            area[i][j] = 0;
        }
    }
}

// 이동가능 여부 확인 (영역을 벗어났는가? + 구역설정이 되었는가?)
bool canMove(int x, int y) {
    if (0 <= x && x < n && 0 <= y && y < n && area[x][y] == 0) {
        return true;
    }
    return false;
}

// bfs 탐색으로 영역 설정 (x.y: 좌표, num: 구역번호)
void bfs(int x, int y, int num) {
    area[x][y] = num;
    queue<pair<int, int>> q;
    q.push({x, y});

    while (!q.empty()) {
        tie(x, y) = q.front();
        q.pop();

        for (int k = 0; k < 4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];

            if (canMove(nx, ny)) {
                area[nx][ny] = num;
                q.push({nx, ny});
            }
        }
    }
}

// 구역 설정 후 차이의 값 계산
int calc(int x, int y, int d1, int d2) {
    initArea();

    // 5번 구역
    for (int i = 0; i <= d1; i++) {
        area[x+i][y-i] = 5;
        area[x+d2+i][y+d2-i] = 5;
    }
    for (int i = 0; i <= d2; i++) {
        area[x+i][y+i] = 5;
        area[x+d1+i][y-d1+i] = 5;
    }
    
    // 1번 구역
    for (int i = 0; i < x; i++) {
        area[i][y] = 1;
    }

    // 2번 구역
    for (int j = y+d2+1; j < n; j++) {
        area[x+d2][j] = 2;
    }

    // 3번 구역
    for (int j = 0; j < y-d1; j++) {
        area[x+d1][j] = 3;
    }

    // 4번 구역
    for (int i = x+d1+d2+1; i < n; i++) {
        area[i][y-d1+d2] = 4;
    }
    
    // 1번 ~ 4번구역 설정 (bfs 활용)
    bfs(0, 0, 1);
    bfs(0, n-1, 2);
    bfs(n-1, 0, 3);
    bfs(n-1, n-1, 4);

    // 최대 ~ 최소 차이 값 구하기
    vector<int> cnt(5, 0);
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            if (area[i][j] == 0) {
                area[i][j] = 5;
            }
            cnt[area[i][j] - 1] += board[i][j];
        }
    }
    auto p = minmax_element(cnt.begin(), cnt.end());
    int result = *p.second - *p.first;
    return result;
}

void solution() {
    int ans = -1;

    // x, y, d1, d2을 설정하여 최솟값 구하기
    for (int x = 0; x < n; x++) {
        for (int y = 0; y < n; y++) {
            for (int d1 = 1; d1 < n; d1++) {
                for (int d2 = 1; d2 < n; d2++) {
                    if (0 <= y-d1 && y+d2 < n) {
                        if (x+d1+d2 < n) {
                            int tmp = calc(x, y, d1, d2);
                            if (ans == -1 || ans > tmp) {
                                ans = tmp;
                            }
                        }
                    }
                }
            }
        }
    }

    cout << ans << '\n';
}

int main() {
    input();
    solution();
    return 0;
}