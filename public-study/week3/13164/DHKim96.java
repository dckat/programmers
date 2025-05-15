package SSG_coding_test.week3;

import java.io.*;
import java.util.Arrays;

public class BOJ13164 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");

        int N = Integer.parseInt(input[0]); // 유치원 원생 수
        int K = Integer.parseInt(input[1]); // 나누려는 조의 개수

        int[] heights = new int[N]; // 원생들의 키를 담은 배열(오름차순 정렬)

        int[] diffs = new int[N - 1]; // 키 차이를 담은 배열

        String[] line = br.readLine().split(" ");

        for (int i = 0; i < N; i++){
            heights[i] = Integer.parseInt(line[i]);
        }

        for (int i = 0; i < N - 1; i++){
            diffs[i] = heights[i + 1] - heights[i];
        }

        // 100,000,000 => 한 조 당 티셔츠 비용의 최댓값 == 99,999,999
        // 조의 최대 개수 300,000
        // 300,000 * 99,999,999 > int 범위
        // ==> long 사용

        long totalCost = 0;

        // 티셔츠 비용의 합의 최솟값은 티셔츠 비용의 최솟값의 합이면 만족

        // 티셔츠 비용의 최촛값은 가장 많은 키 차이가 발생하는 지점을 조로 편성

        // 예컨대 1 3 5 6 10
        // diffs => {2, 2, 1, 4}
        // 가장 많은 키 차이 지점을 조로 편성하면 해당 키 차이는 제외됨
        // => 2, 4 지점에 조 편성
        // {1 3 | 5 6 | 10}
        // => 나머지 키 차이는 {2, 1}
        // => k 개의 조를 편성하기 위해서는 k - 1의 지점에 구분선을 마련(차이 제외)
        // ==> 가장 큰 k - 1 개의 차이를 제외하고 남은 차이의 합산

        // 차이 배열의 크기 N - 1개에서 가장 큰 값 K - 1 개 제외

        Arrays.sort(diffs);

        for (int i = 0; i <  (N - 1) - (K - 1); i++){
            totalCost += diffs[i];
        }

        bw.write(totalCost + "");
        bw.flush();
        bw.close();
        br.close();

    }
}
