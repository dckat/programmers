import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 중량제한/540ms
public class BJ1939 {

    // 노드, 간선 가중치
    static class Node {
        int v, w;

        Node(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    static int solution(int N, int M, int[][] edges, int start, int end) {
        ArrayList<ArrayList<Node>> graph = new ArrayList<>(); // 섬 간 연결 관계, 중량 제한 저장
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        int low = Integer.MAX_VALUE; // 이분탐색 하한값
        int high = Integer.MIN_VALUE; // 상한값

        // 그래프에 가중치 정보 저장. 최소값, 최대값 갱신
        for (int[] edge : edges) {
            int v1 = edge[0]; // [v1, v2, w] => [시작노드, 끝노드, 가중치]
            int v2 = edge[1];
            int w = edge[2];
            graph.get(v1).add(new Node(v2, w)); // v1
            graph.get(v2).add(new Node(v1, w));
            low = Math.min(low, w);
            high = Math.max(high, w);
        }

        int answer = Integer.MIN_VALUE;

        while (low <= high) {
            int middle = (low + high) / 2; // 중량 제한 설정
            if (bfs(graph, N, start, end, middle)) { // 현재 중량으로 시작 섬에서 도착 섬까지 이동가능한 지 판별
                low = middle + 1;
                answer = middle;
            } else {
                high = middle - 1;
            }
        }

        return answer;
    }

    static boolean bfs(ArrayList<ArrayList<Node>> graph, int N, int start, int end, int weight) {
        Queue<Node> q = new LinkedList<>(); // 방문할 노드 저장
        boolean[] isVisit = new boolean[N + 1]; // 방문 여부 배열
        q.offer(new Node(start, 0));

        while (!q.isEmpty()) {
            Node curr = q.poll();
            if (curr.v == end) return true; // 현재 노드가 목표 노드라면, 경로가 존재!

            for (Node next : graph.get(curr.v)) {
                if (weight <= next.w && !isVisit[next.v]) { // 간선의 가중치가 현재 중량제한 이상, 그리고 방문하지 않은 경우
                    isVisit[next.v] = true; // 방문 처리
                    q.offer(next);
                }
            }
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 섬(노드)의 개수
        int M = Integer.parseInt(st.nextToken()); // 다리(간선)의 개수

        int[][] edges = new int[M][3]; // 다리 정보 저장
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            edges[i][0] = Integer.parseInt(st.nextToken());
            edges[i][1] = Integer.parseInt(st.nextToken());
            edges[i][2] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken()); // 시작 섬
        int end = Integer.parseInt(st.nextToken()); // 도착 섬

        System.out.println(solution(N, M, edges, start, end));
    }
}