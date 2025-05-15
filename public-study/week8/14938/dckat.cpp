#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;

int n, m, r;
vector<int> items(101);             // 각 정점 별 아이템 갯수
vector<pair<int, int>> v[101];      // 각 정점과 인접한 정점과 거리 저장 (first: 정점 / second: 거리)

int dik(int start) {
    int tmp = 0;        // 각 지점별 총 가져온 사탕 수

    vector<int> d(n+1, -1);     // 최단경로 확인을 위한 vector 초기화 (경로가 없는 것은 -1) --> 각 지점별 최단거리를 확인해야 하므로 매번 초기화 되어야 함
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;     // 다익스트라 알고리즘을 위한 pq (minHeap 기반)

    d[start] = 0;
    pq.push({0, start});

    while (!pq.empty()) {
        // start번 지점부터 가장 적은 거리의 지점이 항상 pq에서 pop
        int dist = pq.top().first;
        int target = pq.top().second;
        pq.pop();

        // target에서 인접된 지점을 찾고 최소거리로 갱신
        for (int i = 0; i < v[target].size(); i++) {
            int next = v[target][i].first;
            int nextDist = dist + v[target][i].second;

            if (d[next] == -1 || d[next] > nextDist) {
                d[next] = nextDist;
                pq.push({d[next], next});
            }
        }
    }

    // 수색 범위 이내의 지점을 확인하여 얻을 수 있는 아이템 갯수 합산
    for (int i = 1; i <= n; i++) {
        // 초기화를 -1로 하였으므로 이에 대한 추가적인 확인 필요 (이 부분 때문에 삽질 좀 했음...)
        if (d[i] != -1 && d[i] <= m) {
            tmp += items[i];
        }
    }
    return tmp;
}

int main() {
    cin >> n >> m >> r;

    for (int i = 1; i <= n; i++) {
        cin >> items[i];
    }

    for (int i = 0; i < r; i++) {
        int a, b, l;
        cin >> a >> b >> l;

        v[a].push_back({b, l});
        v[b].push_back({a, l});
    }

    int max = -1;

    // 1번 지점부터 가질수 있는 최대 아이템 갯수 갱신
    for (int i = 1; i <= n; i++) {
        int sum = dik(i);
        if (max < sum) {
            max = sum;
        }
    }

    cout << max << '\n';
    return 0;
}