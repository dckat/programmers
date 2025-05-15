import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1922 {
    static int[] parent;

    static class Edge implements Comparable<Edge> {
        int v1, v2, weight;

        Edge(int v1, int v2, int weight) {
            this.v1 = v1;
            this.v2 = v2;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return weight - o.weight;
        }
    }

    static int solution(Edge[] edges) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.addAll(Arrays.asList(edges));

        int weight = 0;
        while(!pq.isEmpty()) {
            Edge cur = pq.poll();

            if(find(cur.v1) != find(cur.v2)) {
                union(cur.v1, cur.v2);
                weight += cur.weight;
            }
        }

        return weight;
    }

    static int find(int x) {
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int x, int y) {
        int p1 = find(x);
        int p2 = find(y);

        if(p1 != p2) {
            parent[p2] = p1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine()); // 컴퓨터의 수
        int M = Integer.parseInt(br.readLine()); // 선의 수

        parent = new int[N+1];

        for(int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        Edge[] edges = new Edge[M];
        StringTokenizer st;
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            edges[i] = new Edge(a, b, c);
        }

        bw.write(solution(edges) + "");
        bw.flush();
        bw.close();
        br.close();
    }
}
