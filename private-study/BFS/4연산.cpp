#include <iostream>
#include <queue>
#include <set>
using namespace std;

const long long limit = 1000000000L;

int main() {
    long long s, t;
    set<long long> check;
    cin >> s >> t;

    queue<pair<long long, string>> q;
    q.push({s, ""});
    check.insert(s);

    while(!q.empty()) {
        long long cur = q.front().first;
        string s = q.front().second;
        q.pop();

        if (cur == t) {
            if (s.length() == 0) {
                cout << 0 << '\n';
            }
            cout << s << '\n';
            return 0;
        }

        if (0 <= cur*cur && cur*cur <= limit && check.count(cur*cur) == 0) {
            q.push({cur*cur, s+'*'});
            check.insert(cur*cur);
        }
        if (0 <= cur+cur && cur+cur <= limit && check.count(cur+cur) == 0) {
            q.push({cur+cur, s+'+'});
            check.insert(cur+cur);
        }
        if (0 <= cur-cur && cur-cur <= limit && check.count(cur-cur) == 0) {
            q.push({cur-cur, s+'-'});
            check.insert(cur-cur);
        }
        if (cur != 0 && 0 <= cur/cur && cur/cur <= limit && check.count(cur/cur) == 0) {
            q.push({cur/cur, s+'/'});
            check.insert(cur/cur);
        }
    }
    cout << -1 << '\n';
    return 0;
}