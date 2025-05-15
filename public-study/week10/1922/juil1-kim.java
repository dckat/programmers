import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ1922 {
    static int[] parents;

    // 서로소 집합의 대표자 찾기 (경로 압축)
    static int find(int a) {
        if (a == parents[a]) return a;
        return parents[a] = find(parents[a]);
    }

    // 서로소 집합 합치기
    static void union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot == bRoot) return; // 이미 같은 집합인 경우 리턴
        parents[bRoot] = aRoot; // bRoot를 aRoot에 연결
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 컴퓨터의 수
        int m = Integer.parseInt(br.readLine()); // 연결할 수 있는 선의 수

        // 부모 배열 초기화
        parents = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parents[i] = i;
        }

        // 간선 정보를 저장할 우선순위 큐 (가중치 기준 정렬)
        PriorityQueue<int[]> edges = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);

        // 간선 입력 처리
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            edges.add(new int[]{cost, u, v});
        }

        int totalCost = 0;
        int edgeCount = 0;

        while (!edges.isEmpty() && edgeCount < n - 1) {
            int[] currentEdge = edges.poll();
            int cost = currentEdge[0];
            int u = currentEdge[1];
            int v = currentEdge[2];

            // 두 노드가 같은 집합이 아니라면 간선을 추가
            if (find(u) != find(v)) {
                union(u, v);
                totalCost += cost;
                edgeCount++;
            }
        }

        System.out.println(totalCost);
    }
}
