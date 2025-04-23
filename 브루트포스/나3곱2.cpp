#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int getThree(long long num) {
    long long tmp = num;

    int cnt = 0;
    while (tmp > 0) {
        if (tmp % 3 != 0) {
            break;
        }
        tmp /= 3;
        cnt++;
    }

    return cnt;
}

bool cmp(pair<int, long long>& p1, pair<int, long long>& p2) {
    if (p1.first == p2.first) {
        return p1.second < p2.second;
    }
    return p1.first > p2.first;
}

int main() {
    int n;
    cin >> n;

    vector<pair<int, long long>> v(n);
    for (int i = 0; i < n; i++) {
        cin >> v[i].second;
        v[i].first = getThree(v[i].second);
    }

    sort(v.begin(), v.end(), cmp);

    for (int i = 0; i < n; i++) {
        cout << v[i].second << ' ';
    }
    cout << '\n';

    return 0;
}