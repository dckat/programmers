import java.io.*;
import java.util.*;

public class BJ1766 {
    // 문제집/544ms

    static String solution(int n, int m, int[][] edges) {
        List<Integer>[] pList = new ArrayList[n + 1]; // 그래프 인접 리스트
        int[] lock = new int[n + 1];                 // 각 노드의 진입 차수 배열
        StringBuilder sb = new StringBuilder();      // 결과를 저장할 StringBuilder

        // 그래프 초기화
        for (int i = 1; i <= n; i++) {
            pList[i] = new ArrayList<>();
        }

        // 간선 정보 입력
        for (int[] edge : edges) {
            int fst = edge[0];
            int sec = edge[1];
            pList[fst].add(sec);
            lock[sec]++;
        }

        // 위상 정렬 수행
        Queue<Integer> q = new PriorityQueue<>(); // 우선순위 큐 사용 (작은 숫자부터 처리)
        for (int i = 1; i <= n; i++) {
            if (lock[i] == 0) { // 진입 차수가 0인 노드 큐에 추가
                q.add(i);
            }
        }

        while (!q.isEmpty()) {
            int pos = q.poll(); // 큐에서 노드 꺼내기
            unlock(pos, pList, lock); // 해당 노드와 연결된 간선 해제
            sb.append(pos).append(" "); // 결과에 추가

            for (int next : pList[pos]) { // 다음 노드 탐색
                if (lock[next] == 0) {   // 진입 차수가 0이 된 경우 큐에 추가
                    q.add(next);
                }
            }
        }

        return sb.toString().trim(); // 결과 문자열 반환
    }

    // 간선을 해제하는 메서드
    static void unlock(int num, List<Integer>[] pList, int[] lock) {
        for (int unlock : pList[num]) {
            lock[unlock]--;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());   // 노드의 개수
        int m = Integer.parseInt(st.nextToken());   // 간선의 개수

        int[][] edges = new int[m][2];              // 간선 정보 저장 배열
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            edges[i][0] = Integer.parseInt(st.nextToken());
            edges[i][1] = Integer.parseInt(st.nextToken());
        }

        // Solution 호출 및 결과 출력
        System.out.println(solution(n, m, edges));
    }
}
