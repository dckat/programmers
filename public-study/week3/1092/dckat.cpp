#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int main() {
    int n, m;
    int answer = 0;
    cin >> n;

    vector<int> crane(n);
    for (int i = 0; i < n; i++) {
        cin >> crane[i];
    }

    cin >> m;

    vector<int> box(m);
    for (int i = 0; i < m; i++) {
        cin >> box[i];
    }

    // 박스와 크레인 무게제한을 내림차순 정렬
    sort(box.begin(), box.end(), greater<int>());
    sort(crane.begin(), crane.end(), greater<int>());

    // 박스를 다 들수 없는 경우 (무거운 박스 > 가장 큰 무게제한의 크레인)
    if (box[0] > crane[0]) {
        cout << -1 << '\n';
        return 0;
    }

    while (!box.empty()) {
        answer++;   // 동시에 1번에 하나씩 옮기므로 이곳에 결과값이 1씩 추가
        for (int i = 0; i < crane.size(); i++) {
            for (int j = 0; j < box.size(); j++) {
                if (crane[i] >= box[j]) {
                    box.erase(box.begin() + j);
                    break;
                }
            }
        }
    }
    cout << answer << '\n';
    return 0;
}