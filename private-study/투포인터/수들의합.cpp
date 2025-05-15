#include <iostream>
#include <vector>
using namespace std;

int solution(vector<int> v, int m) {
    int answer = 0;
    int left = 0;
    int right = 0;
    int sum = v[0];

    while (left <= right && right < v.size()) {
        if (sum < m) {
            right++;
            sum += v[right];
        }
        else if (sum == m) {
            answer++;
            right++;
            sum += v[right];
        }
        else if (sum > m) {
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