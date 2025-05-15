import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int solution(int C, int N, int[][] input){

        int answer = 1000 * 100; // 홍보비 와 홍보비로 얻을 수 있는 고객 최대 수 100

        int[] dp = new int[C + 101]; // dp[i] = 고객 i명 을 늘릴 때 발생하는 최소 비용

        Arrays.fill(dp, answer);

        dp[0] = 0;

        for (int i = 0; i < N; i++) {
            int cost = input[i][0];
            int customer = input[i][1];
            for (int j = customer; j <= C + 100; j++) {
                dp[j] = Math.min(dp[j], cost + dp[j - customer]);
            }
        }

        for (int i = 0; i <= 100; i++) {
            answer = Math.min(answer, dp[C + i]);
        }

        return answer;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int C = Integer.parseInt(st.nextToken()); // 목표 고객 수
        int N = Integer.parseInt(st.nextToken()); // 홍보 가능 도시 개수

        int[][] input = new int[N][2];

        for (int i = 0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            input[i][0] = Integer.parseInt(st.nextToken()); // 홍보 비용
            input[i][1] = Integer.parseInt(st.nextToken()); // 해당 비용으로 얻을 수 있는 고객의 수
        }

        System.out.println(solution(C, N, input));

        br.close();
    }
}
