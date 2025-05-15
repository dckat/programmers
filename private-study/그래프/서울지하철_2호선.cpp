#include <iostream>
#include <vector>
#include <queue>
using namespace std;

int n;
int check[3001];
bool visited[3001];
vector<int> edges[3001];
int dist[3001];

void bfs() {
    queue<int> q;
    for (int i = 1; i <= n; i++) {
        if (check[i] == 2) {
            dist[i] = 0;
            visited[i] = true;
            q.push(i);
        }
        else {
            dist[i] = -1;
        }
    }

    while (!q.empty()) {
        int x = q.front();
        visited[x] = true;
        q.pop();

        for (auto y : edges[x]) {
            if (!visited[y]) {
                q.push(y);
                dist[y] = dist[x] + 1;
            }
        }
    }
}

int go(int x, int p) {
    if (check[x] == 1) {
        return x;
    }
    check[x] = 1;

    for (int y : edges[x]) {
        if (y == p) {
            continue;
        }
        int res = go(y, x);
        if (res == -2) {
            return -2;
        }

        if (res >= 0) {
            check[x] = 2;
            if (x == res) {
                return -2;
            }
            else {
                return res;
            }
        }
    }
    return -1;
}

int main() {
    cin >> n;

    for (int i = 0; i < n; i++) {
        int from, to;
        cin >> from >> to;

        edges[from].push_back(to);
        edges[to].push_back(from);
    }
    go(1, -1);
    bfs();
 
    for (int i = 1; i <= n; i++) {
        cout << dist[i] << ' ';
    }

    return 0;
}