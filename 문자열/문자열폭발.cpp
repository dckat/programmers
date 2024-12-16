#include <iostream>
#include <string>
#include <stack>
#include <algorithm>
using namespace std;

int main() {
    string a, b;
    string result;
    cin >> a >> b;

    stack<char> s;

    for (int i = 0; i < a.length(); i++) {
        s.push(a[i]);

        if (s.top() == b[b.length() - 1] && s.size() >= b.length()) {
            string temp = "";
            while (temp.length() != b.length()) {
                temp += s.top();
                s.pop();
            }
            reverse(temp.begin(), temp.end());
            if (temp != b) {
                for (int j = 0; j < temp.length(); j++) {
                    s.push(temp[j]);
                }
            }
        }
    }

    if (s.empty()) {
        cout << "FRULA" << '\n';
        return 0;
    }
    while (!s.empty()) {
        result += s.top();
        s.pop();
    }
    reverse(result.begin(), result.end());
    cout << result << '\n';

    return 0;
}