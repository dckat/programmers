#include <iostream>
#include <vector>
using namespace std;

vector<int> cube(25);

bool check(vector<int> cube) {
    for (int i = 0; i < 6; i++) {
        for (int j = 0; j < 4; j++) {
            if (cube[4*i+1] != cube[4*i+(j+1)]) {
                return false;
            }
        }
    }
    return true;
}

vector<int> ul(vector<int> cube) {
    int temp = cube[13];
    cube[13] = cube[5];
    cube[5] = cube[17];
    cube[17] = cube[21];
    cube[21] = temp;

    temp = cube[14];
    cube[14] = cube[6];
    cube[6] = cube[18];
    cube[18] = cube[22];
    cube[22] = temp;

    return cube;
}

vector<int> ur(vector<int> cube) {
    cube = ul(cube);
    cube = ul(cube);
    cube = ul(cube);

    return cube;
}

vector<int> dl(vector<int> cube) {
    int temp = cube[15];
    cube[15] = cube[7];
    cube[7] = cube[19];
    cube[19] = cube[23];
    cube[23] = temp;

    temp = cube[16];
    cube[16] = cube[8];
    cube[8] = cube[20];
    cube[20] = cube[24];
    cube[24] = temp;

    return cube;
}

vector<int> dr(vector<int> cube) {
    cube = dl(cube);
    cube = dl(cube);
    cube = dl(cube);

    return cube;
}

vector<int> lu(vector<int> cube) {
    int temp = cube[1];
    cube[1] = cube[5];
    cube[5] = cube[9];
    cube[9] = cube[24];
    cube[24] = temp;

    temp = cube[3];
    cube[3] = cube[7];
    cube[7] = cube[11];
    cube[11] = cube[22];
    cube[22] = temp;

    return cube;
}

vector<int> ld(vector<int> cube) {
    cube = lu(cube);
    cube = lu(cube);
    cube = lu(cube);

    return cube;
}

vector<int> ru(vector<int> cube) {
    int temp = cube[2];
    cube[2] = cube[6];
    cube[6] = cube[10];
    cube[10] = cube[23];
    cube[23] = temp;

    temp = cube[4];
    cube[4] = cube[8];
    cube[8] = cube[12];
    cube[12] = cube[21];
    cube[21] = temp;

    return cube;
}

vector<int> rd(vector<int> cube) {
    cube = ld(cube);
    cube = ld(cube);
    cube = ld(cube);

    return cube;
}

vector<int> fl(vector<int> cube) {
    int temp = cube[3];
    cube[3] = cube[17];
    cube[17] = cube[9];
    cube[9] = cube[14];
    cube[14] = temp;

    temp = cube[4];
    cube[4] = cube[19];
    cube[19] = cube[10];
    cube[10] = cube[16];
    cube[16] = temp;

    return cube;
}

vector<int> fr(vector<int> cube) {
    cube = fl(cube);
    cube = fl(cube);
    cube = fl(cube);

    return cube;
}

vector<int> bl(vector<int> cube) {
    int temp = cube[1];
    cube[1] = cube[18];
    cube[18] = cube[11];
    cube[11] = cube[13];
    cube[13] = temp;

    temp = cube[2];
    cube[2] = cube[20];
    cube[20] = cube[12];
    cube[12] = cube[15];
    cube[15] = temp;

    return cube;
}

vector<int> br(vector<int> cube) {
    cube = bl(cube);
    cube = bl(cube);
    cube = bl(cube);
    
    return cube;
}

void input() {
    for (int i = 1; i <= 24; i++) {
        cin >> cube[i];
    }
}

void solution() {
    if (check(ul(cube)) || check(ur(cube)) || check(dl(cube)) || check(dr(cube))) {
        cout << 1 << '\n';
    }
    else if (check(lu(cube)) || check(ru(cube)) || check(ld(cube)) || check(rd(cube))) {
        cout << 1 << '\n';
    }
    else if (check(fl(cube)) || check(fr(cube)) || check(bl(cube)) || check(br(cube))) {
        cout << 1 << '\n';
    }
    else {
        cout << 0 << '\n';
    }
}

int main() {
    input();
    solution();
    return 0;
}