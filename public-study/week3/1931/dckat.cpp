#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

// 종료시간을 기준으로 정렬
bool cmp(pair<int, int> p1, pair<int, int> p2) {
    // 종료시간이 같으면 시작시간 기준으로 정렬
    if (p1.second == p2.second) {
        return p1.first < p2.first;
    }
    return p1.second < p2.second;
}

int main() {
    int n;
    cin >> n;
    vector<pair<int, int>> v(n);
    
    for (int i = 0; i < n; i++) {
        cin >> v[i].first >> v[i].second;
    }
    sort(v.begin(), v.end(), cmp);

    int end = v[0].second;
    int result = 1;
    for (int i = 1; i < n; i++) {
        if (v[i].first >= end) {
            end = v[i].second;
            result++;
        }
    }

    cout << result << '\n';
    return 0;
}