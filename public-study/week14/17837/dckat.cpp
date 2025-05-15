#include <iostream>
#include <vector>
#include <tuple>
#include <algorithm>
using namespace std;

int dx[] = {0, 0, -1, 1};
int dy[] = {1, -1, 0, 0};
vector<int> board[13][13];               // 현재 보드별 위치된 말 현황 (말의 번호가 저장)
vector<tuple<int, int, int>> token;      // 1 ~ k번말까지 현재 위치.방향 저장 (x, y, 방향)
int color[13][13];                       // 색 정보 저장
int n, k;

bool canMove(int x, int y) {
    if (1 <= x && x <= n && 1 <= y && y <= n) {
        return true;
    }
    return false;
}

int findIdx(int x, int y, int k) {
    for (int i = 0; i < board[x][y].size(); i++) {
        if (board[x][y][i] == k) {
            return i;
        }
    }
}

void input() {
    cin >> n >> k;
    
    // 보드 상태 저장
    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= n; j++) {
            cin >> color[i][j];
        }
    }

    // 1번말부터 최초 위치 및 방향 저장
    // 인덱스 통일을 위해 임의의 값을 vector에 추가
    token.push_back({0, 0, -1});
    int x, y, dir;
    for (int i = 1; i <= k; i++) {
        cin >> x >> y >> dir;
        board[x][y].push_back(i);
        token.push_back({x, y, dir-1});
    }
}

int solution() {
    int count = 1;

    while (count != 1001) {
        for (int i = 1; i <= k; i++) {
            int x, y, dir;
            tie(x, y, dir) = token[i];

            int nx = x + dx[dir];
            int ny = y + dy[dir];

            // 파란색 또는 벗어나는 범위
            if (!canMove(nx, ny) || color[nx][ny] == 2) {
                switch (dir) {
                    case 0: dir = 1; break;
                    case 1: dir = 0; break;
                    case 2: dir = 3; break;
                    case 3: dir = 2; break;
                }
                get<2>(token[i]) = dir;

                nx = x + dx[dir];
                ny = y + dy[dir];

                if (!canMove(nx, ny) || color[nx][ny] == 2) {
                    continue;
                }
                get<0>(token[i]) = nx;
                get<1>(token[i]) = ny;
            }
            
            // 이동하려는 말의 인덱스 찾기
            int curIdx = findIdx(x, y, i);
            int nextIdx = board[nx][ny].size();

            // 위에 있는 말을 모두 이동
            for (int j = curIdx; j < board[x][y].size(); j++) {
                int chess = board[x][y][j];
                board[nx][ny].push_back(chess);
                board[x][y].erase(board[x][y].begin() + j);
                j--;

                // 이동된 말의 정보 변경
                get<0>(token[chess]) = nx;
                get<1>(token[chess]) = ny;
            }

            // 특정 위치에 4마리 이상의 말이 모인 경우
            if (board[nx][ny].size() >= 4) {
                return count;
            }

            // 빨간색인 경우에는 옮긴 말들을 반대로 뒤집기
            if (color[nx][ny] == 1) {
                reverse(board[nx][ny].begin() + nextIdx, board[nx][ny].end());
            }
        } 
        count++;
    }
    return -1;
}


int main() {
    input();
    cout << solution() << '\n';
    return 0;
}