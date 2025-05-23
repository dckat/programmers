import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int[][] a = null;
    private static int[] order = new int[9];
    private static boolean[] check = new boolean[9];
    private static int n;
    private static int answer;

    private static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        a = new int[n][9];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                a[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static int playBall() {
        int result = 0;     // 얻은 점수
        int now = 0;        // 현재 타순

        // N이닝 공격 진행
        for (int i = 0; i < n; i++) {
            // 이닝별 베이스 및 아웃 상태값 초기화
            int b1 = 0;
            int b2 = 0;
            int b3 = 0;
            int out = 0;

            // 3아웃이 될때까지 각 이닝의 공격 진행
            while (out < 3) {
                int cur = a[i][order[now]];

                // 아웃
                if (cur == 0) {
                    out++;
                }
                // 안타
                else if (cur == 1) {
                    result += b3;
                    b3 = b2;
                    b2 = b1;
                    b1 = 1;
                }
                // 2루타
                else if (cur == 2) {
                    result += (b3 + b2);
                    b3 = b1;
                    b2 = 1;
                    b1 = 0;
                }
                // 3루타
                else if (cur == 3) {
                    result += (b3 + b2 + b1);
                    b3 = 1;
                    b2 = b1 = 0;
                }
                // 홈런
                else {
                    result += (b3 + b2 + b1 + 1);
                    b3 = b2 = b1 = 0;
                }
                now++;

                // 한 바퀴를 돈 경우
                if (now == 9) {
                    now = 0;
                }
            }
        }
        return result;
    }

    private static void solution(int index) {
        // 9명의 선수의 타순을 모두 정한 경우
        if (index == 9) {
            // 게임을 진행하여 점수의 최댓값 갱신
            int result = playBall();

            if (answer < result) {
                answer = result;
            }
            return;
        }

        // 1번 선수는 4번 타순에 선택
        if (index == 3) {
            order[index] = 0;
            check[0] = true;
            solution(index+1);
        }

        // 나머지 8명의 선수의 타순을 결정
        for (int i = 1; i < 9; i++) {
            if (check[i]) {
                continue;
            }
            order[index] = i;
            check[i] = true;
            solution(index+1);
            order[index] = -1;
            check[i] = false;
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        solution(0);
        System.out.println(answer);
    }
}