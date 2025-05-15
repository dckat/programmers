import java.io.*;
import java.util.*;

public class Main {
/*
	[BOJ]1922 : 네트워크 연결
*/
    static int[] parent;
    static int[] rank;

    // 경로 압축 (Find)
    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]); // 경로 압축
    }

    // 랭크 기반 병합 (Union)
    static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y) return; // 이미 같은 집합이면 병합 불필요

        if (rank[x] > rank[y]) {
            parent[y] = x;
        } else if (rank[x] < rank[y]) {
            parent[x] = y;
        } else {
            parent[y] = x;
            rank[x]++;
        }
    }

    public static int solution(int N, int[][] edges) {
        int answer = 0;
        parent = new int[N + 1];
        rank = new int[N + 1];

        // 부모 배열 초기화
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }

        // 간선 가중치 기준으로 정렬
        Arrays.sort(edges, (a, b) -> Integer.compare(a[2], b[2]));

        int edgeCount = 0;
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            int weight = edge[2];

            // 사이클이 생성되지 않으면 간선 추가
            if (find(from) != find(to)) {
                union(from, to);
                answer += weight;
                edgeCount++;

                // 신장 트리 완성 조건
                if (edgeCount == N - 1) break;
            }
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 정점 수
        int M = Integer.parseInt(br.readLine()); // 간선 수

        int[][] edges = new int[M][3];
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            edges[i][0] = Integer.parseInt(st.nextToken());
            edges[i][1] = Integer.parseInt(st.nextToken());
            edges[i][2] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(N, edges));
    }
}
import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;
    static int[] rank;

    // 경로 압축 (Find)
    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]); // 경로 압축
    }

    // 랭크 기반 병합 (Union)
    static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y) return; // 이미 같은 집합이면 병합 불필요

        if (rank[x] > rank[y]) {
            parent[y] = x;
        } else if (rank[x] < rank[y]) {
            parent[x] = y;
        } else {
            parent[y] = x;
            rank[x]++;
        }
    }

    public static int solution(int N, int[][] edges) {
        int answer = 0;
        parent = new int[N + 1];
        rank = new int[N + 1];

        // 부모 배열 초기화
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }

        // 간선 가중치 기준으로 정렬
        Arrays.sort(edges, (a, b) -> Integer.compare(a[2], b[2]));

        int edgeCount = 0;
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            int weight = edge[2];

            // 사이클이 생성되지 않으면 간선 추가
            if (find(from) != find(to)) {
                union(from, to);
                answer += weight;
                edgeCount++;

                // 신장 트리 완성 조건
                if (edgeCount == N - 1) break;
            }
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 정점 수
        int M = Integer.parseInt(br.readLine()); // 간선 수

        int[][] edges = new int[M][3];
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            edges[i][0] = Integer.parseInt(st.nextToken());
            edges[i][1] = Integer.parseInt(st.nextToken());
            edges[i][2] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(N, edges));
    }
}
