#include <iostream>
#include <vector>
using namespace std;

int solution(vector<int> v, int m) {
    int answer = 0;     // 길이의 최솟값
    int left = 0;       // 투 포인터 (left)
    int right = 0;      // 투 포인터 (right)
    int sum = v[0];     // 부분합 저장

    while (left <= right && right < v.size()) {
        // 합이 m보다 작은 경우 (오른쪽 이동)
        if (sum < m) {
            right++;
            sum += v[right];
        }
        // 합이 m 이상인 경우 (왼쪽 이동)
        else if (sum >= m) {
            // 길이 갱신
            int len = right-left+1;
            if (answer == 0 || answer > len) {
                answer = len;
            }
            sum -= v[left];
            left++;

            if (left > right && left < v.size()) {
                right = left;
                sum = v[left];
            }
        }
    }
    return answer;
}

int main() {
    int n, m;
    cin >> n >> m;

    vector<int> v(n);

    for (int i = 0; i < n; i++) {
        cin >> v[i];
    }

    int ans = solution(v, m);
    cout << ans << '\n';

    return 0;
}