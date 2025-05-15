import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 간선 정보 클래스
class Edge implements Comparable<Edge>{
    int v1;
    int v2;
    int weight;

    public Edge(int v1, int v2, int weight) {
        this.v1 = v1;
        this.v2 = v2;
        this.weight = weight;
    }

    // 우선 순위 큐 활용
    @Override
    public int compareTo(Edge e) {
        return weight - e.weight;
    }
}

public class BOJ1197 {
    static int[] parent;


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        parent = new int[v + 1];

        // 부모노드 세팅
        for(int i = 1; i <= v; i++) {
            parent[i] = i;
        }

        Edge[] edges = new Edge[e];
        // 간선 정보 입력
        for(int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edges[i] = new Edge(v1, v2, weight);
        }

        bw.write(solution(edges) + "");
        bw.flush();
        bw.close();
        br.close();
    }

    private static int solution(Edge[] edges) {
        PriorityQueue<Edge> pq = new PriorityQueue<>(); // 간선 정보 저장
        pq.addAll(Arrays.asList(edges));

        int weight = 0;
        while(!pq.isEmpty()) {
            Edge cur = pq.poll(); // 가중치가 가장 작은 간선
            // 부모노드가 다를때만 (사이클X)
            if(find(cur.v1) != find(cur.v2)) {
                union(cur.v1, cur.v2);
                weight += cur.weight;
            }
        }
        return weight;
    }

    // 합치기
    public static void union(int n1, int n2) {
        int p1 = find(n1);
        int p2 = find(n2);

        if(p1 != p2)
            parent[p2] = p1;
    }

    // 부모 노드 찾기
    public static int find(int n) {
        if(parent[n] == n) {
            return n;
        }
        return parent[n] = find(parent[n]);
    }
}