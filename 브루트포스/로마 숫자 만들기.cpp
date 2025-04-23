#include <iostream>
using namespace std;

bool check[1001];

int solution(int n) {
    for (int i = 0; i <= n; i++) {
        for (int j = 0; j <= n-i; j++) {
            for (int k = 0; k <= n-i-j; k++) {
                int l = n-i-j-k;
                int num = i + 5*j + 10*k + 50*l;
                check[num] = true;
            }
        }
    }
    
    int answer = 0;
    for (int i = 1; i <= 1000; i++) {
        if (check[i]) {
            answer++;
        }
    }
    return answer;
}

int main() {
    int n;
    cin >> n;

    int answer = solution(n);
    cout << answer << '\n';

    return 0;
}