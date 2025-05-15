package SSG_coding_test.week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ14938 {
    /*
    [BOJ]14938 : 서강그라운드
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stk = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(stk.nextToken()); // 지역의 개수
        int m = Integer.parseInt(stk.nextToken()); // 수색 범위
        int r = Integer.parseInt(stk.nextToken()); // 길의 개수

        int[] items = new int[n + 1]; // 아이템 수 저장 위한 배열. index = 지역 번호

        stk = new StringTokenizer(br.readLine());

        for (int i = 1; i <= n; i++){
            items[i] = Integer.parseInt(stk.nextToken());
        }

        List<List<int[]>> graph = graphInit(n); // 그래프 생성
        // 간선 정보 담은 배열.  new int[]{연결된 지역 번호, 길의 길이}

        // 간선 연결
        for (int i = 0; i < r; i++){
            stk = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(stk.nextToken()); // 지역 번호 a
            int b = Integer.parseInt(stk.nextToken()); // 지역 번호 b
            int l = Integer.parseInt(stk.nextToken()); // 길의 길이

            addEdge(graph, a, b, l);
        }

        int[] distance = new int[n + 1];

        System.out.println(dijkstra(graph, n, m, items, distance));

        br.close();
    }

    private static int search(List<List<int[]>> graph, int start, int m, int[] items, int[] distance){
        Arrays.fill(distance, Integer.MAX_VALUE);

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));

        int total = 0;
        distance[start] = 0;
        pq.add(new int[]{start, 0});

        while (!pq.isEmpty()){
            int[] curr = pq.poll();

            int currIdx = curr[0];
            int currWeight = curr[1];


            if (currWeight > distance[currIdx] || currWeight > m) continue;


            total += items[currIdx];

            for (int[] neighbor : graph.get(currIdx)){
                int neighborIdx = neighbor[0];
                int neighborWeight = neighbor[1];
                int currPlusNeighbor = neighborWeight + distance[currIdx];
                if (distance[neighborIdx] > currPlusNeighbor){
                    distance[neighborIdx] = currPlusNeighbor;
                    pq.add(new int[]{neighborIdx, currPlusNeighbor});
                }
            }

        }

        return total;
    }

    private static int dijkstra(List<List<int[]>> graph, int n, int m, int[] items, int[] distance){
        int max = 0;

        for (int start = 1; start <= n; start++){
            max = Math.max(max, search(graph, start, m, items, distance));
        }

        return max;
    }

    private static void addEdge(List<List<int[]>> graph, int a, int b, int l){
        graph.get(a).add(new int[]{b, l});
        graph.get(b).add(new int[]{a, l});
    }

    private static List<List<int[]>> graphInit(int n){
        List<List<int[]>> graph = new ArrayList<>();

        for (int i = 0; i <= n; i++){
            graph.add(new ArrayList<>());
        }

        return graph;
    }

}
