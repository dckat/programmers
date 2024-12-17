#include <iostream>
#include <queue>
using namespace std;

bool visited[100001];

pair<int, int> bfs(int n, int k) {
    pair<int, int> ans = {-1, -1};
    queue<pair<int, int>> q;
 	q.push(make_pair(n, 0));
    visited[n] = true;

	while (!q.empty()) {
		int x = q.front().first;
		int d = q.front().second;
		q.pop();

		visited[x] = true;
		if (x == k) {
            if (ans.first == -1) {
                ans.first = d;
                ans.second = 1;
            }
            else if (ans.first == d) {
                ans.second++;
            }
		}

		int nx = x - 1;
		if (nx >= 0 && !visited[nx]) {
			q.push(make_pair(nx, d + 1));
		}

		nx = x + 1;
		if (nx <= 100000 && !visited[nx]) {
			q.push(make_pair(nx, d + 1));
		}

		nx = 2 * x;
		if (nx <= 100000 && !visited[nx]) {
			q.push(make_pair(nx, d + 1));
		}
	}

    return ans;
}

int main() {
    int n, k;
    cin >> n >> k;

    pair<int, int> result = bfs(n, k);

    cout << result.first << '\n' << result.second << '\n';
    return 0;
}