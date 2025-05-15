#include <iostream>
#include <algorithm>
using namespace std;

char v[1001][1001];
int d[1001][1001];      // 인덱스별 만들어질 수 있는 정사각형 변의 길이

int main() {
    int n, m;
    int max = 0;
    cin >> n >> m;

    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= m; j++) {
            cin >> v[i][j];
            v[i][j] -= '0';     // '0'과 '1'을 숫자 0과 1로 변경
        }
    }

    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= m; j++) {
            // 값이 1
            if (v[i][j] == 1) {
                // 현재 위치 기준 위쪽.왼쪽.왼쪽 대각선 확인하여 정사각형의 길이 갱신
                d[i][j] = min(min(d[i-1][j], d[i][j-1]), d[i-1][j-1]) + 1;

                // 가장 긴 정사각형 길이 변경
                if (max < d[i][j]) {
                    max = d[i][j];
                }
            }
        }
    }
    cout << max*max << '\n';
    return 0;
}