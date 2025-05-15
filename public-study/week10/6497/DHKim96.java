package SSG_coding_test.week10;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Arrays;

public class BOJ6497 {
    /*
    [BOJ] 6497 : 전력난
    => "절약"할 수 있는 최대 액수
    => 입력은 "여러 개"의 테스트 케이스로 구분
    */

    private static int[] parent;
    private static int[] rank;

    static int find(int x){
        if(parent[x] == x) { // 루트노드라면
            return x;
        }
        return parent[x] = find(parent[x]); // 경로 압축
    }

    static void union(int x, int y){
        x = find(x);
        y = find(y);

        if (x != y) {
            if (rank[x] > rank[y]) {
                parent[y] = x;
            } else if (rank[x] < rank[y]) {
                parent[x] = y;
            } else {
                parent[y] = x;
                rank[x]++;
            }
        }
    }

    static int solution(int m, int[][] edges) {

        int totalCost = 0;
        int selectTotalCost = 0;

        parent = new int[m];
        rank = new int[m];

        for (int i = 0; i < m; i++){
            parent[i] = i;
        }

        Arrays.sort(edges, (o1, o2) -> o1[2] - o2[2]); // 가중치 오름차순

        for (int[] edge : edges) {
            totalCost += edge[2];
            if (find(edge[0]) != find(edge[1])) { // 사이클 형성하지 않음
                selectTotalCost += edge[2];
                union(edge[0], edge[1]);
            }
        }

        return totalCost - selectTotalCost; // 초기 연결 그래프의 비용 - mst 연결 비용
    }

    static void printResults(List<Integer> results) {
        for (int answer : results) {
            System.out.println(answer);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<Integer> results = new ArrayList<>();

        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken()); // 집의 수
            int n = Integer.parseInt(st.nextToken()); // 길의 수

            if (m == 0 && n == 0) break;

            int[][] edges = new int[n][3]; // from, to, weight

            // 여러 개의 테스트케이스로 구분

            for (int i = 0; i < n; i++){
                st = new StringTokenizer(br.readLine());
                edges[i][0] = Integer.parseInt(st.nextToken()); // from
                edges[i][1] = Integer.parseInt(st.nextToken()); // to
                edges[i][2] = Integer.parseInt(st.nextToken()); // weight 거리 == 가로등을 킬 때 소요되는 비용
            }

            results.add(solution(m, edges));
        }

        printResults(results);

        br.close();
    }
}