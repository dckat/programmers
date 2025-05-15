#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int main() {
    ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

    int n, k;
    cin >> n >> k;

    vector<int> v(n);       // 원생 키 정보
    vector<int> diff(n-1);  // 인접한 원생간 키 차이
    int answer = 0;         // 비용의 최솟값
    for (int i = 0; i < n; i++) {
        cin >> v[i];
    }

    for (int i = 1; i < n; i++) {
        diff[i-1] = v[i] - v[i-1];
    }
    sort(diff.begin(), diff.end());     // 키 차이 정렬

    // 가장 큰 키 차이의 k-1개를 제외한 키 차이 누적
    for (int i = 0; i < n-k; i++) {
        answer += diff[i];
    }

    cout << answer << '\n';

    return 0;
}