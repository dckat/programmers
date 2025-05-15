import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int I;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, -1, 1, 1, -2, -2, 2, 2};
    static int[] dy = {2, -2, -2, 2, 1, -1, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int TC = Integer.parseInt(br.readLine());

        while(TC--> 0) {
            I = Integer.parseInt(br.readLine());
            map = new int[I][I];
            visited = new boolean[I][I];

            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int dx = Integer.parseInt(st.nextToken());
            int dy = Integer.parseInt(st.nextToken());

            bfs(x, y);

            sb.append(map[dy][dx]).append("\n");
        }
        bw.write(sb.toString());

        bw.flush();
        bw.close();
        br.close();;
    }

    static void bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x,y});
        visited[y][x] = true;

        while(!q.isEmpty()) {
            int[] pq = q.poll();
            int nx = pq[0];
            int ny = pq[1];

            for(int i = 0; i < 8; i++) {
                int nextX = nx + dx[i];
                int nextY = ny + dy[i];

                if(nextX >= 0 && nextY >= 0 && nextX < I && nextY < I) {
                    if(!visited[nextY][nextX]) {
                        q.add(new int[]{nextX, nextY});
                        map[nextY][nextX] = map[ny][nx] + 1;
                        visited[nextY][nextX] = true;
                    }
                }
            }
        }
    }
}
