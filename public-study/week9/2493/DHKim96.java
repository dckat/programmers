package SSG_coding_test.week9;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ2493 {
    /*
    [BOJ] 2493 : 탑
     */
    static int[] lasers; // 레이저를 쏘는 높이

    static int[] solution(int[] lasers){
        Deque<int[]> stack = new ArrayDeque<>();

        int[] solution = new int[lasers.length];

        for (int i = lasers.length - 1; i > 0; i--) {
            while (!stack.isEmpty() && stack.peek()[0] <= lasers[i]) {
                int[] towerInfo = stack.pop();
                solution[towerInfo[1]] = i;
            }

            stack.push(new int[] {lasers[i], i}); // 높이와 순서
        }

        return solution;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine()); // 탑의 수

        StringTokenizer st = new StringTokenizer(br.readLine());

        lasers = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            lasers[i] = Integer.parseInt(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();

        int[] solution = solution(lasers);

        for (int i = 1; i < solution.length; i++) {
            sb.append(solution[i]).append(" ");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
