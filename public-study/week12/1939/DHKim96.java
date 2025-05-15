import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static void graphInit(List<List<int[]>> graph, int N, int[][] edges){
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < edges.length; i++) {
            graph.get(edges[i][0]).add(new int[]{edges[i][1], edges[i][2]});
            graph.get(edges[i][1]).add(new int[]{edges[i][0], edges[i][2]});
        }
    }


    static int solution(int N, int M, int[][] edges, int start, int end){
        // 한 번의 이동으로 옮길 수 있는 중량의 최댓값
        int answer = 0;

        List<List<int[]>> graph = new ArrayList<>();

        graphInit(graph, N, edges);

        Arrays.sort(edges, Comparator.comparingInt(o -> o[2]));

        int right = edges[edges.length - 1][2]; // 최댓값 : 제한 중량의 최댓값

        int left = edges[0][2]; // 최솟값

        while(left <= right){
            boolean isPossible = false;

            int mid = (left + right) / 2;

            Queue<Integer> q = new LinkedList<>();
            boolean[] visited = new boolean[N + 1];

            q.offer(start);

            while(!q.isEmpty()){
                int cur = q.poll();

                if (cur == end) {
                    isPossible = true;
                    break;
                }

                for (int[] neighbor : graph.get(cur)) {
                    int neighborNode = neighbor[0];
                    if (!visited[neighborNode] && neighbor[1] >= mid) {
                        visited[neighborNode] = true;
                        q.add(neighborNode);
                    }
                }
            }

            if (!isPossible){
                right = mid - 1;
            } else {
                answer = mid;
                left = mid + 1;
            }
        }

        return answer;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 섬의 개수
        int M = Integer.parseInt(st.nextToken()); // 다리의 개수

        int[][] edges = new int[M][3];

        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());

            edges[i][0] = Integer.parseInt(st.nextToken()); // FROM
            edges[i][1] = Integer.parseInt(st.nextToken()); // TO
            edges[i][2] = Integer.parseInt(st.nextToken()); // 제한 중량
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        System.out.println(solution(N, M, edges, start, end));

        br.close();

    }
}
