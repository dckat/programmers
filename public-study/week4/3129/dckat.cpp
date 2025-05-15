#include <iostream>
#include <string>
using namespace std;

int main() {
    string eMessage;
    string pMessage;
    string answer = "";
    string key = "";

    cin >> eMessage >> pMessage;

    int len1 = eMessage.length();
    int len2 = pMessage.length();

    // 암호화 되지 않은 일부분을 암호화 된 메시지와 연산하며 키 찾기
    for (int i = 0; i < (len1 - len2) + 1; i++) {
        string subMessage = eMessage.substr(i, len2);
        string tKey = "";   // 후보가 될 키 값을 임시저장
        bool flag = false;  // 후보가 될 키 값을 최종적으로 찾았는지 확인

        // slicing 한 일부분을 계산하여 연산을 위한 키의 일부 추출
        for (int j = 0; j < len2; j++) {
            char c = subMessage[j] - pMessage[j] + 'a';
            if (c < 'a') {
                c += 26;
            }
            tKey += c;
        }

        // 뽑아낸 키의 일부에서 반복자가 있는 것을 찾아서 키값 알아내기 
        // 메시지 일부는 항상 키 길이의 2배이상 ==> 문제 조건
        // j: 키의 길이 (될 수 있는 키 길이: 1 ~ 메시지 일부의 길이의 절반)
        for (int j = 1; j <= len2 / 2; j++) {
            int tmp = 0;
            string tmp1, tmp2;      // 순환을 만족하는 지 확인하기 위한 임시 문자열 2개
            for (int k = 0; k < (len2 / j) - 1; k++) {
                tmp1 = tKey.substr(k, j);
                tmp2 = tKey.substr(k+j, j);

                if (tmp1 == tmp2) {
                    tmp++;
                }
                else {
                    break;
                }
            }
            // 순환관계를 만족하는 키값을 찾은 경우
            if (tmp == (len2 / j) - 1) {
                flag = true;
            }
            // 최종적으로 key값을 저장해주고 반복문 탈출
            if (flag) {
                // 암호화된 메시지와 키의 인덱스를 맞추기 위해 키값 재조정
                for (int k = 0; k < j; k++) {
                    if ((i + k) % tmp1.length() == 0) {
                        key = tKey.substr(k, tmp1.length());
                        break;
                    }
                }
                break;
            }
        }
    }

    // 최종적으로 찾은 키로 암호화된 키를 복호화하여 풀기
    for (int i = 0; i < eMessage.length(); i++) {
        char c = eMessage[i] - key[i%key.length()] + 'a';
        if (c < 'a') {
            c += 26;
        }
        answer += c;
    }
    cout << answer << '\n';

    return 0;
}