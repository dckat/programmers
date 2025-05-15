#include <iostream>
#include <vector>
#include <string>
#include <cstring>
using namespace std;

// 트라이 구조체
struct Trie {
    bool finish;    // 끝나는 지점
    Trie* next[10]; // 0~9 숫자 트라이

    Trie() {
        finish = false;
        memset(next, NULL, sizeof(next));
    }

    // 트라이에 하나씩 삽입
    void insert(char* str) {
        // 마지막 문자에 다다른 경우
        if (*str == '\0') {
            finish = true;
            return;
        }
        else {
            int cur = *str - '0';
            if (next[cur] == NULL) {
                next[cur] = new Trie();
            }
            next[cur]->insert(str + 1);
        }
    }

    // 일관성 검사 (true: 일관성 없음. false: 일관성 있음.)
    bool find(char* str) {
        // 트라이 끝에 다다른 경우
        if (*str == '\0') {
            return false;
        }
        // 중복된 요소가 존재
        if (finish == true) {
            return true;
        }

        int cur = *str - '0';
        if (next[cur] == NULL) {
            return false;
        }
        return next[cur]->find(str+1);
    }
};

int t, n;
char numbers[10000][11];

void input() {
    cin >> n;

    for (int i = 0; i < n; i++) {
        cin >> numbers[i];
    }
}

void solution() {
    Trie* root = new Trie();
    bool flag = true;

    for (int i = 0; i < n; i++) {
        root->insert(numbers[i]);
    }

    for (int i = 0; i < n; i++) {
        if (root->find(numbers[i])) {
            flag = false;
            break;
        }
    }

    if (!flag) {
        cout << "NO" << '\n';
    }
    else {
        cout << "YES" << '\n';
    }
}

void loop() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> t;
    
    while (t--) {
        input();
        solution();
    }
}

int main() {
    loop();
    return 0;
}