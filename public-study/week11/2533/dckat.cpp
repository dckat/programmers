#include <iostream>
#include <vector>
#include <cmath>
using namespace std;

int n;
vector<int> tree[1000001];
int dp[1000001][2];
bool visited[1000001];

void solution(int num) {
    if (visited[num]) {
        return;
    }
    visited[num] = true;
    dp[num][0] = 0;
    dp[num][1] = 1;

    for (int i = 0; i < tree[num].size(); i++) {
        int child = tree[num][i];
        if (visited[child]) {
            continue;
        }
        solution(child);
        dp[num][0] += dp[child][1];
        dp[num][1] += min(dp[child][0], dp[child][1]);
    }
}

void input() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int u, v;

    cin >> n;
    for (int i = 1; i < n; i++) {
        cin >> u >> v;
        tree[u].push_back(v);
        tree[v].push_back(u);
    }
}

int main() {
    input();
    solution(1);
    cout << min(dp[1][0], dp[1][1]) << '\n';
    return 0;
}