#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;

// 다익스트라 알고리즘
void dij(vector<int>& d, vector<pair<int, int>> v[], int start) {
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
    d[start] = 0;
    pq.push({0, start});

    while (!pq.empty()) {
        int dist = pq.top().first;
        int target = pq.top().second;
        pq.pop();

        for (int i = 0; i < v[target].size(); i++) {
            int next = v[target][i].first;
            int nextDist = dist + v[target][i].second;

            if (d[next] == -1 || d[next] > nextDist) {
                d[next] = nextDist;
                pq.push({d[next], next});
            }
        }
    }
}

int main() {
    int n, m, x;
    cin >> n >> m >> x;

    vector<int> d(n+1, -1);     // X번 --> 각자 집
    vector<int> rd(n+1, -1);    // 각자 집 --> X번

    vector<pair<int, int>> v[n+1];      // 순방향 경로
    vector<pair<int, int>> rv[n+1];     // 역방향 경로 (X번에서 어떻게 오는지 확인)

    for (int i = 0; i < m; i++) {
        int start, end, t;
        cin >> start >> end >> t;

        // 가는 방향과 오는 방향 연결그래프 정보 저장
        v[start].push_back({end, t});
        rv[end].push_back({start, t});
    }

    dij(d, v, x);
    dij(rd, rv, x);

    int max = -1;
    for (int i = 1; i <= n; i++) {
        if (d[i] != -1 && rd[i] != -1) {
            int tmp = d[i] + rd[i];
            if (max < tmp) {
                max = tmp;
            }
        }
    }
    cout << max << '\n';
    return 0;
}