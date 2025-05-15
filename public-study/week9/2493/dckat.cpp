#include <iostream>
#include <vector>
#include <stack>
using namespace std;

int main() {
    cin.tie(0);
    cout.tie(0);
    ios_base::sync_with_stdio(false);

    int n;
    cin >> n;

    vector<int> v(n+1);             // 입력 벡터
    stack<pair<int, int>> s;        // 정답을 구하기 위한 스택 (값. 인덱스로 구성)

    for (int i = 1; i <= n; i++) {
        cin >> v[i];

        // 수신 가능한 탑 찾기 (비어있기 전까지)
        while (!s.empty()) {
            // 수신한 탑을 찾은 경우
            if (s.top().first >= v[i]) {
                cout << s.top().second << ' ';
                break;
            }
            s.pop();
        }
        if (s.empty()) {
            cout << 0 << ' ';
        }

        s.push({v[i], i});
    }
    return 0;
}