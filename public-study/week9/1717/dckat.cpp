#include <iostream>
#include <vector>
using namespace std;

vector<int> v(1000000);

// 최상단 노드 찾기 (최상단을 찾기 위해 재귀호출)
int find(int x) {
    if (x == v[x]) {
        return x;
    }
    return v[x] = find(v[x]);
}

// 합집합 (최상단 노드를 같게 하는 작업)
void uni(int x, int y) {
    // 합집합 전 최상단 노드부터 탐색
    int rx = find(x);
    int ry = find(y);

    if (rx != ry) {
        v[rx] = ry;
    }
}

int main() {
    cin.tie(0);
    cout.tie(0);
    ios_base::sync_with_stdio(false);

    int n, m;
    cin >> n >> m;

    for (int i = 0; i <= n; i++) {
        v[i] = i;
    }

    for (int i = 0; i < m; i++) {
        int a, b, c;
        cin >> a >> b >> c;

        // 1번연산 (집합 포함 여부)
        if (a == 1) {
            if (find(b) == find(c)) {
                cout << "YES" << '\n';
            }
            else {
                cout << "NO" << '\n';
            }
        }
        // 0번연산 (합집합)
        else {
            uni(b, c);
        }
    }
}