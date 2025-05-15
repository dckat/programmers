package BOJ.dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1806 {
    static int solution(int N, int S, int[] sequence){

        int answer = Integer.MAX_VALUE;
        int left = 0;
        int right = 0;
        int sum = 0;

        while (right < N) {
            sum += sequence[right++];  // 오른쪽 포인터를 이동하며 합을 증가

            while (sum >= S) {  // 조건을 만족하면 최소 길이 갱신 후 left 이동
                answer = Math.min(answer, right - left);
                sum -= sequence[left++];  // 왼쪽 포인터 이동
            }
        }

        return (answer == Integer.MAX_VALUE) ? 0 : answer;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 수열의 길이(< 10000)
        int S = Integer.parseInt(st.nextToken()); // 부분합의 기준점(<= 100000000)

        int[] sequence = new int[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++){
            sequence[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(N, S, sequence));

        br.close();

    }
}
