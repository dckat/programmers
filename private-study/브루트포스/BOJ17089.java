import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static boolean[][] relation = null;
    private static int[] friends = null;
    private static int n, m;

    private static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        relation = new boolean[n+1][n+1];
        friends = new int[n+1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            relation[a][b] = true;
            relation[b][a] = true;
            friends[a]++;
            friends[b]++;
        }
    }

    private static int solution() {
        int answer = -1;

        for (int i = 1; i <= n; i++) {
            for (int j = i+1; j <= n; j++) {
                // 두 명이 친구 관계일때만 세번째 친구를 선택
                if (relation[i][j]) {
                    for (int k = j+1; k <= n; k++) {
                        // 세 명이 모두 친구관계가 성립할때 최솟값 계산하여 갱신
                        if (relation[i][k] && relation[j][k]) {
                            int temp = friends[i] + friends[j] + friends[k] - 6;
                            if (answer == -1 || answer > temp) {
                                answer = temp;
                            }
                        }
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