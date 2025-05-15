#include <iostream>
#include <vector>
#include <queue>
using namespace std;

int main() {
    int n;
    int answer = 0;
    cin >> n;

    vector<int> v(n, 0);
    priority_queue<int> pq;
    for (int i = 0; i < n; i++) {
        cin >> v[i];
        pq.push(-v[i]);
    }

    // 한 묶음만 남을때까지 비교 작업 수행
    while (pq.size() != 1) {
        int n1, n2;         // 가장 적은 수의 카드갯수 2개
        
        // pq가 maxHeap 기반이라 음수 처리 필요 (greater 처리해서 minHeap 기반도 가능)
        // 가장 적은 갯수의 2개를 pop
        n1 = -pq.top();
        pq.pop();
        n2 = -pq.top();
        pq.pop();

        // 더한 값을 결과값에 저장하고 pq에도 push
        answer += (n1 + n2);
        pq.push(-(n1+n2));

    }

    cout << answer << '\n';
    return 0;
}