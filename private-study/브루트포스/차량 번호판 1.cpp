#include <iostream>
#include <string>
using namespace std;

void input(string& s) {
    cin >> s;
}

int solution(string s) {
    int ans = 1;
    for (int i = 0; i < s.length(); i++) {
        int cnt = s[i] == 'c' ? 26 : 10;

        if (i > 0 && s[i] == s[i-1]) {
            cnt--;
        }
        
        ans *= cnt;
    }
    return ans;
}

int main() {
    string s;
    input(s);
    int answer = solution(s);
    cout << answer << '\n';
    return 0;
}