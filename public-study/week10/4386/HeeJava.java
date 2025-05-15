import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ4386 {
    static int parent[];
    static int n;
    static class Node {
        int num;
        double x, y;

        Node(int num, double x, double y) {
            this.num = num;
            this.x = x;
            this.y = y;
        }
    }

    static class Edge implements Comparable<Edge> {
        int v1, v2;
        double weight;

        Edge(int v1, int v2, double weight) {
            this.v1 = v1;
            this.v2 = v2;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            if(weight < o.weight) return -1;
            return 1;
        }
    }

    static double solution(Node[] nodes) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                double weight = distance(nodes[i], nodes[j]);

                pq.add(new Edge(nodes[i].num, nodes[j].num, weight));
            }
        }

        double weight = 0;
        while (!pq.isEmpty()) {
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

    static double distance(Node a, Node b) {
        return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        Node[] nodes = new Node[n];
        parent = new int[n+1];

        for(int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        StringTokenizer st;
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());

            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());

            nodes[i] = new Node(i, x, y);
        }

        bw.write(String.format("%.2f\n", solution(nodes)));
        bw.flush();
        bw.close();
        br.close();
    }
}