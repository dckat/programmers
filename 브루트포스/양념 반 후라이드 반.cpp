#include <iostream>
#include <algorithm>
using namespace std;

void input(int& a, int& b, int& c, int& x, int& y) {
    cin >> a >> b >> c >> x >> y;
}

int solution(int a, int b, int c, int x, int y) {
    int answer = 0;
    int num = max(x, y);

    for (int i = 0; i <= num; i++) {
        int num1 = 2*i;
        int num2 = x-i > 0 ? x-i : 0;
        int num3 = y-i > 0 ? y-i : 0;

        int tmp = num1*c + num2*a + num3*b;

        if (answer == 0 || answer > tmp) {
            answer = tmp;
        }
    }
    return answer;
}

int main() {
    int a, b, c, x, y;
    input(a, b, c, x, y);
    int answer = solution(a, b, c, x, y);
    cout << answer << '\n';
    return 0;
}