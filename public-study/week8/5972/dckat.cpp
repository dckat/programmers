#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;

int main() {
    int n, m;
    cin >> n >> m;

    vector<int> d(n+1, -1);         // 출발지로부터 모든 지점까지 최소 거리
    vector<pair<int, int>> v[n+1];  // 각 정점별 인접된 헛간 정보 저장
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;     // 다익스트라 알고리즘을 위한 pq (minHeap 기반)

    for (int i = 0; i < m; i++) {
        int a, b, c;
        cin >> a >> b >> c;

        // 양방향이라 2가지 모두 저장해야 됨
        v[a].push_back({b, c});
        v[b].push_back({a, c});
    }

    // 1번부터 시작
    d[1] = 0;
    pq.push({0, 1});

    while (!pq.empty()) {
        // 1번 헛간에서 가장 가까운 헛간의 정보가 항상 pop
        int dist = pq.top().first;
        int target = pq.top().second;
        pq.pop();

        // 현재 헛간에서 인접된 헛간을 찾고 최소거리로 갱신
        for (int i = 0; i < v[target].size(); i++) {
            int next = v[target][i].first;
            int nextDist = dist + v[target][i].second;

            if (d[next] == -1 || d[next] > nextDist) {
                d[next] = nextDist;
                pq.push({d[next], next});
            }
        }
    }
    cout << d[n] << '\n';
    return 0;
}