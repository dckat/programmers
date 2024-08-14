#include <iostream>
#include <queue>
using namespace std;

bool check[1000001];
int d[1000001];

void init() {
    for (int i = 1; i <= 1000000; i++) {
        check[i] = false;
        d[i] = -1;
    }
}

// f: 건물 층, s: 시작 층, g: 도착 층, u: 올라감, d: 내려감
int main() {
    int F, S, G, U, D;
    cin >> F >> S >> G >> U >> D;

    init();

    queue<int> q;
    q.push(S);
    check[S] = true;
    d[S] = 0;

    while (!q.empty()) {
        int cur = q.front();
        q.pop();

        if (cur == G) {
            cout << d[G] << '\n';
            return 0;
        }

        int upNext = cur + U;
        int downNext = cur - D;

        if (1 <= upNext && upNext <= F && d[upNext] == -1) {
            d[upNext] = d[cur] + 1;
            q.push(upNext);
        }
        if (1 <= downNext && downNext <= F && d[downNext] == -1) {
            d[downNext] = d[cur] + 1;
            q.push(downNext);
        }
    }
    
    cout << "use the stairs" << '\n';
    return 0;
}