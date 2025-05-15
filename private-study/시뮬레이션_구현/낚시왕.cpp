#include <iostream>
#include <vector>
#include <tuple>
using namespace std;

// 좌표별 상어 정보
struct Shark {
    int speed = 0;
    int direction = 0;
    int size = 0;
};

int dx[] = {0, -1, 1, 0, 0};
int dy[] = {0, 0, 0, 1, -1};
Shark cur[101][101];
Shark nex[101][101];
int r, c, m;

void input() {
    cin >> r >> c >> m;

    for (int i = 0; i < m; i++) {
        int x, y, s, d, z;
        cin >> x >> y >> s >> d >> z;

        if (d <= 2) {
            s %= (2*r - 2);
        }
        else {
            s %= (2*c - 2);
        }

        cur[x][y] = Shark({s, d, z});
    }
}

// 속도에 따라 상어의 이동
tuple<int, int, int> getNext(int x, int y, int speed, int dir) {
    for (int k = 0; k < speed; k++) {
        // 위
        if (dir == 1) {
            // 경계
            if (x == 1) {
                x = 2;
                dir = 2;
            }
            else {
                x--;
            }
        }
        // 아래
        else if (dir == 2) {
            // 경계
            if (x == r) {
                x = r-1;
                dir = 1;
            }
            else {
                x++;
            }
        }
        // 오른쪽
        else if (dir == 3) {
            if (y == c) {
                y = c-1;
                dir = 4;
            }
            else {
                y++;
            }
        }
        // 왼쪽
        else {
            if (y == 1) {
                y = 2;
                dir = 3;
            }
            else {
                y--;
            }
        }
    }
    return make_tuple(x, y, dir);
}

long long solution() {
    long long answer = 0;

    // 1. 낚시왕의 이동
    for (int j = 1; j <= c; j++) {
        // 2. 가장 가까운 상어를 잡음
        for (int i = 1; i <= r; i++) {
            if (cur[i][j].size > 0) {
                answer += cur[i][j].size;
                cur[i][j].size = 0;
                break;
            }
        }

        // 3. 상어의 이동
        for (int l1 = 1; l1 <= r; l1++) {
            for (int l2 = 1; l2 <= c; l2++) {
                if (cur[l1][l2].size == 0) {
                    continue;
                }
                auto f = cur[l1][l2];
                int x, y, dir;
                tie(x, y, dir) = getNext(l1, l2, f.speed, f.direction);

                if (nex[x][y].size == 0 || nex[x][y].size < f.size) {
                    nex[x][y] = Shark({f.speed, dir, f.size});
                }
            }
        }

        // 최종적으로 이동된 상어정보 갱신
        for (int l1 = 1; l1 <= r; l1++) {
            for (int l2 = 1; l2 <= c; l2++) {
                cur[l1][l2] = nex[l1][l2];
                nex[l1][l2].size = 0;
            }
        }
    }
    return answer;
}

int main() {
    input();
    long long answer = solution();
    cout << answer << '\n';
    return 0;
}