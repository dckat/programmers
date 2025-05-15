#include <iostream>
#include <vector>
using namespace std;

vector<vector<int>> v(201, vector<int>(201));   // 도시 연결 입력 vector     
vector<int> cons[201];      // 연결된 도시 정보
vector<int> cities(1001);  // 여행 계획
vector<bool> visited(201, false);    // 방문여부 확인

// DFS로 방문 가능한 도시들을 탐색
void trip(int city) {
    visited[city] = true;

    // 현재 도시에서 연결된 다른 도시들을 확인
    for (int i = 0; i < cons[city].size(); i++) {
        int next = cons[city][i];
        if (!visited[next]) {
            trip(next);
        }
    }
}

int main() {
    int n, m;
    cin >> n >> m;

    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= n; j++) {
            cin >> v[i][j];
            // 1이면 경로가 cons에 추가
            if (v[i][j] == 1) {
                cons[i].push_back(j);
            }
        }
    }
    
    for (int i = 1; i <= m; i++) {
        cin >> cities[i];
    }
    
    // 첫 번째 도시부터 여행 시작 (DFS)
    trip(cities[1]);

    for (int i = 1; i <= m; i++) {
        int city = cities[i];

        // 방문하지 못한 도시가 하나라도 있으면 나올수 없는 여행계획
        // NO 출력하고 종료
        if (!visited[city]) {
            cout << "NO" << '\n';
            return 0;
        }
    }
    cout << "YES" << '\n';
    return 0;
}