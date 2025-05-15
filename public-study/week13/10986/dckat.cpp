#include <iostream>
#include <vector>
using namespace std;

long long N, M;
vector<long long> v(1000001);       // 입력 정보
vector<long long> s(1000001);       // 합 정보
vector<long long> m(1001);          // 같은 나머지 값을 가지는 경우의 수

void input() {
    cin >> N >> M;

    for (int i = 1; i <= N; i++) {
        cin >> v[i];
    }
}

// 나머지가 같은 경우의 수에서 2개를 선택한 조합의 갯수 반환
// ex: i번째 누적합과 j번째 누적합의 나머지가 k로 동일한 경우
//     ==> s[j] - s[i]의 누적 합의 나머지는 0으로 나누어 떨어짐.
// ==> 나머지가 같게 나오는 경우들 중에서 2개 선택하면 해당 구간은 반드시 나누어 떨어짐.
long long calc(long long num) {
    return (num * (num - 1)) / (long long)2;
}

void solution() {
    long long answer = 0;       // 결과값

    // 현재 인덱스 별 누적합의 나머지를 구하는 반복문
    for (int i = 1; i <= N; i++) {
        s[i] = s[i-1] + v[i];
        int mod = s[i] % M;

        // 나누어 떨어지는 경우 결과값 1 증가
        if (mod == 0) {
            answer++;
        }
        m[mod]++;
    }

    for (int i = 0; i <= 1000; i++) {
        if (m[i] >= 2) {
            answer += calc(m[i]);
        }
    }

    cout << answer << '\n';
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    input();
    solution();
    return 0;
}