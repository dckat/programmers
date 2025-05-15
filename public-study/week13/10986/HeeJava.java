import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 숫자 갯수
        int M = Integer.parseInt(st.nextToken()); // 나눌 수

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(arr, N, M));
    }

    static long solution(int[] arr, int N, int M) {
        long[] reminder = new long[M];

        long result = 0;
        long sum = 0;
        for(int i = 0; i < N; i++) {
            sum += arr[i]; // 누적합을 구한다
            int remind = (int) (sum % M);
            reminder[remind]++; // M으로 나눈 인덱스를 구한다
        }

        result += reminder[0]; // M으로 나눈 값들은 결과 값에 더한다.

        for(int i = 0; i < M; i++) {
            if(reminder[i] > 1) {
                result += (reminder[i] * (reminder[i]-1)) / 2; // nC2 조합 공식 n * (n-1) / 2
            }
        }
        return result;
    }
}
