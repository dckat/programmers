#include <iostream>
#include <vector>
#include <queue>
using namespace std;

int N, M;
int a[1000][1000];
int dist[1000][1000];
bool visit[1000][1000];

int dx[] = { -1,1,0,0 };
int dy[] = { 0,0,-1,1 };

int main()
{
	queue<pair<int, int>> q;
	cin >> N >> M;

	for (int i = 0; i < M; i++) {
		for (int j = 0; j < N; j++) {
			cin >> a[i][j];

			if (a[i][j] == 1) {
				dist[i][j] = 0;
				q.push(make_pair(i, j));
				visit[i][j] = true;
			}
		}
	}

	while (!q.empty()) {
		int x = q.front().first;
		int y = q.front().second;
		q.pop();

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx >= 0 && ny >= 0 && nx < M && ny < N) {
				if (!visit[nx][ny] && a[nx][ny] == 0) {
					q.push(make_pair(nx, ny));
					dist[nx][ny] = dist[x][y] + 1;
					visit[nx][ny] = true; a[nx][ny] = 1;
				}
			}
		}
	}

	int answer = 0;
	for (int i = 0; i < M; i++) {
		for (int j = 0; j < N; j++) {
			if (a[i][j] == 0) {
				cout << -1 << '\n';
				return 0;
			}
			answer = max(answer, dist[i][j]);
		}
	}
	cout << answer << '\n';

	return 0;
}