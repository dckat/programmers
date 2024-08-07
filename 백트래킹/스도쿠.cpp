#include <iostream>
using namespace std;

int a[9][9];
bool c[10][10];
bool c2[10][10];
bool c3[10][10];

int square(int x, int y) {
    return (x/3)*3 + (y/3);
}

bool go(int z) {
    if (z == 81) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                cout << a[i][j] << ' ';
            }
            cout << '\n';
        }
        return true;
    }

    int x = z/9;
    int y = z%9;

    if (a[x][y] != 0) {
        return go(z+1);
    }
    else {
        for (int i = 1; i <= 9; i++) {
            if (c[x][i] == 0 && c2[y][i] == 0 && c3[square(x,y)][i] == 0) {
                c[x][i] = true;
                c2[y][i] = true;
                c3[square(x,y)][i] = true;
                a[x][y] = i;

                if (go(z+1)) {
                    return true;
                }
                
                a[x][y] = 0;
                c[x][i] = false;
                c2[y][i] = false;
                c3[square(x,y)][i] = false;
            }
        }
    }
    return false;
}

int main() {
    for (int i = 0; i < 9; i++) {
        for (int j = 0; j < 9; j++) {
            cin >> a[i][j];

            if (a[i][j] != 0) {
                c[i][a[i][j]] = true;
                c2[j][a[i][j]] = true;
                c3[square(i,j)][a[i][j]] = true;
            }
        }
    }
    go(0);
    return 0;
}