import java.io.*;
import java.util.StringTokenizer;

public class BJ1976 {
    public static int[] parent; // 각 도시의 부모노드 저장 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        parent = new int[n + 1];
        //노드 자기 자신을 부모로 초기화
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        // Union-Find 병합
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                int node = Integer.parseInt(st.nextToken());
                if (node == 1) // 두 도시가 연결된 경우 병합
                    union(i, j);
            }
        }

        // 방문 하고자 하는 도시들의 조상이 같은 경우 -> 어떤 경로를 거치든 방문이 가능
        // 조상이 다른 경우에는 이동할 수 없음
        // 도시 하나씩 조상을 꺼내어 비교해 다 같은 경우 YES, 다른 경우 NO 출력
        st = new StringTokenizer(br.readLine());
        String answer = "YES"; // 기본값 YES
        int top = top(Integer.parseInt(st.nextToken()));
        while (st.hasMoreTokens()) {
            int top2 = top(Integer.parseInt(st.nextToken()));
            if (top != top2) { // 대표 노드가 다른 경우
                answer = "NO";
                break;
            }
        }
        bw.write(answer); // 출력
        br.close();
        bw.close();
    }

    public static int top(int a) {
        if (a == parent[a]) // 자기 자신이 부모인 경우
            return a;
        parent[a] = top(parent[a]); // 부모를 최상위 부모로 갱신 (경로 압축)
        return parent[a];
    }

    public static void union(int a, int b) {
        int topA = top(a);
        int topB = top(b);

        //a보다 b가 큰 경우 b의 조상은 a의 조상
        if (topA < topB)
            parent[topB] = topA;
        else
            parent[topA] = topB;
    }
}