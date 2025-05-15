package SSG_coding_test.week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1238 {
    /*
        * [BOJ]1238 : 파티
        * 다익스트라(Dijkstra) 알고리즘 : 우선순위 큐 + bfs + dp
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stk = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(stk.nextToken()); // 학생의 수
        int M = Integer.parseInt(stk.nextToken()); // X번 마을의 단방향 도로 개수
        int X = Integer.parseInt(stk.nextToken()); // 마을 번호

        List<List<int[]>> graph = new ArrayList<>();

        // 인접 그래프 초기화
        for (int i = 0; i < N + 1; i++){
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++){
            stk = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(stk.nextToken()); // 도로 시작점
            int end = Integer.parseInt(stk.nextToken()); // 도로 끝점
            int T = Integer.parseInt(stk.nextToken()); // 소요 시간

            graph.get(start).add(new int[]{end, T}); // 간선 연결
        }

        System.out.println(solution(graph, N, X));

        br.close();
    }

    private static int solution(List<List<int[]>> graph, int N, int X){

        int maxTime = 0;

        // N명의 학생들 중 "오고 가는데" 가장 많은 시간을 소비하는 학생

        // 1. X에서 모든 노드까지 가는 소요 시간
        int[] fromX = dijkstra(graph, N, X);

        for (int start = 1; start < N + 1; start++){
            // 2. 집에서 X로 갈 때 최단 소요 시간
            int[] HomeToX = dijkstra(graph, N, start);

            maxTime = Math.max(maxTime, fromX[start] + HomeToX[X]);
        }

        return maxTime;
    }


    private static int[] dijkstra(List<List<int[]>> graph, int N, int start) {
        // 집이 1 일때, 2일 때, 3일 때 ... N번 일 때 까지의 소요 시간 중 가장 긴 소요 시간 출력
        int[] times = new int[N + 1];

        Arrays.fill(times, Integer.MAX_VALUE);

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1])); // 소요 시간이 낮을 수록 우선순위

        times[start] = 0;

        pq.add(new int[]{start, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            int currNode = cur[0];
            int currTime = cur[1];

            if (currTime > times[currNode]) continue;

            for (int[] neighbor : graph.get(currNode)) {
                int neighborNode = neighbor[0];
                int neighborTime = neighbor[1];

                if (currTime + neighborTime < times[neighborNode]) {
                    times[neighborNode] = currTime + neighborTime;
                    pq.add(new int[]{neighborNode, times[neighborNode]});
                }
            }

        }

        return times;
    }

}
