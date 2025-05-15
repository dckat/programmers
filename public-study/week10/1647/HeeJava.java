import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1647 {
    static int parent[];

    static class Edge implements Comparable<Edge> {
        int v1, v2, weight;

        Edge(int v1, int v2, int weight) {
            this.v1 = v1;
            this.v2 = v2;
            this.weight = weight;
        }

        public int compareTo(Edge o) {
            return weight - o.weight;
        }
    }

    static int solution(Edge[] edges) {
        PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
        pq.addAll(Arrays.asList(edges));

        int weight = 0;
        int last = 0;
        while(!pq.isEmpty()) {
            Edge cur = pq.poll(); // 가중치가 가장 작은 간선

            if(find(cur.v1) != find(cur.v2)) {
                union(cur.v1, cur.v2);
                weight += cur.weight;
                last = cur.weight;
            }
        }
        return weight - last; // union 된 것중 가중치가 가장 큰 것을 뺀다.
    }

    static int find(int x) {
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int x1, int x2) {
        int p1 = find(x1);
        int p2 = find(x2);

        if(p1 != p2)
            parent[p2] = p1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 집의 개수
        int M = Integer.parseInt(st.nextToken()); // 길의 개수

        parent = new int[N+1];
        for(int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        Edge[] edges = new Edge[M];

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());;

            edges[i] = new Edge(v1, v2, weight);
        }

        bw.write(solution(edges) + "");
        bw.flush();
        bw.close();
        br.close();
    }
}
