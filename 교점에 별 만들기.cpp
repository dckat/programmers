#include <string>
#include <vector>
#include <set>
#include <algorithm>

using namespace std;

vector<string> solution(vector<vector<int>> line) {
    vector<string> answer;
    set<pair<long long, long long>> points;
    
    for (int i = 0; i < line.size(); i++) {
        for(int j = i+1; j < line.size(); j++) {
            long long AD_BC = (long long)line[i][0]*line[j][1] - (long long)line[i][1]*line[j][0];
            
            if (AD_BC != 0) {
                double x = ((double)line[i][1]*line[j][2] - (double)line[i][2]*line[j][1])/AD_BC;
                double y = ((double)line[i][2]*line[j][0] - (double)line[i][0]*line[j][2])/AD_BC;
    
                if (x - (long long)x == 0 && y - (long long)y == 0) {
                    points.insert({x, y});
                }
            }
        }
    }
    
    long long minX = points.begin()->first;
    long long maxX = points.begin()->first;
    long long minY = points.begin()->second;
    long long maxY = points.begin()->second;
    
    for (auto s: points) {
        if (minX > s.first) {
            minX = s.first;
        }
        if (minY > s.second) {
            minY = s.second;
        }
        if (maxX < s.first) {
            maxX = s.first;
        }
        if (maxY < s.second) {
            maxY = s.second;
        }
    }
    
    for (long long y = minY; y <= maxY; y++) {
        string tmp = "";
        for (long long x = minX; x <= maxX; x++) {
            tmp += ".";
        }
        answer.push_back(tmp);
    }
    
    for (auto p: points) {
        answer[abs(p.second - maxY)][abs(p.first - minX)] = '*';
    }
    return answer;
}