import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ1520 {

    static int M,N;
    static int[][] map,dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[M][N];
        dp = new int[M][N];

        for (int i = 0; i < M ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < M; i++) {
            Arrays.fill(dp[i],Integer.MIN_VALUE); // 계산되지 않은 상태 나타냄
        }

        System.out.println(dfs(0,0));
    }

    static int dfs(int x, int y){

        int dx[] = {0,1,0,-1};
        int dy[] = {1,0,-1,0};
        if(x == M-1 && y == N-1) return 1; // 오른쪽 아래 도착시 경로 하나 완성

        if(dp[x][y] != Integer.MIN_VALUE) return dp[x][y]; //방문한 곳이니 탐색 전에 존재하는 값을 던져줌

        dp[x][y] = 0; //아래 경로 개수 합산을 위해 초기화
        for (int i = 0; i < 4; i++) { // 상하좌우 탐색
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 0 || ny < 0 || nx >= M || ny >= N) continue; // 지도범위 벗어나면 무시

            if(map[x][y] > map[nx][ny]) { // 현재 위치보다 낮은 곳으로만 이동 가능
                dp[x][y] += dfs(nx, ny);
            }
        }

        return dp[x][y]; // 모든 경로 개수 반환
    }
}