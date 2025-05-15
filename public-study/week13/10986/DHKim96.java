package BOJ.dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10986 {

    static long solution(int N, int M, int[] sequence) {

        long answer = 0; // M으로 나누어떨어지는 (i,j) 쌍의 개수 => int 형 범위 초과 가능
        long sum = 0;
        long[] cnt = new long[M]; // cnt[i]: M으로 나눈 나머지가 i인 누적합 배열의 갯수

        // 1. 누적합 구하기
        for (int i = 1; i <= N; i++) {
            sum += sequence[i];
            int index = (int)(sum % M);
            // 2. 누적합을 M으로 나눈 나머지가 같은 배열의 갯수 구하기
            if (index == 0) answer++; // 누적합 자체가 M의 배수인 경우
            cnt[index]++;
        }

        // 3. 나머지가 같은 누적합의 갯수에서 2개를 꺼내는 경우의 수 == 부분합을 M으로 나누어 떨어지는 구간의 개수
        // ex) prefix[2] % M = prefix[3] % M = prefix[5] % M
        // * 모듈러 연산에 의해
        //  => prefix[5] % M - prefix[3] % M = 0
        //  => (prefix[5] - prefix[3]) % M = 0
        //  ==> sequence[4] 부터 sequence[5] 까지의 부분합이 M으로 나누어 떨어짐

        for (int i = 0; i < M; i++) {
            if (cnt[i] > 1) {
                answer += cnt[i] * (cnt[i] - 1 ) / 2;
            }
        }

        return answer;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 수의 개수
        int M = Integer.parseInt(st.nextToken()); // 나누는 수

        int[] sequence = new int[N + 1];

        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {
            sequence[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(N, M, sequence));

        br.close();
    }
}
