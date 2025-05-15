import java.io.*;
import java.util.StringTokenizer;

public class 여행가자 {
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        parent = new int[N+1];

        for(int i = 1; i <= N; i++)
            parent[i] = i;

        int M = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++) {
                int a = Integer.parseInt(st.nextToken());
                if(a == 1) union(i, j);
            }
        }

        st = new StringTokenizer(br.readLine());
        int start = find(Integer.parseInt(st.nextToken()));

        boolean notConnected = false;
        for (int i = 1; i < M; i++) {
            int now = Integer.parseInt(st.nextToken());

            // 맨 처음 출발 도시와 연결되어있지 않은 도시가 있으면
            // 여행 계획이 불가능한 것임.
            if (start != find(now)) {
                notConnected = true;
                break;
            }
        }

        if(notConnected) bw.write("NO");
        else bw.write("YES");
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
}