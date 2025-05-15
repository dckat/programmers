package SSG_coding_test.week5;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ14500 {
    /*
        * [BOJ] 테트로미노
            * => 완전 탐색
     */
    static int[][] paper;
    static boolean[][] visited;
    static int N, M, maxSum;

    //상하좌우
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    // 테트로미노 "ㅗ" 모양 별도 정의
    static int[][][] tetroT = {
            {{0, 0}, {1, 0}, {1, 1}, {2, 0}},
            {{0, 0}, {1, -1}, {1, 0}, {2, 0}},
            {{0, 0}, {0, 1}, {0, 2}, {1, 1}},
            {{0, 0}, {1, -1}, {1, 0}, {1, 1}}
    };


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer stk = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stk.nextToken()); // 세로
        M = Integer.parseInt(stk.nextToken()); // 가로
        maxSum = 0;
        paper = new int[N][M];
        visited = new boolean[N][M];


        for (int i = 0; i < N; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                paper[i][j] = Integer.parseInt(stk.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                dfs(i, j, 0, paper[i][j]);
                visited[i][j] = false;

                checkTetroT(i, j);
            }
        }

        bw.write(maxSum + "");
        bw.flush();
        bw.close();
        br.close();
    }


    public static void dfs(int row, int col, int depth, int sum){
        if (depth == 3){
            maxSum = Math.max(maxSum, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nextRow = row + dx[i];
            int nextCol = col + dy[i];

            if (canMove(nextRow, nextCol)){
                visited[nextRow][nextCol] = true;
                dfs(nextRow, nextCol, depth+1, sum + paper[nextRow][nextCol]);
                visited[nextRow][nextCol] = false;
            }
        }
    }

    public static void checkTetroT(int row, int col){
        for (int[][] shape : tetroT) {
            int sum = 0;
            boolean valid = true;
            for (int[] block : shape){
                int nextRow = row + block[0];
                int nextCol = col + block[1];

                if (!canMove(nextRow, nextCol)) {
                    valid = false;
                    break;
                }

                sum += paper[nextRow][nextCol];
            }
            if (valid){
                maxSum = Math.max(maxSum, sum);
            }
        }
    }

    public static boolean canMove(int row, int col){
        return row < paper.length && row >= 0 && col >= 0 && col < paper[0].length && !visited[row][col];
    }

}
