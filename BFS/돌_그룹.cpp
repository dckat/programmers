#include <iostream>
#include <queue>
using namespace std;

int a, b, c;
int sum;
bool check[1501][1501];

int bfs() {
    queue<pair<int, int>> q;
    q.push({a,b});
    check[a][b] = true;

    while (!q.empty()) {
        int ca = q.front().first;
        int cb = q.front().second;
        int cc = sum - ca - cb;
        q.pop();

        if (ca == cb && cb == cc) {
            return 1;
        }

        int nx[3] = {ca, ca, cb};
        int ny[3] = {cb, cc, cc};

        for (int i = 0; i < 3; i++) {
            int na = nx[i];
            int nb = ny[i];

            if (na < nb) {
                nb -= na;
                na += na;
            }
            else if (na > nb) {
                na -= nb;
                nb += nb;
            }
            else {
                continue;
            } 
            int nc = sum - na - nb;

            int ra = min(min(na, nb), nc);
            int rb = max(max(na, nb), nc);

            if (!check[ra][rb]) {
                check[ra][rb] = true;
                q.push({ra, rb});
            }
        }
    }
    return 0;
}

int main() {
    cin >> a >> b >> c;
    sum = a + b + c;

    if (sum % 3 != 0) {
        cout << 0 << '\n';
        return 0;
    }

    cout << bfs() << '\n';
    
    return 0;

}