package ssgStudy.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ1303 {
    /*
    * 전쟁 - 전투
    * dfs 이용.
    */
    static int N, M;    // 가로, 세로
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static char[][] map;
    static boolean[][] visited;
    static int sum = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);

        int bSum = 0;
        int wSum = 0;

        map = new char[M][N];
        for (int i = 0; i < M; i++) {   //map 설정
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        visited = new boolean[M][N];
        for (int y = 0; y < M; y++) {
            for (int x = 0; x < N; x++) {
                if (!visited[y][x]) {
                    if(map[y][x] == 'B'){
                        dfs(y, x, 'B');
                        bSum += sum * sum;
                        sum = 0;    // 이전 영역 크기 초기화 -> 새로운 영역 크기를 계산하기 위해
                    }else {
                        dfs(y, x, 'W');
                        wSum += sum * sum;
                        sum = 0;
                    }
                }
            }
        } // for

        System.out.println(wSum + " " + bSum);
        br.close();
    } // main

    static void dfs(int y, int x, char word) {
        visited[y][x] = true;
        sum++;
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            // 유효한 map 범위 내에 있는지 확인.
            if (ny >= 0 && ny < M && nx >= 0 && nx < N) {
                // 새로운 위치가 '방문되지 않았는지, 탐색 중인 문자와 같은지' 확인.
                if(!visited[ny][nx] && map[ny][nx] == word) {
                    dfs(ny, nx, word);
                }
            }
        }
    } // dfs
}
