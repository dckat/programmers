#include <iostream>
#include <vector>
using namespace std;

bool check[2000001];
vector<int> A;
int N;

void solution(int index, int sum) {
    if (index == N) {
        check[sum] = true;
        return;
    }
    solution(index+1, sum+A[index]);
    solution(index+1, sum);
}

int main() {
    int num;
    cin >> N;

    for (int i = 0; i < N; i++) {
        cin >> num;
        A.push_back(num);
    }
    solution(0, 0);

    for (int i = 1;;i++) {
        if (!check[i]) {
            cout << i << '\n';
            break;
        }
    }

    return 0;
}