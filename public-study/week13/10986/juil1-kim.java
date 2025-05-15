import java.util.*;
import java.io.*;

public class BJ10986 {
    // 나머지 합/448ms

    static long solution(int N, int M, int[] numbers) {
        long result = 0;
        long[] S = new long[N + 1]; // 누적합 배열
        long[] cnt = new long[M];   // 나머지 카운트 배열

        // 누적합 계산, 나머지 처리
        for (int i = 1; i <= N; i++) {
            // 이전 누적합에 현재 값을 더하고 M으로 나눈 나머지를 저장
            S[i] = (S[i - 1] + numbers[i - 1]) % M;

            if (S[i] == 0) {
                result++; // 조건 만족. 결과값 증가
            }

            // 같은 나머지를 가진 구간의 개수를 카운트
            cnt[(int) S[i]]++;
        }

        // 같은 나머지를 가진 구간 쌍 계산
        for (int i = 0; i < M; i++) {
            if (cnt[i] > 1) {
                // 같은 나머지를 가진 두 구간을 선택하는 조합 계산: nC2 = n * (n - 1) / 2
                result += (cnt[i] * (cnt[i] - 1)) / 2;
            }
        }

        return result; // 최종 결과 반환
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());   // 수의 개수
        int M = Integer.parseInt(st.nextToken());   // 나누기 할 수

        int[] numbers = new int[N]; // 입력된 수 배열
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(N, M, numbers));
    }
}