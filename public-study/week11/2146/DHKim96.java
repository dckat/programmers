package SSG_coding_test.week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2146 {
    static int[] dx = {-1, 1, 0, 0}; // 상하좌우
    static int[] dy = {0, 0, -1, 1};
    static boolean[][] visited;
    static List<int[]> edges;


    static boolean canMove(int x, int y, int[][] map){
        return x >= 0 && y >= 0 && x < map.length && y < map[x].length;
    }

    // 섬에 식별 번호를 부여하기 위한 메서드
    // 추가로 각 섬의 가장자리를 파악하여 edges 에 추가
    static void dfs(int currX, int currY, int[][] map, int number){
        boolean isEdge = false;

        map[currX][currY] = number;

        for (int i = 0; i < 4; i++){
            int nextX = currX + dx[i];
            int nextY = currY + dy[i];

            if (canMove(nextX, nextY, map)){
                if (map[nextX][nextY] == 1) {
                    dfs(nextX, nextY, map, number);
                } else if (map[nextX][nextY] == 0) {
                    isEdge = true;
                }
            }
        }

        if (isEdge){
            edges.add(new int[]{currX, currY});
        }
    }

    // 타 섬까지의 거리를 구하는 메서드
    static int bfs(int currX, int currY, int[][] map, int number){
        visited = new boolean[map.length][map[0].length]; // 방문배열 초기화해줘야 함

        int min = Integer.MAX_VALUE;

        Queue<int[]> queue = new LinkedList<>(); // 0 : x / 1 : y / 2 : 다른 섬까지의 거리

//        visited[currX][currY] = true;
        queue.add(new int[]{currX, currY, 0});

        while(!queue.isEmpty()){

            int[] curr = queue.poll();

            currX = curr[0];
            currY = curr[1];
            int distance = curr[2];

//            visited[currX][currY] = true; => 메모리 초과 (큐에서 대기하는 동안에는 방문이 반영되지 않음)

            for (int i = 0; i < 4; i++){
                int nextX = currX + dx[i];
                int nextY = currY + dy[i];

                if (canMove(nextX, nextY, map) && !visited[nextX][nextY] ) {
                    if (map[nextX][nextY] != 0 && map[nextX][nextY] != number) { // 다른 섬에 방문 시 return
                        return distance;
                    } else if (map[nextX][nextY] == 0) { // 바다일 시
                        visited[nextX][nextY] = true;
                        queue.add(new int[]{nextX, nextY, distance + 1}); // 거리 + 1
                    }
                }
            }

        }

        return min;
    }


    static int solution(int N, int[][] map) {
        int answer = Integer.MAX_VALUE;

        visited = new boolean[N][N];
        // 1. 각 섬마다 식별 번호 부여
        edges = new ArrayList<>();

        int number = 2;

        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                if (map[i][j] == 1) {
                    dfs(i, j, map, number++);
                }
            }
        }

        // 2. 섬의 가장자리에서 다른 섬까지의 거리 산출

        for(int[] edge : edges){
            answer = Math.min(answer, bfs(edge[0], edge[1], map, map[edge[0]][edge[1]]));
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // 지도의 크기 N

        int[][] map = new int[N][N];

        for (int i = 0; i < N; i++){

            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solution(N, map));

        br.close();
    }
}
