import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static final int SIZE = 101; // 격자 크기
    static boolean[][] grid = new boolean[SIZE][SIZE];
    static int[] dx = {1, 0, -1, 0}; // 오 위 왼 아
    static int[] dy = {0, -1, 0, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            drawDragon(x, y, d, g);
        }

        System.out.println(result());
    }

    static void drawDragon(int x, int y, int d, int g) {
        List<Integer> directions = new ArrayList<>();
        directions.add(d);
        grid[y][x] = true;

        // 세대별 방향 구하기
        for(int i = 0; i < g; i++) { // g세대까지 반복
            int size = directions.size(); // 현재 저장된 방향 개수
            for(int j = size-1; j >=0; j--) { // 기존 방향을 역순으로 읽음
                directions.add((directions.get(j) + 1) % 4); // 90도 회전한 방향 추가
            }
        }

        for(int dir : directions) {
            x += dx[dir];
            y += dy[dir];
            if(x >= 0 && x < SIZE && y >= 0 && y < SIZE) {
                grid[y][x] = true;
            }
        }
    }

    static int result() {
        int count = 0;
        for(int i = 0; i < SIZE-1; i++) {
            for(int j = 0; j < SIZE-1; j++) {
                if(grid[i][j] && grid[i+1][j] && grid[i][j+1] && grid[i+1][j+1]) {
                    count++;
                }
            }
        }
        return count;
    }
}
