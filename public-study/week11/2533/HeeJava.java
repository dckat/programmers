import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static List<Integer>[] graph;
    static int[][] dp;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        graph = new ArrayList[n+1];
        dp = new int[n+1][2];
        visited = new boolean[n+1];

        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for(int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            graph[start].add(end);
            graph[end].add(start);
        }

        dfs(1); // 트리 1부터 시작
        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    static void dfs(int n) {
        visited[n] = true;
        dp[n][0] = 0; // 얼리어답터가 아닌 경우
        dp[n][1] = 1; // 얼리어답터가 맞는 경우 1 개수해준다.

        for(int child : graph[n]) {
            if(!visited[child]) {
                dfs(child); // 트리의 자식 값을 먼저 구해준다.
                dp[n][0] += dp[child][1]; // 얼리어답터가 아닌 경우 자식은 무조건 얼리어답터다.
                dp[n][1] += Math.min(dp[child][0], dp[child][1]); // 얼리어답터인 경우 자식은 얼리어답터일 수도 있고 아닐 수도 있다.
            }
        }
    }
}
