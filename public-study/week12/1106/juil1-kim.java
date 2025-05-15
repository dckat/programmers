import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 호텔/108ms
public class BJ1106 {

    static int solution(int C, int N, int[][] arr) {
        int[] dp = new int[C + 100]; // C+100까지의 범위가 중요

        // dp 배열 초기화
        for (int i = 0; i < C + 100; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        dp[0] = 0;

        // DP를 사용하여 최소 비용 계산
        for (int i = 0; i < N; i++) {
            int cost = arr[i][0];
            int num = arr[i][1];
            for (int j = num; j < C + 100; j++) {
                if (dp[j - num] != Integer.MAX_VALUE) {
                    dp[j] = Math.min(dp[j], dp[j - num] + cost);
                }
            }
        }

        // 최소 비용 찾기
        int minCost = Integer.MAX_VALUE;
        for (int i = C; i < C + 100; i++) {
            minCost = Math.min(dp[i], minCost);
        }

        return minCost;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] line = br.readLine().split(" ");
        int C = Integer.parseInt(line[0]); // 목표 고객 수
        int N = Integer.parseInt(line[1]); // 홍보 방법 수

        int[][] arr = new int[N][2];
        for (int i = 0; i < N; i++) {
            line = br.readLine().split(" ");
            arr[i][0] = Integer.parseInt(line[0]); // 홍보 비용
            arr[i][1] = Integer.parseInt(line[1]); // 고객 수
        }

        System.out.println(solution(C, N, arr));
    }
}
