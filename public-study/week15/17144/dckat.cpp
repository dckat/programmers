#include <iostream>
#include <algorithm>
using namespace std;

int a[50][50];  // 입력 정보
int b[50][50];  // 확산된 미세먼지 변화량 저장
int dx[] = {0, -1, 0, 1};       // 이동 방향
int dy[] = {1, 0, -1, 0};       // 이동 방향
int r, c, t;    // 입력값
int sx, sy;     // 공기 청정기 위치 (윗행 기준)

void input() {
    cin >> r >> c >> t;

    for (int i = 0; i < r; i++) {
        for (int j = 0; j < c; j++) {
            cin >> a[i][j];

            // 공기청정기 위치
            if (a[i][j] == -1) {
                sx = i;
                sy = j;
            }
        }
    }
}

// 범위 확인
bool isBound(int x, int y) {
    if (0 <= x && x < r && 0 <= y && y < c) {
        return true;
    }
    return false;
}

// 확산 가능여부 확인
bool canMove(int x, int y) {
    // 영역 내에 존재 및 공기청정기가 없는 경우
    if (isBound(x, y) && a[x][y] != -1) {
        return true;
    }
    return false;
}

void go(int sX, int sY, int dir) {
    int pre = 0;
    int x = sX;
    int y = sY + 1;
    int k = 0;

    while (true) {
        if (x == sX && y == sY) {
            break;
        }
        swap(pre, a[x][y]);

        x += dx[k];
        y += dy[k];
        
        // 경계를 넘어간 경우 방향 전환
        // 위: 반시계 방향, 아래: 시계 방향
        if (!isBound(x, y)) {
            x -= dx[k];
            y -= dy[k];
            k = (k+dir) % 4;

            x += dx[k];
            y += dy[k];
        }
        
    }
}

void solution() {
    while (t--) {
        // 1번 케이스: 미세먼지 확산
        for (int x = 0; x < r; x++) {
            for (int y = 0; y < c; y++) {
                if (a[x][y] > 0) {
                    int cnt = 0;
                    int val = a[x][y] / 5;

                    // 확산 가능한 미세먼지 영역 찾고 값 변경
                    for (int k = 0; k < 4; k++) {
                        int nx = x + dx[k];
                        int ny = y + dy[k];

                        if (canMove(nx, ny)) {
                            b[nx][ny] += val;
                            cnt++;
                        }
                    }
                    a[x][y] -= cnt*val;
                }
            }
        }

        // 확산된 양만큼 미세먼지 현황 변경
        for (int x = 0; x < r; x++) {
            for (int y = 0; y < c; y++) {
                a[x][y] += b[x][y];
                b[x][y] = 0;
            }
        }

        // 공기청정기 이동 (위.아래 각각 공기 순환)
        go(sx-1, sy, 1);
        go(sx, sy, 3);
    }

    int answer = 0;
    for (int i = 0; i < r; i++) {
        for (int j = 0; j < c; j++) {
            if (a[i][j] > 0) {
                answer += a[i][j];
            }
        }
    }
    cout << answer << '\n';
}

int main() {
    input();
    solution();
}