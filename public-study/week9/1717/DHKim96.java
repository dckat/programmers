package SSG_coding_test.week9;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ1717 {
    /*
    [BOJ] 1717 : 집합의 표현
    * 분리 집합, 유니온 파인드
        주어진 집합은 서로소 집합(공통 부분이 없는 집합)
        집합 관계를 효율적으로 판단할 수 있는 방법 == 유니온 파인드
     */

    static int[] parent;

    static void union(int x, int y){
        x = find(x);
        y = find(y);

        if (x != y) parent[y] = x; // 루트 노드의 불일치는 곧 서로 다른 집합임을 의미
    }

    static boolean isUnion(int x, int y){
        return find(x) == find(y);
    }
    
    // 경로 압축 포함
    static int find(int x){
        if (parent[x] == x) return x; // 루트 노드일 경우 탈출
        return parent[x] = find(parent[x]);
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 집합의 개수
        int m = Integer.parseInt(st.nextToken()); // 연산의 개수

        parent = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int type = Integer.parseInt(st.nextToken()); // 0 이면 union, 1이면 find
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if (type == 1) {
                sb.append(isUnion(x, y) ? "YES" : "NO").append("\n");
            } else {
                union(x, y);
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();

    }
}
