#include <iostream>
#include <string>
#include <vector>
#include <climits>
using namespace std;


struct Term {
    int num;
    int op;
};

int solution(int n, string s) {
    vector<Term> v;

    for (int i = 0; i < n; i++) {
        if (s[i] - '0' >= 0 && s[i] - '0' <= 9) {
            v.push_back({s[i] - '0', 0});
        }
        else if (s[i] == '+') {
            v.push_back({0, 1});
        }
        else if (s[i] == '-') {
            v.push_back({0, 2});
        }
        else if (s[i] == '*') {
            v.push_back({0, 3});
        }
    }

    int m = (s.length() - 1) / 2;

    int answer = INT_MIN;
    for (int i = 0; i < (1 << m); i++) {
        bool ok = true;

        for (int j = 0; j < m-1; j++) {
            if ((i & (1 << j)) != 0 && (i & (1 << (j+1))) != 0) {
                ok = false;
                break;
            }
        }
    
        if (!ok) {
            continue;
        }

        vector<Term> temp(v);
        for (int j = 0; j < m; j++) {

            if (((i & (1 << j)) != 0)) {
                int k = 2*j + 1;
                if (temp[k].op == 1) {
                    temp[k-1].num += temp[k+1].num;
                    temp[k+1].num = 0;
                }
                else if (temp[k].op == 2) {
                    temp[k-1].num -= temp[k+1].num;
                    temp[k].op = 1;
                    temp[k+1].num = 0;
                }
                else {
                    temp[k-1].num *= temp[k+1].num;
                    temp[k].op = 1;
                    temp[k+1].num = 0;
                }
            }
        }
        
        int tmp = temp[0].num;
        for (int j = 0; j < m; j++) {
            int k = 2*j + 1;

            if (temp[k].op == 1) {
                tmp += temp[k+1].num;
            }
            else if (temp[k].op == 2) {
                tmp -= temp[k+1].num;
            }
            else {
                tmp *= temp[k+1].num;
            }
        }

        if (tmp > answer) {
            answer = tmp;
        }
    }
    return answer;
}

int main() {
    int n;
    string s;

    cin >> n >> s;
    int answer = solution(n, s);

    cout << answer << '\n';

    return 0;
}