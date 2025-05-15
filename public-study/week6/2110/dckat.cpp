#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;

int main() {
    int n, c;
    cin >> n >> c;

    vector<int> v(n, 0);
    priority_queue<int> pq;
    for (int i = 0; i < n; i++) {
        cin >> v[i];
    }
    sort(v.begin(), v.end());

    int min = 1;                // 최소 간격
    int max = v[n-1] - v[0];    // 최대 간격
    int result;                 // 결과값

    while (min <= max) {
        int cnt = 1;
        int mid = (min+max) / 2;    // 기준이 되는 간격
        int start = v[0];

        // 기준이 된 간격 이상으로 설치 가능한지 체크
        for (int i = 1; i < n; i++) {
            if (v[i] - start >= mid) {
                cnt++;
                start = v[i];
            }
        }

        // c개 이상 설치 (간격을 증가)
        // c개 이상일때 일부를 빼서 c개를 맞춰도 간격은 무조건 mid보다 크거나 같음
        if (cnt >= c) {
            result = mid;
            min = mid + 1;
        }
        // c개 미만 설치 (간격을 감소)
        else {
            max = mid - 1;
        }
    }
    cout << result << '\n';

    return 0;
}