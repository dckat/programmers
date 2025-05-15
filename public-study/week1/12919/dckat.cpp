#include <iostream>
#include <string>
#include <algorithm>
using namespace std;

bool flag = false;

// t에서 알파벳을 제거하여 s가 되는 지 확인하는 함수 (재귀)
void check(string s, string t) {
    string newT = "";   // t를 갱신시키기 위해 활용하는 변수

    // 같은 경우를 찾았으면 더 이상 비교를 실행하지 않도록 return
    if (flag == true) {
        return;
    }
    // 길이가 같은 경우 s와 t를 비교
    if (s.length() == t.length()) {
        if (s == t) {
            flag = true;
        }
        return;
    }

    // 경우 1: 맨 뒤에 A가 추가된 경우
    if (t[t.length() - 1] == 'A') {
        newT = t;
        newT.erase(newT.size() - 1);
        check(s, newT);
    }
    // 경우 2: 맨 뒤에 B를 추가하고 뒤집은 경우
    if (t[0] == 'B') {
        newT = t;
        newT.erase(newT.begin());
        reverse(newT.begin(), newT.end());
        check(s, newT);
    }
}

int main() {
    string s,t;
    cin >> s >> t;

    check(s, t);

    cout << flag << '\n';
    return 0;
}