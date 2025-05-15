import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ6497 {
    static int[] parents;

    static int find(int a) { // 서로소 집합의 대표자 찾기
        if (a == parents[a]) return a;
        else return parents[a] = find(parents[a]); // 경로 압축
    }

    static void union(int a, int b) { // 서로소 집합 합치기
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot == bRoot) return;
        parents[bRoot] = aRoot;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken()); // 집의 수
            int n = Integer.parseInt(st.nextToken()); // 길의 수

            if (m == 0 && n == 0) break; // 입력 종료 조건

            parents = new int[m]; // 부모 배열 초기화
            for (int i = 0; i < m; i++) {
                parents[i] = i; // 대표자 초기화
            }

            PriorityQueue<int[]> edges = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]); // 최소 힙 선언
            int totalCost = 0; // 모든 가로등을 켰을 때의 총 비용

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());

                edges.add(new int[] {w, u, v}); // (가중치, 시작노드, 도착노드 순으로 입력)
                totalCost += w; // 총 비용 계산
            }

            int mstCost = 0; // 최대 비용
            int connect = 0; // 연결된 간선 개수

            while (!edges.isEmpty() && connect < m - 1) { // 최대비용 완성 조건
                int[] cur = edges.poll();

                // 같은 서로소 집합에 속해 있는 경우 간선을 무시
                if (find(cur[1]) == find(cur[2])) {
                    continue;
                }

                mstCost += cur[0];
                connect++;
                union(cur[1], cur[2]);
            }

            System.out.println(totalCost - mstCost);
        }
    }
}
