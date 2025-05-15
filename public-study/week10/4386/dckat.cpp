#include <iostream>
#include <vector>
#include <queue>
#include <tuple>
#include <cmath>
#include <algorithm>
using namespace std;

vector<int> s(101);

bool cmp(tuple<int, int, double>& a, tuple<int, int, double>& b) {
    return get<2>(a) < get<2>(b);
}

// 최상단 노드 찾기 (최상단을 찾기 위해 재귀호출)
int find(int x) {
    if (x == s[x]) {
        return x;
    }
    return s[x] = find(s[x]);
}

// 합집합 (최상단 노드를 같게 하는 작업)
void uni(int x, int y) {
    // 합집합 전 최상단 노드부터 탐색
    int rx = find(x);
    int ry = find(y);

    if (rx != ry) {
        s[rx] = ry;
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    // 소수점 설정 (2자리 까지)
    cout << fixed;
    cout.precision(2);

    int v;
    double sum = 0.0;
    cin >> v;

    vector<pair<double, double>> V;
    vector<tuple<int, int, double>> E;

    // 집합 초기화 (1번 부터)
    for (int i = 1; i <= v; i++) {
        s[i] = i;
    }

    // 좌표 정보 입력받기
    for (int i = 0; i < v; i++) {
        double a, b;
        cin >> a >> b;
        V.push_back({a, b});
    }

    // 거리 좌표 구하기
    for (int i = 0; i < v; i++) {
        double x = V[i].first;
        double y = V[i].second;
        for (int j = i+1; j < v; j++) {
            double xx = V[j].first;
            double yy = V[j].second;

            double dist = sqrt(((x - xx) * (x - xx) + (y - yy) * (y - yy)));

            E.push_back({i+1, j+1, dist});
        }
    }

    // 간선 길이 기준 정렬
    sort(E.begin(), E.end(), cmp);

    // 우선순위 큐 선언 (minheap)
    priority_queue<tuple<double, int, int>, vector<tuple<double, int, int>>, greater<tuple<double, int, int>>> pq;

    for (int i = 0; i < E.size(); i++) {
        pq.push({get<2>(E[i]), get<0>(E[i]), get<1>(E[i])});
    }

    // 우큐에서 빼면서 최소 신장 트리 갖기 (v-1개 채워질때 까지)
    // 하나씩 빼면서 사이클도 찾기 (union. find 이용)
    int cnt = 0;
    while (cnt != v-1) {
        tuple<double, int, int> t = pq.top();
        pq.pop();

        int start = get<1>(t);
        int end = get<2>(t);
        double dist = get<0>(t);

        if (find(start) != find(end)) {
            uni(start, end);
            sum += dist;
            cnt++;
        }
    }
    cout << sum << '\n';
    
    return 0;
}