import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ4386 {
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
        int n = Integer.parseInt(br.readLine()); // 별의 개수
        parents = new int[n + 1];

        // 부모 배열 초기화
        for (int i = 0; i <= n; i++) {
            parents[i] = i; // 대표자 초기화
        }

        double[][] stars = new double[n][2]; // 별의 좌표 저장

        // 별의 좌표 입력
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            stars[i][0] = Double.parseDouble(st.nextToken());
            stars[i][1] = Double.parseDouble(st.nextToken());
        }

        // 가중치가 가장 작은 것을 가지고 있는 간선을 루트로 하는 최소 힙 선언
        PriorityQueue<double[]> edges = new PriorityQueue<>((o1, o2) -> Double.compare(o1[0], o2[0]));

        // 모든 별 쌍에 대해 간선 추가
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double distance = Math.sqrt(Math.pow(stars[i][0] - stars[j][0], 2) + Math.pow(stars[i][1] - stars[j][1], 2));
                edges.add(new double[]{distance, i, j}); // (거리, 시작노드, 도착노드)
            }
        }

        double answer = 0.0;
        int connect = 0;

        while (connect < n - 1) {
            double[] cur = edges.poll();
            double cost = cur[0];
            int u = (int) cur[1];
            int v = (int) cur[2];

            // 같은 서로소 집합인지 파악. 이미 트리에 연결되어 있는 노드끼리의 간선이면 무시
            if (find(u) == find(v)) {
                continue;
            }

            answer += cost;
            connect++;
            union(u, v);
        }

        System.out.printf("%.2f\n", answer); // 결과 출력 (소수점 둘째 자리까지)
    }
}
