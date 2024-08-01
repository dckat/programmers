#include <iostream>
#include <vector>
#include <string>
using namespace std;

int count (int mask, vector<int> words) {
    int cnt = 0;

    for (int word: words) {
        if ((word & (1 << 26) - 1 - mask) == 0) {
            cnt++;
        }
    }
    return cnt;
}

int solution(int index, int k, int mask, vector<int> words) {
    if (k < 0) return 0;
    if (index == 26) {
        return count(mask, words);
    }

    int ans = 0;
    int t1 = solution(index+1, k-1, mask | (1 << index), words);
    if (ans < t1) {
        ans = t1;
    }

    if (index != 'a' - 'a' && index != 'c' - 'a' && index != 'i' - 'a' &&
    index != 'n' - 'a' && index != 't' - 'a') {
        t1 = solution(index+1, k, mask, words);
        if (ans < t1) {
            ans = t1;
        }
    }

    return ans;
}


int main() {
    int N, K;
    cin >> N >> K;
    vector<int> words(N);

    for (int i = 0; i < N; i++) {
        string s;
        cin >> s;

        for (char c: s) {
            words[i] |= (1 << (c - 'a'));
        }
    }

    cout << solution(0, K, 0, words) << '\n';

    return 0;
}