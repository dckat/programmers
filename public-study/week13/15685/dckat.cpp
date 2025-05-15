#include <iostream>
#include <vector>
using namespace std;

int N;
bool square[101][101];
int start[20][2];
int g[20];
int d[20];
int dx[] = {1, 0, -1, 0};
int dy[] = {0, -1, 0, 1};

void input() {
    cin >> N;

    for (int i = 0; i < N; i++) {
        cin >> start[i][0] >> start[i][1] >> d[i] >> g[i];
    }
}

// 드래곤 커브의 방향정보 저장
vector<int> makeDirections(int direction, int generation) {
    vector<int> dirs;
    dirs.push_back(direction);  // 0세대 1개 추가하고 시작

    // direction 리스트 만들기
    // 다음 세대에 추가되는 드래곤 커브 방향: 이전 세대까지의 드래곤 커브의 방향의 역순 + 시계방향 90도 회전
    for (int j = 1; j <= generation; j++) {
        for (int k = dirs.size() - 1; k >= 0; k--) {
            int dir = (dirs[k] + 1) % 4;
            dirs.push_back(dir);
        }
    }
    return dirs;
}

// 생성된 방향들을 토대로 드래곤커브 그리기
void dragonCurve(int startX, int startY, vector<int>& dirs) {
    int x = startX;
    int y = startY;
    square[x][y] = true;

    for (int j = 0; j < dirs.size(); j++) {
        int dir = dirs[j];
        int nx = x + dx[dir];
        int ny = y + dy[dir];
        square[nx][ny] = true;
        x = nx;
        y = ny;
    }
}

// 정사각형 여부 확인
bool canSquare(int x, int y) {
    if (square[x][y] && square[x][y+1] && square[x+1][y] && square[x+1][y+1]) {
        return true;
    }
    return false;
}

int checkSquare() {
    int answer = 0;

    // 정사각형이 만들어지는 갯수 체크
    for (int i = 0; i < 100; i++) {
        for (int j = 0; j < 100; j++) {
            if (canSquare(i, j)) {
                answer++;
            }
        }
    }
    return answer;
}

void solution() {
    // 드래곤 커브별로 방향정보 받아오고 보드에 그리기
    for (int i = 0; i < N; i++) {
        vector<int> dirs = makeDirections(d[i], g[i]);
        dragonCurve(start[i][0], start[i][1], dirs);
    }
    int answer = checkSquare();
    cout << answer << '\n';
}

int main() {
    input();
    solution();
    return 0;
}