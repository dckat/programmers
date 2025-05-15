#include <iostream>
#include <vector>
using namespace std;

bool check[4000001];

vector<int> make_prime(int n) {
    vector<int> prime;
    check[0] = check[1] = true;

    for (int i = 2; i <= 4000000; i++) {
        if (!check[i]) {
            for (int j = 2*i; j <= 4000000; j+=i) {
                check[j] = true;
            }
        }
    }

    for (int i = 2; i <= 4000000; i++) {
        if (!check[i]) {
            prime.push_back(i);
        }
    }

    return prime;
}

int solution(vector<int> v, int n) {
    int answer = 0;
    int left = 0;
    int right = 0;
    int sum = v[0];

    while (left <= right && right < v.size()) {
        if (sum < n) {
            right++;
            sum += v[right];
        }
        else if (sum == n) {
            answer++;
            right++;
            sum += v[right];
        }
        else if (sum > n) {
            sum -= v[left];
            left++;
            
            if (left > right && left < v.size()) {
                right = left;
                sum = v[left];
            }
        }
    }

    return answer;
}

int main() {
    int n;
    cin >> n;

    vector<int> v = make_prime(n);

    int ans = solution(v, n);
    cout << ans << '\n';

    return 0;
}