#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

char alpha[256];

int calc (vector<string>& v, vector<char>& letters, vector<int>& d) {
    int result = 0;

    for (int i = 0; i < letters.size(); i++) {
        alpha[letters[i]] = d[i];
    }

    for (int i = 0; i < v.size(); i++) {
        int cur = 0;
        for (int j = 0; j < v[i].size(); j++) {
            cur = 10*cur + alpha[v[i][j]];
        }
        result += cur;
    }
    return result;
}


int main() {
    int N;
    int answer = 0;
    cin >> N;
    vector<string> v(N);
    vector<char> letters;

    for (int i = 0; i < N; i++) {
        cin >> v[i];

        for (int j = 0; j < v[i].length(); j++) {
            letters.push_back(v[i][j]);
        }
    }
    sort(letters.begin(), letters.end());
    letters.erase(unique(letters.begin(), letters.end()), letters.end());
    
    vector<int> d(letters.size());
    
    for (int i = 0; i < letters.size(); i++) {
        d[i] = 9-i;
    }
    sort(d.begin(), d.end());

    do {
        int temp = calc(v, letters, d);
        if (answer < temp) {
            answer = temp;
        }

    } while (next_permutation(d.begin(), d.end()));

    cout << answer << '\n';
}