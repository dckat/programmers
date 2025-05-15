import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 문제의 수
        int M = Integer.parseInt(st.nextToken()); // 문제의 정보

        ArrayList<Integer>[] list = new ArrayList[N+1];
        int[] inDegree = new int[N + 1]; // 진입 차수 배열

        for(int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b); // A -> B
            inDegree[b]++; // B의 진입차수 증가
        }

        // 번호가 작은 문제를 우선
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // 진입 차수가 0인 문제들을 먼저 넣는다
        for(int i = 1; i <= N; i++) {
            if(inDegree[i] == 0) pq.add(i);
        }

        // 위상 정렬
        StringBuilder sb = new StringBuilder();
        while(!pq.isEmpty()) {
            int now = pq.poll();
            sb.append(now).append(" ");

            // 현재 문제와 연결된 문제들 업데이트
            for(int next : list[now]) {
                inDegree[next]--;
                if(inDegree[next] == 0) pq.add(next);
            }
        }

        System.out.println(sb);
        br.close();
    }
}
