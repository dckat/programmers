import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ11049 {

    static int[][] arr;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine()); // 행렬 개수

        //행렬 정보 저장
        arr = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N][N];

        for (int d = 1; d < N; d++) {
            // i+d : 현재 부분 문제의 끝점
            for (int i = 0; i + d < N; i++) {
                int min = Integer.MAX_VALUE; // 최소값의 초기값
                for (int k = i; k < i + d; k++) { // 현재 구간 [i, i+d]를 나누는 분할점 k를 탐색
                    int val = dp[i][k] + dp[k + 1][i + d] + sum(i, k, i + d);
                    // 분할점 k에서 발생하는 총 연산 횟수 : val
                    // dp[i][k]: 왼쪽 그룹 (i부터 k까지)의 최소 곱셈 연산 횟수
                    // dp[k+1][i+d]: 오른쪽 그룹 (k+1부터 i+d까지)의 최소 곱셈 연산 횟수
                    // sum(i, k, i+d): 왼쪽 그룹과 오른쪽 그룹을 합칠 때 발생하는 곱셈 연산 횟수

                    min = Math.min(min, val);
                }
                dp[i][i + d] = min; // 최소 곱셈 연산 횟수 저장
            }
        }

        System.out.println(dp[0][N - 1]);
    }

    static int sum(int i, int k, int j) { // i: 시작/ k: 분할 / j: 끝
        return arr[i][0] * arr[k][1] * arr[j][1];
    }

}