import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class Main {

    static int[][] dp;
    static List<Integer>[] tree;
    static boolean[] visited;

    static void dfs(int node){
        visited[node] = true; // 방문 처리
        dp[node][0] = 0; // 현재 노드가 얼리어답터가 아닐 때
        dp[node][1] = 1; // 현재 노드가 얼리어답터일 때

        for (int child : tree[node]) { // 임의로 부모-자식 관계로 설정
            if (!visited[child]) { // 방문하지 않은 노드라면 방문
                dfs(child); // 재귀호출을 통해 자식 노드의 dp 값을 미리 구함
                // => 현재 노드가 얼리어답터일 때와 아닌 경우에 따라 자식 노드의 dp 값을 달리 추가
                dp[node][0] += dp[child][1]; // 부모 노드가 얼리어답터가 아니면 자식 노드는 무조건 얼리어답터여야만 함
                dp[node][1] += Math.min(dp[child][0], dp[child][1]);
                // 부모 노드가 얼리어답터라도 자식 노드가 얼리어답터인 경우가 더 최소 값일 수 있음(2번째 테케)
            }
        }
    }

    static int solution(int N, int[][] edges){

        tree = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        dp = new int[N + 1][2];
        // dp[i][0] : i번째 노드가 얼리어답터가 아닐 때 최소 값
        // dp[i][1] : i번째 노드가 얼리어답터일 때의 최소 값

        for (int i = 0; i <= N; i++){
            tree[i] = new ArrayList<>();
        }

        for (int[] edge : edges){
            tree[edge[0]].add(edge[1]);
            tree[edge[1]].add(edge[0]);
        }

        dfs(1);

        return Math.min(dp[1][0], dp[1][1]);
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // 친구 관계 트리의 정점 개수

        int[][] edges = new int[N - 1][2];

        for (int i = 0; i < N - 1; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            edges[i][0] = Integer.parseInt(st.nextToken());
            edges[i][1] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(N, edges));

        br.close();

    }
}