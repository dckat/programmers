import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int B,W;
    static int count;
    static int N,M;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken()); // 가로
        N = Integer.parseInt(st.nextToken()); // 세로

        map = new char[N][M];
        visited = new boolean[N][M];

        for(int i = 0; i < N; i++) { // 맵 만들기
            String line = br.readLine();
            for(int j = 0; j < line.length(); j++) {
                map[i][j] = line.charAt(j);
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {

                if(!visited[i][j]) {
                    count = 0;
                    dfs(i, j, map[i][j]); // 0, 0, W

                    if(map[i][j] == 'W') {
                        W += count * count;
                    } else if(map[i][j] == 'B') {
                        B += count * count;
                    }
                }
            }
        }

        bw.write(W + " " + B);
        bw.flush();
        bw.close();
        br.close();
    }

    static void dfs(int x, int y, char color) {
        visited[x][y] = true;
        count++;
        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i]; // {-1, 1, 0, 0}
            int ny = y + dy[i]; // {0, 0, -1, 1}

            if(nx >= 0 && ny >= 0 && nx < N && ny < M) {
                if(!visited[nx][ny] && map[nx][ny] == color) {
                    dfs(nx, ny, map[nx][ny]); // 0, 1, W
                }
            }
        }
    }
}
