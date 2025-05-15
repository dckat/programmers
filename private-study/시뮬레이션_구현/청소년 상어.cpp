#include <iostream>
#include <vector>
using namespace std;

int dx[] = {-1, -1, 0, 1, 1, 1, 0, -1};
int dy[] = {0, -1, -1, -1 ,0, 1, 1, 1};

void input(vector<vector<int>>& fish, vector<vector<int>>& dir) {
    for (int i = 0; i < 4; i++) {
        for (int j = 0; j < 4; j++) {
            cin >> fish[i][j] >> dir[i][j];
            dir[i][j]--;
        }
    }
}

bool isBound(int x, int y) {
    if (0 <= x && x < 4 && 0 <= y && y < 4) {
        return true;
    }
    return false;
}

bool canMove(vector<vector<int>>& fish, int nx, int ny, int x, int y) {
    if (isBound(nx, ny) && fish[nx][ny] >= 0 && (nx != x || ny != y)) {
        return true;
    }
    return false;
}

int solution(vector<vector<int>> fish, vector<vector<int>> dir, int x, int y, int d) {
    for (int who = 1; who <= 16; who++) {
        bool f = false;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (fish[i][j] == who) {
                    for (int k = 0; k < 8; k++) {
                        int nx = i + dx[dir[i][j]];
                        int ny = j + dy[dir[i][j]];

                        if (canMove(fish, nx, ny, x, y)) {
                            swap(fish[i][j], fish[nx][ny]);
                            swap(dir[i][j], dir[nx][ny]);
                            f = true;
                            break;
                        }
                        else {
                            dir[i][j] = (dir[i][j]+1)%8;
                        }
                    }
                }
                if (f) {
                    break;
                }
            }
            if (f) {
                break;
            }
        }
    }
    int result = 0;
    int sx = x + dx[d];
    int sy = y + dy[d];

    while (isBound(sx, sy)) {
        if (fish[sx][sy] != 0) {
            int temp = fish[sx][sy];
            fish[sx][sy] = 0;
            int cur = temp + solution(fish, dir, sx, sy, dir[sx][sy]);

            if (result < cur) {
                result = cur;
            }
            fish[sx][sy] = temp;
        }
        sx += dx[d];
        sy += dy[d];
    }
    return result;
}

int main() {
    vector<vector<int>> fish(4, vector<int>(4));
    vector<vector<int>> dir(4, vector<int>(4));
    
    input(fish, dir);

    int answer = fish[0][0];
    fish[0][0] = 0;
    answer += solution(fish, dir, 0, 0, dir[0][0]);

    cout << answer << '\n';
    return 0;
}