import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 부분합/260ms
public class BJ1806 {

    static int solution(int n, int s, int[] arr) {
        int sum = 0;
        int length = Integer.MAX_VALUE; // 초기값 설정
        int start = -1;

        for (int end = 0; end < n; end++) {
            sum += arr[end];
            if (sum >= s) {
                while (sum >= s) {
                    length = Math.min(length, end - start);
                    start++;
                    sum -= arr[start];
                }
            }
        }

        // 최소 길이가 초기값 -> 0 반환
        return (length == Integer.MAX_VALUE) ? 0 : length;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nAndS = br.readLine().split(" ");
        int n = Integer.parseInt(nAndS[0]); // 배열 크기 n
        int s = Integer.parseInt(nAndS[1]); // 목표합 s

        String[] arrInfo = br.readLine().split(" ");
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(arrInfo[i]); // 정수 변환
        }

        System.out.println(solution(n, s, arr));
    }
}