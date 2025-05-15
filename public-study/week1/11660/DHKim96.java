package SSG_coding_test.week1;

import java.io.*;

public class BOJ11660 {
    /*
     * 구간 합 구하기 5
     * (x1, y1) ~ (x2,y2) 합 구하기
     *
     * => 1. 이차원 배열 3중 반복문 ==> 연산횟수 1억번 넘어감 => 시간초과
     * => 2. 일차원 배열로 변환 => O(N^2 * M) => 연산 횟수 1억번 이상
     * => 3. 누적합
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 1. N, M 입력 받기
        String[] input = br.readLine().split(" ");

        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        // 2. 좌표별 요소 입력 받기 => 동시에 누적합 배열로 변환
        int[][] sum = new int[N+1][N+1]; // 인덱스 0은 사용하지 않음

        for (int i = 1; i <= N; i++) {
            String[] line = br.readLine().split(" ");

            for (int j = 1; j <= N; j++) {
                sum[i][j] = Integer.parseInt(line[j - 1]) + sum[i][j-1] + sum[i-1][j] - sum[i-1][j-1];
            }
        }

        // 3. x1, y1, x2, y2 입력 받기
        int[][] xy = new int[M][4]; // x : 행 / y : 열

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++) {

            String[] line = br.readLine().split(" ");

            int x1 = Integer.parseInt(line[0]);
            int y1 = Integer.parseInt(line[1]);
            int x2 = Integer.parseInt(line[2]);
            int y2 = Integer.parseInt(line[3]);

            int result = 0;

            if(y1 == 0){
                result = sum[x2][y2] - sum[x1-1][y2];
            } else {
                result = sum[x2][y2] - sum[x1-1][y2] - sum[x2][y1-1] + sum[x1-1][y1-1];
            }

            sb.append(result).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

}
