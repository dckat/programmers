package BOJ.dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1520 {
    /*
     * 내리막길
     */
    private static int[][] map;
    private static int[][] dp;
    private static final int[] dRow = {-1, 1, 0, 0}; // 상 하 좌 우
    private static final int[] dCol = {0, 0, -1, 1}; // 상 하 좌 우

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stk = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(stk.nextToken()); // 세로
        int N = Integer.parseInt(stk.nextToken()); // 가로

        map = new int[M][N];
        dp = new int[M][N];

        // map 정보 입력 받기
        for (int row = 0; row < M; row++) {
            stk = new StringTokenizer(br.readLine());
            for (int col = 0; col < N; col++) {
                map[row][col] = Integer.parseInt(stk.nextToken());
                dp[row][col] = -1; // 탐색 안한 상태를 반영하기 위한 초기화
            }
        }

        System.out.println( dfs(0, 0));

        br.close();
    }

    /*
    dp[i][j] = (i,j)에서 종점(M-1,N-1)까지 가는 경로의 수
     */
    private static int dfs(int currRow, int currCol){
        // 종점 도착 시 탈출
        if (currRow == (map.length - 1) && currCol == (map[currRow].length - 1)){
            return 1;
        }

        if (dp[currRow][currCol] != -1){ // 이미 탐색 했을 경우 바로 반환
            return dp[currRow][currCol];
        }

        dp[currRow][currCol] = 0; // 탐색했음을 반영

        for (int i = 0; i < 4; i++){
            int nextRow = currRow + dRow[i];
            int nextCol = currCol + dCol[i];

            if (canMove(nextRow, nextCol, currRow, currCol)) {
                dp[currRow][currCol] += dfs(nextRow, nextCol);
            }
        }

        return dp[currRow][currCol];
    }

    private static boolean canMove(int nextRow, int nextCol, int currRow, int currCol) {
        return nextRow >= 0 && nextCol >= 0 &&
                nextRow < map.length && nextCol < map[nextRow].length &&
                map[nextRow][nextCol] < map[currRow][currCol];
    }
}
