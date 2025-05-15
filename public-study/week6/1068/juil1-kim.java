import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ1068 {
    static ArrayList<Integer>[] graph;
    static boolean visited[];
    static int delete;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // 노드의 개수
        graph = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        for (int i = 0; i < N; i++)
            graph[i] = new ArrayList<>();
        int root = -1; // 루트 노드 초기값

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int p = Integer.parseInt(st.nextToken());
            if (p == -1) {
                // i 노드가 바로 루트 노드
                root = i; // 루트 노드 정보를 저장함
            } else {
                // p 가 i 노드의 부모 노드임
                graph[i].add(p);
                graph[p].add(i); // 서로 연결
            }
        }
        delete = Integer.parseInt(br.readLine());
        if (delete == root) {
            System.out.println(0);
            return;
        } else dfs(root);
        System.out.println(ans);
    }

    static void dfs(int v) {
        visited[v] = true;
        int nodes = 0;
        for (int cur : graph[v]) {
            // 연결 노드 탐색
            if (cur != delete && !visited[cur]) {
                nodes++;
                dfs(cur);
            }
        }
        if (nodes == 0) {
            ans++; // 자식 노드가 하나도 없으면 리프 노드
        }
    }
}
