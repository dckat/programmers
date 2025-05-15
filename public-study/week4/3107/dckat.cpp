#include <iostream>
#include <sstream>
#include <string>
#include <vector>
using namespace std;

void addColon(string& s, int cnt) {
    if (cnt != 8) {
        s += ":";
    }
}


vector<string> split(string s) {
    vector<string> result;
    istringstream ss(s);
    string buffer;

    // : 기준으로 split
    while (getline(ss, buffer, ':'))
        result.push_back(buffer);

    return result;
}

int main() {
    string input;
    cin >> input;

    vector<string> v = split(input);
    string answer = "";

    int cnt = 0;    // 현재 채워진 4자리 단위 갯수
    for (int i = 0; i < v.size(); i++) {
        // ::인 경우 추후 0000이 안나오는 위치까지 0000을 추가 (cnt로 확인 가능)
        if (v[i].length() == 0) {
            for (int j = 8-cnt; j > v.size() - (i+1); j--) {
                answer += "0000";
                cnt++;
                addColon(answer, cnt);
            }
        }
        else {
            int len = v[i].length();
            for (int i = 0; i < 4-len; i++) {
                answer += "0";
            }
            answer += v[i];
            cnt++;
            addColon(answer, cnt);
        }
    }

    cout << answer << '\n';
    return 0;
}