#include <iostream>
#include <vector>
#include <cmath>
#include <climits>
using namespace std;

int v[501][2];

// 행렬 곱셈 횟수의 최솟값 저장
// d[i][j] ==> i번째 행렬부터 j번째 행렬까지 행렬곱셈에 필요한 최소 연산 수
int d[501][501]; 

int main() {
    int n;
    cin >> n;

    for (int i = 1; i <= n; i++) {
        cin >> v[i][0] >> v[i][1];
    }

    // i: 곱을 수행하려는 행렬의 크기
    // j: 곱셈이 시작되는 행렬. k: 임의의 중간 지점
    // d[j][i+j] ==> min(d[j][k]+d[k+1][i+j] + 2개 그룹의 행렬 곱 연산 횟수) [j <= k <= i+j]
    for (int i = 1; i <= n; i++) {
        for (int j = 1; i+j <= n; j++) {
            d[j][i+j] = INT_MAX;
            for (int k = j; k <= i+j; k++) {
                d[j][i+j] = min(d[j][i+j], d[j][k]+d[k+1][i+j]+(v[j][0]*v[k][1]*v[i+j][1]));
            }
        }
    }

    cout << d[1][n] << '\n';
    return 0;
}