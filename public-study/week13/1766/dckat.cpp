#include <iostream>
#include <vector>
#include <queue>
using namespace std;

int n, m;
vector<int> orders[32001];  // 각 문제에서 다음으로 풀 문제
int depth[32001];           // 해당 문제 번호의 순위 (클수록 후순위에 풀어야 함)

void input() {
    int a, b;
    cin >> n >> m;

    for (int i = 0; i < m; i++) {
        cin >> a >> b;
        orders[a].push_back(b);
        depth[b]++;
    }
}

void solution() {
    // 우선순위 큐 선언 (minheap)
    // 쉬운 문제 (번호가 작은 문제) 부터 풀어야 함.
    priority_queue<int, vector<int>, greater<int>> pq;
    
    // 우선순위가 가장 높은 문제들을 선별하여 우선순위 큐에 push
    for (int i = 1; i <= n; i++) {
        if (depth[i] == 0) {
            pq.push(i);
        }
    }

    while (!pq.empty()) {
        int cur = pq.top();
        pq.pop();
        cout << cur << ' ';

        // 다음으로 풀 문제를 확인
        for (int i = 0; i < orders[cur].size(); i++) {
            int next = orders[cur][i];

            // 다음 문제의 순위를 1 감소
            depth[next]--;
            
            // 해당 문제 이전에 풀 문제가 없을 경우
            // 우선순위 큐에 저장
            if (depth[next] == 0) {
                pq.push(next);
            }
        }
    }
    cout << '\n';
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    
    input();
    solution();
    return 0;
}