#include <iostream>
#include <vector>
#include <queue>
using namespace std;

int dist[300][300];
bool visit[300][300];

int dx[] = { -2,-1,1,2,2,1,-1,-2 };
int dy[] = { 1,2,2,1,-1,-2,-2,-1 };

int main()
{
    ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);
    
	int T;
	cin >> T;

	while (T--) {
		int N;
		cin >> N;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				dist[i][j] = 0;
				visit[i][j] = false;
			}
		}

		int sx, sy;
		int fx, fy;

		cin >> sx >> sy;
		cin >> fx >> fy;

		queue<pair<int, int>> q;
		q.push(make_pair(sx, sy));
		visit[sx][sy] = true;

		while (!q.empty()) {
			int x = q.front().first;
			int y = q.front().second;
			q.pop();

			if (x == fx && y == fy) {
				cout << dist[fx][fy] << '\n';
				break;
			}

			for (int i = 0; i < 8; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if (0 <= nx && nx < N && 0 <= ny && ny < N) {
					if (!visit[nx][ny]) {
						q.push(make_pair(nx, ny));
						dist[nx][ny] = dist[x][y] + 1;
						visit[nx][ny] = true;
					}
				}
			}
		}
	}
	return 0;
}