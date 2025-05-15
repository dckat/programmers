package ssgStudy.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ14500 {
    /*
    * 테트로미노
    */
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0}; // 북 동 남 서
    static int[] dy = {0, 1, 0, -1}; // 북 동 남 서
    static int count;
    static int N,M;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                visited[x][y] = true;
                backTracking(x,y,1,map[x][y]);
                visited[x][y] = false;
            }
        }

        System.out.print(count);
        bf.close();
    }

    static void backTracking(int x, int y, int depth, int sum) { //sum은 map[r][c]가 초깃값, depth는 초깃값 1

        if(depth == 4) {
            if(count < sum) count = sum;
            return;
        }

        for(int i = 0 ; i < 4 ; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if(!isValid(nextX, nextY)) continue; //index 유효하지 않으면 다음 i
            if(visited[nextX][nextY]) continue;

            if(depth == 2) { //ㅗ 만들어줘야 할 경우 (두번째 칸에서 엇나가야지 그래서 depth 2),
                visited[nextX][nextY] = true;
                backTracking(x, y, depth+1, sum + map[nextX][nextY]);
                visited[nextX][nextY] = false;
            }

            visited[nextX][nextY] = true;
            backTracking(nextX, nextY, depth+1, sum + map[nextX][nextY]);
            visited[nextX][nextY] = false;
        }
    }

    static boolean isValid(int x, int y) { //행열의 index가 유효한지 검증
        if(x < 0 || x >= N || y < 0 || y >= M) return false;
        return true;
    }
}
