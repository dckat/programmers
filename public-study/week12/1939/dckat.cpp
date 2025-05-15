#include <iostream>
#include <vector>
#include <cstring>
#include <queue>
using namespace std;

int n, m;
bool visited[10001];                    // 각 섬의 방문여부 (그래프 이론 활용)
vector<pair<int, int>> bridge[10001];   // 각 섬의 인접정보 저장 (first: 인접 섬, second: 중량제한)
int source, target;     // 시작점. 도착점

// 이동 가능 여부 파악
// 1. 이미 방문했는지 확인
// 2. 다리의 한계중량이 설정된 한계중량보다 크거나 같은지 확인
bool canMove(pair<int, int> next, int weight) {
    if (!visited[next.first] && next.second >= weight) {
        return true;
    }
    return false;
}

// 입력 함수
void input() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> n >> m;

    for (int i = 0; i < m; i++) {
        int a, b, c;
        cin >> a >> b >> c;

        // 양방향이므로 a, b에 대한 연결정보 추가
        bridge[a].push_back({b, c});
        bridge[b].push_back({a, c});
    }
    cin >> source >> target;
}

// 한계중량 구하는 함수
int solution() {
    // 한계중량의 이분탐색을 위한 변수
    int min = 0;
    int max = 1e9 + 1;
    int mid;

    int result;     // return value
    bool flag;      // 설정된 한계중량으로 섬 도달이 가능한지 확인 flag

    // 1. 이분탐색을 활용하여 최대의 한계중량 탐색
    // 2. BFS를 통해 최단거리로 도달 가능한 경로 탐색
    // 위의 1과 2의 과정을 계속 반복
    while (min <= max) {
        flag = false;
        memset(visited, false, sizeof(visited));    // 방문 여부 초기화
        mid = (min + max) / 2;

        queue<int> q;
        q.push(source);
        visited[source] = true;

        while (!q.empty()) {
            int cur = q.front();
            q.pop();

            // 도착점
            if (cur == target) {
                flag = true;
                break;
            }

            // 현재 섬에서 이동가능한 섬 탐색
            for (int i = 0; i < bridge[cur].size(); i++) {
                pair<int, int> next = bridge[cur][i];
                if (canMove(next, mid)) {
                    q.push(next.first);
                    visited[next.first] = true;
                }
            }
        }
        // 설정된 중량으로 이동 가능한 경우
        // return value를 갱신하고 min 변경 (더 큰값이 있는지 확인)
        if (flag) {
            result = mid;
            min = mid + 1;
        }
        // 이동이 불가능한 경우
        else {
            max = mid - 1;
        }
    }
    return result;
}

int main() {
    input();
    int answer = solution();
    cout << answer << '\n';
    return 0;
}