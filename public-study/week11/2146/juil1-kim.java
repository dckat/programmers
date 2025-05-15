import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ2146 {
    public static int n; //지도(n*n)
    public static int s; // 섬의 개수
    public static int[][] map;
    public static int[][] check;
    public static int min_value; // 가장 짧은 다리 길이

    // BFS 좌표 저장 클래스
    public static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int[] dx = {-1, 1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        n = Integer.parseInt(br.readLine());
        s = 0;
        map = new int[n][n];
        check = new int[n][n];
        // 지도 정보 입력
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        min_value = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (check[i][j] == 0 && map[i][j] == 1) {
                    bfs(i, j, ++s);
                }
            }
        }

        for (int i = 1; i <= s; i++) {
            bfs2(i);
        }

        System.out.println(min_value);
    }

    // 각 연결된 육지로 섬의 번호를 부여하는 bfs
    public static void bfs(int x, int y, int c) {
        Queue<Node> q = new LinkedList<>();
        check[x][y] = c; // 현재 칸에 섬 번호 부여
        q.add(new Node(x, y)); // 시작 노드에 큐 추가
        while (!q.isEmpty()) {
            Node node = q.poll();
            for (int d = 0; d < 4; d++) { // 상하좌우 탐색
                int nx = node.x + dx[d];
                int ny = node.y + dy[d];
                if (0 <= nx && nx < n && 0 <= ny && ny < n) { // 지도 범위 내인지 확인
                    if (map[nx][ny] == 1) {
                        if (check[nx][ny] == 0) {
                            check[nx][ny] = c;
                            q.add(new Node(nx, ny));
                        }
                    }

                }
            }
        }
    }

    // 각 연결된 섬에서 다리를 놓는 bfs
    public static void bfs2(int c) {
        Queue<Node> q = new LinkedList<>();
        int[][] visited = new int[n][n]; // 방문 여부, 다리 길이 저장 배열
        for (int i = 0; i < n; i++) {
            Arrays.fill(visited[i], -1); // 방문 배열 -1(미방문 상태) 초기화
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (check[i][j] == c) { // 현재 섬에 속하는 칸이면
                    visited[i][j] = 0; // 시작점 다리 길이 = 0
                    q.add(new Node(i, j));
                }
            }
        }

        while (!q.isEmpty()) {
            Node node = q.poll();
            for (int d = 0; d < 4; d++) {
                int nx = node.x + dx[d];
                int ny = node.y + dy[d];
                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                // 4방 탐색한 경우 중 바다인 경우 -> 다리를 놓아야함
                if (map[nx][ny] == 0) {
                    if (visited[nx][ny] == -1 || visited[nx][ny] > visited[node.x][node.y] + 1) {
                        visited[nx][ny] = visited[node.x][node.y] + 1;
                        q.add(new Node(nx, ny));
                    }
                } else if (check[nx][ny] != c) { // 4방 탐색했을 때 다른 섬일 경우 -> 다리를 끝내고 다리길이 갱신
                    min_value = Math.min(min_value, visited[node.x][node.y]);
                }

            }
        }
    }
}