#include <iostream>
#include <vector>
#include <tuple>
#include <algorithm>
using namespace std;

int board[20][20];              // 격자 정보
int boardNext[20][20];          // 다음 격자 정보 (임시 저장)
int priority[401][4][4];        // 상어 번호별 이동방향 우선순위
int dir[401];                   // 상어의 이동방향
int smell[20][20];              // 냄새의 남은 시간
int smellWho[20][20];           // 냄새-상어 정보 매핑
int dx[] = {-1, 1, 0, 0};
int dy[] = {0, 0, -1, 1};
int N, M, K;
int out = 0;

void input() {
    cin >> N >> M >> K;

    // 격자 입력
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            cin >> board[i][j];

            // 해당 격자에 상어가 존재 -> 냄새정보 저장
            if (board[i][j] > 0) {
                smell[i][j] = K;
                smellWho[i][j] = board[i][j];
            }
        }
    }

    // 상어 방향
    for (int i = 1; i <= M; i++) {
        cin >> dir[i];
        dir[i]--;
    }

    // 상어의 방향 우선순위
    for (int i = 1; i <= M; i++) {
        for (int j = 0; j < 4; j++) {
            for (int k = 0; k < 4; k++) {
                cin >> priority[i][j][k];
                priority[i][j][k]--;
            }
        }
    }
}

// 1마리만 남았는지 확인
bool check() {
    int cnt = 0;

    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            if (board[i][j] > 0) {
                cnt++;
            }
        }
    }
    return cnt == 1;
}

// 범위 판별
bool isBound(int x, int y) {
    if (0 <= x && x < N && 0 <= y && y < N) {
        return true;
    }
    return false;
}

// 냄새가 없는 칸 탐색
bool canMove(int x, int y) {
    if (isBound(x, y) && smell[x][y] == 0) {
        return true;
    }
    return false;
}

// 자신의 냄새가 존재하는지 탐색
bool canMove2(int x, int y, int no) {
    if (isBound(x, y) && (smell[x][y] > 0 && smellWho[x][y] == no)) {
        return true;
    }
    return false;
}

// 상어의 이동
void move() {
    vector<tuple<int, int, int>> v;

    // 현재 격자의 상어 정보 저장
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            boardNext[i][j] = 0;
            if (board[i][j] > 0) {
                v.push_back({board[i][j], i, j});
            }
        }
    }

    for (int i = 0; i < v.size(); i++) {
        int no, x, y;
        tie(no, x, y) = v[i];

        int sharkDir = dir[no];
        bool ok = false;            // 냄새가 존재하지 않는 칸 판별

        // 아무 냄새가 없는 칸 탐색 후 존재하면 이동 (여러 개면 우선 순위에 따름)
        // 현재 상어의 방향을 기준으로 우선순위 탐색
        for (int k = 0; k < 4; k++) {
            int nx = x + dx[priority[no][sharkDir][k]];
            int ny = y + dy[priority[no][sharkDir][k]];

            // 냄새 없는 칸 발견
            if (canMove(nx, ny)) {
                // 빈 공간에 상어가 들어온 경우 (추방 X)
                if (boardNext[nx][ny] == 0) {
                    boardNext[nx][ny] = no;
                    dir[no] = priority[no][sharkDir][k];
                }
                // 번호가 더 작은 상어가 온 경우 (추방)
                else if (boardNext[nx][ny] > no) {
                    boardNext[nx][ny] = no;
                    dir[no] = priority[no][sharkDir][k];
                    out++;
                }
                // 이동칸에 이미 번호가 작은 상어가 있는 경우 (추방)
                else if (board[nx][ny] <= no) {
                    out++;
                }
                ok = true;
                break;
            }
        }

        // 자신의 냄새가 있는 칸의 방향으로 잡아서 이동 (여러 개면 우선순위에 따름)
        if (!ok) {
            for (int k = 0; k < 4; k++) {
                int nx = x + dx[priority[no][sharkDir][k]];
                int ny = y + dy[priority[no][sharkDir][k]];

                if (canMove2(nx, ny, no)) {
                    boardNext[nx][ny] = no;
                    dir[no] = priority[no][sharkDir][k];
                    break;
                }
            }
        }
    }

    // 격자 갱신
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            board[i][j] = boardNext[i][j];

            // 1초가 지난 냄새 흔적 감소
            if (smell[i][j] > 0) {
                smell[i][j]--;
            }
            // 0이 된 경우
            if (smell[i][j] == 0) {
                smellWho[i][j] = 0;
            }

            // 상어의 이동으로 인한 냄새정보 갱신
            if (board[i][j] > 0) {
                smell[i][j] = K;
                smellWho[i][j] = board[i][j];
            }
        }
    }
}

void print() {
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            cout << board[i][j] << ' ';
        }
        cout << '\n';
    }
}

void solution() {
    int ans = 1;

    while (ans != 1001) {
        move();
        
        // 1마리만 남았는지 확인
        if (out == M-1) {
            break;
        }
        ans++;
    }

    if (ans != 1001) {
        cout << ans << '\n';
    }
    else {
        cout << -1 << '\n';
    }
}

int main() {
    input();
    solution();
    return 0;
}