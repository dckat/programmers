import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ1915 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 행
        int m = Integer.parseInt(st.nextToken()); // 열

        char[][] map = new char[n + 1][m + 1];
        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray(); // 한 줄씩 읽고, char배열로 변환 => 해당 행에 저장
        }

        int[][] dp = new int[n + 1][m + 1]; // 각 위치에서 만들 수 있는 가장 큰 정사각형의 한 변의 길이 저장

        int max = 0;

        for (int i = 1; i <= n; i++) { // dp배열과 입력 배열 인덱스 맞춰야 함. 1부터 시작
            for (int j = 1; j <= m; j++) {
                if (map[i - 1][j - 1] == '0') { // 현재 위치의 값 0 -> 정사각형 X
                    dp[i][j] = 0;
                    continue;
                }

                dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j])) + 1;
                //
                max = Math.max(max, dp[i][j]);
                // 가장 큰 정사각형의 한변의 길이 저장
            }
        }
        System.out.println(max * max);
    }
}
