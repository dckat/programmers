package SSG_coding_test.week10;

import java.io.*;
import java.util.*;

public class BOJ1647 {

    /**
     * [BOJ] 1647 : 도시 분할 계획
     */

    static class Edge {
        int to, weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static int solution(int N, int[][] edges) {
        // 모든 집을 최소 비용으로 연결한 최소 신장 트리에서 가장 유지비가 비싼 길을 없애면
        // 최소 비용으로 연결된 두 개의 마을로 분리 가능

        List<List<Edge>> graph = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            graph.get(edge[0]).add(new Edge(edge[1], edge[2]));
            graph.get(edge[1]).add(new Edge(edge[0], edge[2]));
        }

        int totalCost = 0, maxCost = 0;
        boolean[] visited = new boolean[N + 1];

        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));
        pq.offer(new Edge(1, 0)); // 1번 노드를 시작점으로 선택

        int visitedCount = 0;

        while (!pq.isEmpty()) {
            Edge curr = pq.poll();

            if (visited[curr.to]) continue;

            visited[curr.to] = true;
            totalCost += curr.weight;
            maxCost = Math.max(maxCost, curr.weight);

            visitedCount++;
            if (visitedCount == N) break; // 모든 정점을 방문했으면 종료

            for (Edge neighbor : graph.get(curr.to)) {
                if (!visited[neighbor.to]) {
                    pq.offer(neighbor);
                }
            }
        }

        return totalCost - maxCost;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] edges = new int[M][3];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            edges[i][0] = Integer.parseInt(st.nextToken());
            edges[i][1] = Integer.parseInt(st.nextToken());
            edges[i][2] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(N, edges));
    }
}
