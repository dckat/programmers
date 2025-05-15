#include <iostream>
#include <vector>
using namespace std;

// 각 집-치킨집 거리 구하는 함수
int getDist(pair<int, int>& x, pair<int, int>& y) {
    return abs(x.first-y.first) + abs(x.second-y.second);
}

// 도시 치킨거리를 구하는 함수
int calc(vector<pair<int, int>>& h, vector<pair<int, int>>& c) {
    int result = 0;
    
    for (int i = 0; i < h.size(); i++) {
        int min = 100;

        for (int j = 0; j < c.size(); j++) {
            int dist = getDist(h[i], c[j]);
            if (min > dist) {
                min = dist;
            }
        }
        result += min;
    }

    return result;
}

int main() {
    ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

    int n, m;
    cin >> n >> m;

    vector<vector<int>> v(n, vector<int>(n));   // 도시 정보 저장
    vector<pair<int, int>> h;                   // 집 좌표 정보
    vector<pair<int, int>> c;                   // 치킨집 좌표 정보
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cin >> v[i][j];
            // 집
            if (v[i][j] == 1) {
                h.push_back({i, j});
            }
            // 치킨집
            if (v[i][j] == 2) {
                c.push_back({i, j});
            }
        }
    }

    int answer = 0;     // 도시 치킨거리 최솟값 저장
    
    // 비트마스킹 활용 치킨집 선택
    // 선택 후 도시 치킨거리의 최솟값 구함
    for (int i = 1; i < (1 << c.size()); i++) {
        vector<pair<int, int>> cTemp;   // 선택된 치킨집 저장을 위한 vector

        // 비트마스킹 활용 도시 내의 치킨집 선택 (i에 따라)
        for (int j = 0; j < c.size(); j++) {
            if (i & (1 << j)) {
                cTemp.push_back(c[j]);
            }
        }

        // 선택한 치킨집이 m개를 초과하면 계산 수행 X
        if (cTemp.size() > m) {
            continue;
        }
        int temp = calc(h, cTemp);  // 도시 치킨거리 계산

        if (answer == 0 || answer > temp) {
            answer = temp;
        }
    }
    cout << answer << '\n';

    return 0;
}