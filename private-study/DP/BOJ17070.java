import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ17070 {

    private static int[][] a = null;
    private static int n;

    private static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        a = new int[n+1][n+1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                a[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static int solution() {
        int answer = 0;

        // 현재 칸에서 방향에 따라 놓을 수 있는 경우의 수
        // 0: 가로. 1: 세로. 2: 대각선
        int[][][] dp = new int[n+1][n+1][3];
        dp[1][2][0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 2; j <= n; j++) {
                // 초기값
                if (i == 1 && j == 2) {
                    continue;
                }

                // 벽인 경우 파이프를 놓을 수 없음
                if (a[i][j] == 1) {
                    continue;
                }

                // 가로 이동 (이전 칸이 가로.대각선에서 왔음)
                if (a[i][j-1] != 1) {
                    dp[i][j][0] = dp[i][j-1][0] + dp[i][j-1][2];
                }
                // 세로 이동 (이전 칸이 세로.대각선에서 왔음)
                if (a[i-1][j] != 1) {
                    dp[i][j][1] = dp[i-1][j][1] + dp[i-1][j][2];
                }
                // 대각선 이동 (이전 칸이 가로.세로.대각선에서 왔음)
                if (a[i-1][j-1] != 1 && a[i-1][j] != 1 && a[i][j-1] != 1) {
                    dp[i][j][2] = dp[i-1][j-1][0] + dp[i-1][j-1][1] + dp[i-1][j-1][2];
                }
            }
        }
        answer = dp[n][n][0] + dp[n][n][1] + dp[n][n][2];

        return answer;
    }

    public static void main(String[] args) throws Exception {
        input();
        int answer = solution();
        System.out.println(answer);
    }
}