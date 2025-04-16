#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

struct Rotate {
    int x;
    int d;
    int k;
};

int n, m, t;
vector<vector<int>> disc;
vector<Rotate> info;

void input() {
    cin >> n >> m >> t;

    // 1번부터 시작하기 위해 0번 원판은 m개의 0으로 구성
    vector<int> v(m, 0);
    disc.push_back(v);
    v.clear();

    // 1번 원판부터 값 입력
    for (int i = 1; i <= n; i++) {
        for (int j = 0; j < m; j++) {
            int num;
            cin >> num;
            v.push_back(num);
        }
        disc.push_back(v);
        v.clear();
    }

    // 회전 정보 입력
    for (int i = 0; i < t; i++) {
        int x, d, k;
        cin >> x >> d >> k;
        info.push_back({x, d, k});
    }
}

bool check() {
    bool ok = false;
    
    vector<vector<bool>> same(n+1, vector<bool>(m, false));

    // 인접하면서 수가 같은 것 찾기
    for (int i = 1; i <= n; i++) {
        for (int j = 0; j < m; j++) {
            if (disc[i][j] == 0) {
                continue;
            }

            if (disc[i][j] == disc[i][(j+1)%m]) {
                same[i][j] = same[i][(j+1)%m] = true;
            }
            if (i+1 <= n && disc[i][j] == disc[i+1][j]) {
                same[i][j] = same[i+1][j] = true;
            }
        }
    }
    
    // 같은 값이 있는지 체크
    for (int i = 1; i <= n; i++) {
        for (int j = 0; j < m; j++) {
            if (same[i][j]) {
                ok = true;
                disc[i][j] = 0;
            }
        }
    }
    return ok;
}

// 원판 평균을 구해 조정
void adjust() {
    long long sum = 0;
    long long cnt = 0;

    // 수의 합을 계산
    for (int i = 1; i <= n; i++) {
        for (int j = 0; j < m; j++) {
            if (disc[i][j] == 0) {
                continue;
            }
            sum += disc[i][j];
            cnt++;
        }
    }
    
    // 전체 합이 0인 경우 (조정을 수행하지 않음)
    if (cnt == 0LL) {
        return;
    }

    // 합계 기준 값 조정 (평균은 소수점이 나올 수 있어 곱하기로 대체)
    for (int i = 1; i <= n; i++) {
        for (int j = 0; j < m; j++) {
            if (disc[i][j] == 0) {
                continue;
            }
            if (sum < (long long)disc[i][j]*cnt) {
                disc[i][j]--;
            }
            else if (sum > (long long)disc[i][j]*cnt) {
                disc[i][j]++;
            }
        }
    }
}

int solution() {
    int answer = 0;

    for (int i = 0; i < t; i++) {
        int x = info[i].x;
        int d = info[i].d;
        int k = info[i].k;

        // x 배수인 원판 회전
        for (int j = x; j <= n; j += x) {
            // 시계방향
            if (d == 0) {
                rotate(disc[j].rbegin(), disc[j].rbegin()+k, disc[j].rend());
            }
            // 반시계방향
            else {
                rotate(disc[j].begin(), disc[j].begin() + k, disc[j].end());
            }
        }
        bool ok = check();
        if (!ok) {
            adjust();
        }
    }

    for (int i = 1; i <= n; i++) {
        for (int j = 0; j < m; j++) {
            answer += disc[i][j];
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