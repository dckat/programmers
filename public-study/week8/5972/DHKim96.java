package SSG_coding_test.week8;

import java.io.*;
import java.util.*;

public class BOJ5972 {
    private static List<List<int[]>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(stk.nextToken());
        int M = Integer.parseInt(stk.nextToken());
        int[] distance = new int[N + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);

        // 그래프 초기화
        graphInit(N);

        for (int i = 0; i < M; i++) {
            stk = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(stk.nextToken());
            int y = Integer.parseInt(stk.nextToken());
            int weight = Integer.parseInt(stk.nextToken());
            addEdge(x, y, weight);
        }

        // 다익스트라 실행 및 결과 출력
        System.out.println(dijkstra(1, N, distance));
        br.close();
    }

    public static int dijkstra(int start, int N, int[] distance) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        distance[start] = 0;
        pq.add(new int[]{start, 0});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int currNode = curr[0];
            int currDist = curr[1];

            if (currDist > distance[currNode]) continue;

            for (int[] next : graph.get(currNode)) {
                int nextNode = next[0];
                int nextWeight = next[1];
                if (distance[currNode] + nextWeight < distance[nextNode]) {
                    distance[nextNode] = distance[currNode] + nextWeight;
                    pq.add(new int[]{nextNode, distance[nextNode]});
                }
            }
        }

        return distance[N];
    }

    public static void addEdge(int x, int y, int weight) {
        graph.get(x).add(new int[]{y, weight});
        graph.get(y).add(new int[]{x, weight});
    }

    public static void graphInit(int N) {
        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
    }
}
