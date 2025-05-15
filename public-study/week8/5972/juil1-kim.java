import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ5972 {

    static int dist[]; // 최단거리 저장 배열
    static ArrayList<ArrayList<Node>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        dist = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken()); // 간선의 시작 노드
            int B = Integer.parseInt(st.nextToken()); // 간선의 끝 노드
            int C = Integer.parseInt(st.nextToken()); // 간선의 가중치

            graph.get(A).add(new Node(B, C)); // 양방향 그래프이므로 A -> B 추가
            graph.get(B).add(new Node(A, C)); // B -> A 추가
        }

        Arrays.fill(dist, Integer.MAX_VALUE);

        dijst();
        System.out.println(dist[N]); // 1번 노드에서 N번 노드까지의 최단 거리 출력
    }

    static void dijst() {
        PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        // 우선순위 큐 생성 (비용이 작은 순으로 정렬)

        queue.add(new Node(1, 0)); // 시작 노드(1번)와 비용(0)을 큐에 추가
        dist[1] = 0; // 시작 노드의 최단 거리는 0으로 설정

        while (!queue.isEmpty()) {
            Node now = queue.poll(); // 현재 노드 꺼내기

            for (Node next : graph.get(now.v)) {
                // 현재 노드와 연결된 모든 인접 노드를 확인

                if (dist[next.v] > dist[now.v] + next.cost) {
                    // 현재 경로를 통해 더 짧은 거리를 발견한 경우

                    dist[next.v] = dist[now.v] + next.cost;
                    // 최단 거리 갱신

                    queue.add(new Node(next.v, dist[next.v]));
                    // 갱신된 노드를 큐에 추가
                }
            }
        }
    }

    static class Node {
        int v; // 노드 번호
        int cost; // 해당 노드까지의 비용

        public Node(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }
    }
}