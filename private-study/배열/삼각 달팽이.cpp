#include <string>
#include <vector>

using namespace std;

vector<int> solution(int n) {
    vector<int> answer;
    vector<vector<int>> triangle(n, vector<int>(n,0));
    
    int dx[3] = {0, 1, -1};
    int dy[3] = {1, 0, -1};
    
    int v = 1;
    int x = 0;
    int y = 0;
    int d = 0;
    
    while (true) {
        triangle[y][x] = v;
        int nx = x + dx[d];
        int ny = y + dy[d];
        
        if (nx == n || ny == n || nx == -1 || ny == -1 || triangle[ny][nx] != 0) {
            d = (d + 1) % 3;
            nx = x + dx[d];
            ny = y + dy[d];
            
            if (nx == n || ny == n || nx == -1 || ny == -1 || triangle[ny][nx] != 0) {
                break;
            }
        }
        x = nx;
        y = ny;
        v++;
    }
    
    for (int i = 0; i < n; i++) {
        for (int j = 0; j <= i; j++) {
            answer.push_back(triangle[i][j]);
        }
    }
    return answer;
}