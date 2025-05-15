import java.io.*;
import java.util.StringTokenizer;

public class 집합의표현 {
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        parent = new int[N+1];
        for(int i = 1; i <= N; i++)
            parent[i] = i;

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken()); // 0 or 1
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(cmd == 0) union(a, b);
            else {
                bw.write(isSameParent(a, b) ? "YES" : "NO");
                bw.write("\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static int find(int x) {
        if(x == parent[x])
            return x;
        else
            return parent[x] = find(parent[x]);
    }

    public static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if(x != y) {
            if(x < y) {
                parent[y] = x;
            } else {
                parent[x] = y;
            }
        }
    }

    public static boolean isSameParent(int x, int y) {
        x = find(x);
        y = find(y);

        if(x==y) return true;
        else return false;
    }
}
