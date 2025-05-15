#include <iostream>
#include <string>
#include <map>
using namespace std;

// 연속으로 등장할 수 있는 로마숫자 처리 (I, X, C, M)
void addCharCnt(string& ans, int cnt, string c) {
    while (cnt--) {
        ans += c;
    }
}

int convert(string s) {
    int ans = 0;

    // 
    map<char, int> m = {
        {'I', 1},
        {'V', 5},
        {'X', 10},
        {'L', 50},
        {'C', 100},
        {'D', 500},
        {'M', 1000}
    };
    
    for (int i = 0; i < s.length(); i++) {
        // 뒤에 큰 알파벳이 오는 경우 (문제 3번 조건)
        if (i+1 < s.length() && m[s[i]] < m[s[i+1]]) {
            ans -= m[s[i]];
        }
        else {
            ans += m[s[i]];
        }
    }
    return ans;
}

int main() {
    string a, b;
    cin >> a >> b;

    int sum = convert(a) + convert(b);
    cout << sum << '\n';

    string sSum = "";
    int cnt;    // 몫을 확인하기 위한 변수

    // 1000의 자리
    if (sum / 1000 != 0) {
        cnt = sum / 1000;
        // 1000
        addCharCnt(sSum, cnt, "M");
        sum %= 1000;
    }

    // 100의 자리
    if (sum / 100 != 0) {
        cnt = sum / 100;
        // 900
        if (cnt == 9) {
            sSum += "CM";
        }
        // 500
        else if (cnt == 5) {
            sSum += "D";
        }
        // 600 ~ 800
        else if (cnt > 5) {
            sSum += "D";
            cnt -= 5;
            addCharCnt(sSum, cnt, "C");
        }
        // 400
        else if (cnt == 4) {
            sSum += "CD";
        }
        // 100 ~ 300
        else {
            addCharCnt(sSum, cnt, "C");
        }
        sum %= 100;
    }

    // 10의 자리
    if (sum / 10 != 0) {
        cnt = sum / 10;
        // 90
        if (cnt == 9) {
            sSum += "XC";
        }
        // 50
        else if (cnt == 5) {
            sSum += "L";
        }
        // 60 ~ 80
        else if (cnt > 5) {
            sSum += "L";
            cnt -= 5;
            addCharCnt(sSum, cnt, "X");
        }
        // 40
        else if (cnt == 4) {
            sSum += "XL";
        }
        // 10 ~ 30
        else {
            addCharCnt(sSum, cnt, "X");
        }
        sum %= 10;
    }
    // 1의 자리
    cnt = sum;
    // 9
    if (cnt == 9) {
        sSum += "IX";
    }
    // 5
    else if (cnt == 5) {
        sSum += "V";
    }
    // 6 ~ 8
    else if (cnt > 5) {
        sSum += "V";
        cnt -= 5;
        addCharCnt(sSum, cnt, "I");
    }
    // 4
    else if (cnt == 4) {
        sSum += "IV";
    }
    // 1 ~ 3
    else {
        addCharCnt(sSum, cnt, "I");
    }

    cout << sSum << '\n';
    return 0;
}