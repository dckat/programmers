#include <iostream>
#include <algorithm>
#include <vector>
#include <map>
using namespace std;

/* 정렬 기준
    1. 등장횟수 오름차순
    2. 숫자의 오름차순 (등장횟수가 동일)
*/
bool cmp(pair<int, int> a, pair<int, int> b) {
    if (a.second == b.second) {
        return a.first < b.first;
    }
    return a.second < b.second;
}

int main() {
    int r, c, k;
    vector<vector<int>> v(100, vector<int>(100, 0));    // 최초에 100x100 선언 (일부만 하면 나중에 갱신시 불편함)

    cin >> r >> c >> k;

    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            cin >> v[i][j];
        }
    }

    int cnt = 0;
    int row = 3;
    int col = 3;

    // 101까지 해줘야 함 (100초까지 풀려야 하므로 --> 여기서 74프로대에서 걸림)
    while (cnt != 101) {
        map<int, int> m;    // 각 숫자별 등장횟수 체크 map
        int temp = 0;        // 연산 중 갱신에서 나올 size 체크
        if (v[r-1][c-1] == k) {
            cout << cnt << '\n';
            return 0;
        }
        
        // R연산
        if (row >= col) {
            for (int i = 0; i < row; i++) {
                // 숫자 등장한 횟수 체크 후 갱신을 위해 모든 요소를 0으로 변경
                for (int j = 0; j < col; j++) {
                    // 0이면 count 안하고 continue (이 부분이 없으면 map에 0이 포함되어 카운트 됨)
                    if (v[i][j] == 0) {
                        continue;
                    }
                    m[v[i][j]]++;
                    v[i][j] = 0;
                }
                if (temp == 0 || 2*m.size() > temp)
                    temp = 2*m.size();

                vector<pair<int, int>> tmp(m.begin(), m.end());
                sort(tmp.begin(), tmp.end(), cmp);

                for (int j = 0; j < min((int)tmp.size(), 50); j++) {
                    v[i][2*j] = tmp[j].first;
                    v[i][2*j+1] = tmp[j].second;
                }
                m.clear();
            }
            col = temp;
        }
        // C연산
        else {
            for (int i = 0; i < col; i++) {
                for (int j = 0; j < row; j++) {
                    if (v[j][i] == 0) {
                        continue;
                    }
                    m[v[j][i]]++;
                    v[j][i] = 0;
                }
                if (temp == 0 || 2*m.size() > temp)
                    temp = m.size()*2;

                vector<pair<int, int>> tmp(m.begin(), m.end());
                sort(tmp.begin(), tmp.end(), cmp);

                for (int j = 0; j < min((int)tmp.size(), 50); j++) {
                    v[2*j][i] = tmp[j].first;
                    v[2*j+1][i] = tmp[j].second;
                }
                m.clear();
            }
            row = temp;
        }
        cnt++;
    }
    cout << -1 << '\n';
    return 0;
}