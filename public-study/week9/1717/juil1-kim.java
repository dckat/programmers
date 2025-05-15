import java.io.*;
import java.util.*;

public class BJ1717 {
    public static int[] parent; // 각 노드의 부모노드 저장 배열
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n + 1];
        //노드 자기 자신을 부모로 초기화
        for (int i = 0; i <= n; i++)
            parent[i] = i;

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int c = Integer.parseInt(st.nextToken()); // 0:union, 1:find
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (c == 0)
                union(a, b);
            else {
                if (find(a) == find(b))
                    System.out.println("YES");
                else
                    System.out.println("NO");
            }
        }
    }

    public static int find(int a) {
        if (parent[a] == a) // 자기 자신이 부모인 경우
            return a;
        else
            parent[a] = find(parent[a]); // 부모를 최상위 부모로 갱신 (경로 압축)
        return parent[a];
    }

    public static void union(int a, int b) {
        int parentA = find(a);
        int parentB = find(b);

        //a보다 b가 큰 경우 b의 조상은 a의 조상
        if (parentA < parentB)
            parent[parentB] = parentA;
        else
            parent[parentA] = parentB;
    }
}