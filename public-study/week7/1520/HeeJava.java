import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 내리막길 {
    static int[][] map, dp;
    static int M,N;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken()); // 세로 M
        N = Integer.parseInt(st.nextToken()); // 가로 N
        map = new int[M][N];
        dp = new int[M][N];

        for(int i = 0; i < M; i++) {
            Arrays.fill(dp[i], -1);
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bw.write(DFS(0,0) + "");
        bw.flush();
        bw.close();
        br.close();
    }

    public static int DFS(int r, int c) {
        if(r == M-1 && c == N-1) return 1; // 목적지에 도착하면
        if(dp[r][c] != -1) return dp[r][c]; // 방문한 경우면

        dp[r][c] = 0;
        for(int i = 0; i < 4; i++) {
            int ndr = r + dr[i];
            int ndc = c + dc[i];

            if(ndr >= 0 && ndc >= 0 && ndr < M && ndc < N) {
                if(map[ndr][ndc] < map[r][c]) {
                    dp[r][c] += DFS(ndr, ndc);
                }
            }
        }

        return dp[r][c];
    }
}