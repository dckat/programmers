#include <iostream>
#include <vector>
#include <cstring>
#include <algorithm>
using namespace std;

int dx[] = {-1, -1, -1, 0, 0, 1, 1, 1};
int dy[] = {-1, 0, 1, -1, 1, -1, 0, 1};
int N, M, K;
int a[11][11];
int d[11][11];
int p[11][11];
vector<int> tree[11][11];

void input() {
    cin >> N >> M >> K;

    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= N; j++) {
            cin >> a[i][j];
            d[i][j] = 5;
        }
    }

    for (int i = 1; i <= M; i++) {
        int x, y, z;
        cin >> x >> y >> z;
        tree[x][y].push_back(z);
    }
}

bool isBound(int x, int y) {
    if (x >= 1 && x <= N && y >= 1 && y <= N) {
        return true;
    }
    return false;
}

void go() {
    for (int l = 0; l < K; l++) {
        memset(p, 0, sizeof(p));
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {

                // 나이 오름차순으로 정렬
                sort(tree[i][j].begin(), tree[i][j].end());

                int dead = 0;
                vector<int> temp;   // 양분을 먹고 난 후 나무 정보

                // 봄: 나무의 나이만큼 양분을 먹음. 그렇지 않은 나무는 죽음
                for (int k = 0; k < tree[i][j].size(); k++) {
                    if (d[i][j] >= tree[i][j][k]) {
                        d[i][j] -= tree[i][j][k];
                        temp.push_back(tree[i][j][k]+1);
                    }
                    else {
                        dead += tree[i][j][k] / 2;
                    }
                }
                tree[i][j] = temp;

                // 여름: 죽은 나무가 양분으로 변함
                d[i][j] += dead;

                // 가을: 나이가 5의 배수인 나무가 번식 (나이는 1)
                for (int k = 0; k < tree[i][j].size(); k++) {
                    if (tree[i][j][k] % 5 == 0) {
                        for (int z = 0; z < 8; z++) {
                            int nx = i + dx[z];
                            int ny = j + dy[z];

                            if (isBound(nx, ny)) {
                                p[nx][ny]++;
                            }
                        }
                    }
                }

                // 겨울: 땅에 양분 추가
                d[i][j] += a[i][j];
            }
        }

        // 번식이 된 나무를 추가 (해당 나무는 다음 해에 양분을 먹어야 하므로 따로 빼둬야 함)
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                for (int k = 0; k < p[i][j]; k++) {
                    tree[i][j].push_back(1);
                }
            }
        }
    }
}

int solution() {
    int answer = 0;

    go();

    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= N; j++) {
            answer += tree[i][j].size();
        }
    }

    return answer;
}

int main() {
    input();
    int answer = solution();
    cout << answer << '\n';
    return 0;
}