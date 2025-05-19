import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17088 {

    private static int n;
    private static int[] b = null;

    private static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        b = new int[n];

        // 불의 확산과 지훈이의 위치정보들을 -1로 초기화
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static int solution() {
        int answer = -1;

        // 배열의 길이가 1인 경우 0을 반환
        if (b.length == 1) {
            return 0;
        }

        // 맨 앞의 두 수의 공차를 결정
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int[] tmp = b.clone();

                int cnt = 0;
                boolean flag = true;

                tmp[0] += i;
                tmp[1] += j;

                // 1을 더하거나 1을 뺀 연산을 수행한 경우
                if (i != 0) {
                    cnt++;
                }
                if (j != 0) {
                    cnt++;
                }

                int diff = tmp[1] - tmp[0];     // 공차 계산
                for (int k = 2; k < n; k++) {
                    int diff2 = tmp[k] - tmp[k-1];

                    // 공차가 같으면 연산을 수행하지 않음
                    if (diff2 == diff) {
                        continue;
                    }

                    if (diff2 + 1 == diff) {
                        tmp[k]++;
                        cnt++;
                    }
                    else if (diff2 - 1 == diff) {
                        tmp[k]--;
                        cnt++;
                    }
                    else {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    if (answer == -1 || answer > cnt) {
                        answer = cnt;
                    }
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) throws Exception {
        input();
        int answer = solution();
        System.out.println(answer);
    }
}