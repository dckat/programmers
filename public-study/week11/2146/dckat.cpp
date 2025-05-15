#include <iostream>
#include <vector>
#include <queue>
#include <tuple>
#include <cstring>
using namespace std;

int n;
int arr[100][100];
int group[100][100];
bool visited[100][100];
int dx[] = {-1, 1, 0, 0};
int dy[] = {0, 0, -1, 1};

// 범위 파악
bool canMove(int x, int y) {
    if (0 <= x && x < n && 0 <= y && y < n) {
        return true;
    }
    return false;
}

// 입력
void input() {
    cin >> n;

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cin >> arr[i][j];
        }
    }
}

// 같은 그룹 번호 붙이기
void setGroup(int x, int y, int num) {
    queue<pair<int, int>> q;
    q.push({x, y});
    group[x][y] = num;

    while (!q.empty()) {
        int cx, cy;
        tie(cx, cy) = q.front();
        q.pop();

        for (int k = 0; k < 4; k++) {
            int nx = cx + dx[k];
            int ny = cy + dy[k];

            /* 같은 그룹 번호 붙일수 있는 기준
               1. 범위 내에 있는지
               2. 현재 칸이 1인지
               3. 현재 칸이 그룹이 맺어지지 않았는지
            */
            if (canMove(nx, ny) && arr[nx][ny] == 1 && group[nx][ny] == 0) {
                q.push({nx, ny});
                group[nx][ny] = num;
            }
        }
    }
}

// 만들어진 그룹에서 다리 놓기
int bfs(int num) {
    queue<tuple<int, int, int>> q;

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            if (group[i][j] == num) {
                q.push({i, j, 0});
                visited[i][j] = true;
            }
        }
    }

    while (!q.empty()) {
        int x, y, cnt;
        tie(x, y, cnt) = q.front();
        q.pop();

        for (int k = 0; k < 4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];
            int nCnt = cnt + 1;

            // 범위 검사 확인 (공통)
            if (canMove(nx, ny)) {
                // 다른 섬에 도달한 경우 (최단 거리)
                // 다리는 빈 부분만 놓으므로 1을 뺀 cnt 반환
                if (group[nx][ny] != 0 && group[nx][ny] != num) {
                    return cnt;
                }

                /* 다리를 놓을 수 있는 경우
                   1. 현재 칸이 0 이어야 함
                   2. 아직 방문하지 않음
                */
                if (group[nx][ny] == 0 && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    q.push({nx, ny, nCnt});
                }
            }
        }
    }
}

void solution() {
    // 그룹 만들기
    int num = 0;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            if (arr[i][j] == 1 && group[i][j] == 0) {
                num++;
                setGroup(i, j, num);
            }
        }
    }

    // 그룹 간 최단 거리 구하기
    int result = 20000;
    for (int i = 1; i <= num; i++) {
        int ans = bfs(i);

        if (result > ans) {
            result = ans;
        }

        // 최단거리 계산을 여러번 수행하므로 방문배열의 false 초기화
        memset(visited, false, sizeof(visited));
    }

    cout << result << '\n';
}

int main() {
    input();
    solution();
    return 0;
}