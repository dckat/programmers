#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

pair<int, int> solution(vector<int>& a, int index, int cur, int plus, int minus, int mul, int div) {
    if (index == a.size()) {
        return make_pair(cur, cur);
    }
    vector<pair<int, int>> res;
    if (plus > 0) {
        res.push_back(solution(a, index+1, cur+a[index], plus-1, minus, mul, div));
    }
    if (minus > 0) {
        res.push_back(solution(a, index+1, cur-a[index], plus, minus-1, mul, div));
    }
    if (mul > 0) {
        res.push_back(solution(a, index+1, cur*a[index], plus, minus, mul-1, div));
    }
    if (div > 0) {
        res.push_back(solution(a, index+1, cur/a[index], plus, minus, mul, div-1));
    }

    pair<int, int> ans = res[0];

    for (int i = 1; i < res.size(); i++) {
        if (ans.first < res[i].first) {
            ans.first = res[i].first;
        }
        if (ans.second > res[i].second) {
            ans.second = res[i].second;
        }
    }
    return ans;
}


int main() {
    int N, num;
    int plus, minus, mul, div;
    vector<int> a;
    cin >> N;

    for (int i = 0; i < N; i++) {
        cin >> num;
        a.push_back(num);
    }

    cin >> plus >> minus >> mul >> div;

    pair<int, int> p = solution(a, 1, a[0], plus, minus, mul, div);

    cout << p.first << '\n';
    cout << p.second << '\n';
    
    return 0;
}