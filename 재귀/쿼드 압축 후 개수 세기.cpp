#include <string>
#include <vector>

using namespace std;

void DFS(int offSetX, int offSetY, int size, vector<vector<int>>& arr, vector<int>& answer) {
    bool zero = true;
    bool one = true;
    
    for (int i = offSetX; i < offSetX + size; i++) {
        for (int j = offSetY; j < offSetY + size; j++) {
            if (arr[i][j] == 0) {
                one = false;
            }
            if (arr[i][j] == 1) {
                zero = false;
            }
        }
    }
    
    if (zero) {
        answer[0]++;
        return;
    }
    if (one) {
        answer[1]++;
        return;
    }
    
    DFS(offSetX, offSetY, size/2, arr, answer);
    DFS(offSetX, offSetY+size/2, size/2, arr, answer);
    DFS(offSetX+size/2, offSetY, size/2, arr, answer);
    DFS(offSetX+size/2, offSetY+size/2, size/2, arr, answer);
}

vector<int> solution(vector<vector<int>> arr) {
    vector<int> answer(2,0);
    DFS(0, 0, arr.size(), arr, answer);
    return answer;
}