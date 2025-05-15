import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ1238 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 마을 수
        int M = Integer.parseInt(st.nextToken()); // 도로 수
        int X = Integer.parseInt(st.nextToken()); // 파티가 열리는 마을 번호

        // 그래프 초기화 (순방향, 역방향)
        ArrayList<int[]>[] adj = new ArrayList[N + 1];
        ArrayList<int[]>[] reverseAdj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
            reverseAdj[i] = new ArrayList<>();
        }

        // 간선 정보 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            adj[n1].add(new int[]{n2, t}); // 순방향 간선
            reverseAdj[n2].add(new int[]{n1, t}); // 역방향 간선
        }

        int[] distToX = dijkstra(X, N, reverseAdj); // 각 마을에서 X로 가는 거리
        int[] distFromX = dijkstra(X, N, adj);     // X에서 각 마을로 가는 거리

        // 왕복 거리 중 최대값 계산
        int maxTime = 0;
        for (int i = 1; i <= N; i++) {
            maxTime = Math.max(maxTime, distToX[i] + distFromX[i]);
        }

        System.out.println(maxTime);
    }

    static int[] dijkstra(int start, int N, ArrayList<int[]>[] adj) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE); // 초기값: 무한대

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        pq.add(new int[]{start, 0}); // 시작 노드와 비용 추가
        dist[start] = 0;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curNode = cur[0];
            int curCost = cur[1];

            if (dist[curNode] < curCost) continue; // 이미 처리된 노드 무시

            for (int[] edge : adj[curNode]) { // 인접한 노드 탐색
                int nextNode = edge[0];
                int nextCost = curCost + edge[1];

                if (dist[nextNode] > nextCost) { // 더 짧은 경로 발견 시 갱신
                    dist[nextNode] = nextCost;
                    pq.add(new int[]{nextNode, nextCost});
                }
            }
        }

        return dist;
    }
}
