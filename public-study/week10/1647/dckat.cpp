#include <iostream>
#include <vector>
#include <queue>
#include <tuple>
#include <algorithm>
using namespace std;

vector<int> s(100001);

bool cmp(tuple<int, int, int>& a, tuple<int, int, int>& b) {
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

    
    int v, e;
    int sum = 0;
    cin >> v >> e;
    vector<tuple<int, int, int>> V;

    for (int i = 1; i <= v; i++) {
        s[i] = i;
    }

    // 간선 정보 입력받기
    for (int i = 0; i < e; i++) {
        int a, b, c;
        cin >> a >> b >> c;
        V.push_back(make_tuple(a, b, c));
    }

    // 간선 길이 기준 정렬
    sort(V.begin(), V.end(), cmp);

    // 우선순위 큐 선언 (minheap)
    priority_queue<tuple<int, int, int>, vector<tuple<int, int, int>>, greater<tuple<int, int, int>>> pq;

    for (int i = 0; i < V.size(); i++) {
        pq.push({get<2>(V[i]), get<0>(V[i]), get<1>(V[i])});
    }

    // 우큐에서 빼면서 최소 신장 트리 갖기 (v-1개 채워질때 까지)
    // 하나씩 빼면서 사이클도 찾기 (union. find 이용)
    int cnt = 0;
    while (cnt != v-2) {
        tuple<int, int, int> t = pq.top();
        pq.pop();

        int start = get<1>(t);
        int end = get<2>(t);
        int dist = get<0>(t);

        if (find(start) != find(end)) {
            uni(start, end);
            sum += dist;
            cnt++;
        }
    }

    // 합 계산
    cout << sum << '\n';
    return 0;
}