#include <iostream>
#include <vector>
using namespace std;

int answer = 0;
int node;

void dfs(vector<int> d[], int k) {
    int cnt = 0;    // 연결된 자식노드 갯수 반환
    if (node == k) {
        return;
    }
    for (int i = 0; i < d[k].size(); i++) {
        // 자식노드가 연쇄적으로 지워질 대상이 아니면
        if (d[k][i] != node) {
            dfs(d, d[k][i]);
            cnt++;
        }
    }
    // 모두 순회해서 리프노드인 경우 1 증가 (이것을 for문 앞에 두면 틀림)
    if (cnt == 0) {
        answer++;
    }
}

int main() {
    int n, root;
    cin >> n;

    vector<int> v(n, 0);    // 입력 vector
    vector<int> d[n];       // 각 노드별 연결된 자식노드 저장
    for (int i = 0; i < n; i++) {
        cin >> v[i];
        
        // 루트 노드인 경우
        if (v[i] == -1) {
            root = i;
        }

        // 부모노드에서 연결된 자식노드 저장
        if (v[i] != -1) {
            d[v[i]].push_back(i);
        }
    }
    cin >> node;

    dfs(d, root);   // root 부터 dfs 활용 리프노드 탐색 (항상 0에서 출발하지 않음)
    cout << answer << '\n';

    return 0;
}