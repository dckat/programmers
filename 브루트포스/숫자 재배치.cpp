#include <iostream>
#include <vector>
#include <string>
using namespace std;

vector<int> a;
int b;
bool check[9];

int solution(int index, int num) {
    if (index == a.size()) {
        return num;
    }
    int ans = -1;

    for (int i = 0; i < a.size(); i++) {
        if (check[i]) {
            continue;
        }
        if (index == 0 && a[i] == 0) {
            continue;
        }

        check[i] = true;
        int temp = solution(index+1, num*10 + a[i]);

        if (temp < b) {
            if (ans == -1 || ans < temp) {
                ans = temp;
            }
        }
        check[i] = false;
    }
    return ans;
}

int main() {
    string s;
    cin >> s >> b;

    for (int i = 0; i < s.length(); i++) {
        a.push_back(s[i] - '0');
    }

    int answer = solution(0, 0);
    cout << answer << '\n';

    return 0;
}