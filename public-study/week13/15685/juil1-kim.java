import java.io.*;
import java.util.*;

public class BJ15685 {
    // 드래곤커브/112ms

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};

    static int solution(int n, int[][] curves) {
        boolean[][] map = new boolean[101][101];
        int result = 0; // 정사각형 개수

        // 각 드래곤 커브를 맵에 그리기
        for (int i = 0; i < n; i++) {
            int x = curves[i][0];
            int y = curves[i][1];
            int d = curves[i][2];
            int g = curves[i][3];
            drawDragonCurve(map, x, y, d, g);
        }

        // 정사각형 개수 세기
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (map[i][j] && map[i][j + 1] && map[i + 1][j] && map[i + 1][j + 1]) {
                    result++;
                }
            }
        }

        return result;
    }

    static void drawDragonCurve(boolean[][] map, int x, int y, int d, int g) {
        List<Integer> directions = new ArrayList<>();
        directions.add(d);

        // 각 세대별 방향 추가
        for (int i = 1; i <= g; i++) {
            for (int j = directions.size() - 1; j >= 0; j--) {
                directions.add((directions.get(j) + 1) % 4);
            }
        }

        // 초기 점 표시
        map[y][x] = true;

        // 방향에 따라 점 찍기
        for (Integer direction : directions) {
            x += dx[direction];
            y += dy[direction];
            map[y][x] = true;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine()); // 드래곤 커브의 개수
        int[][] curves = new int[n][4];          // 각 드래곤 커브 정보 저장

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            curves[i][0] = Integer.parseInt(st.nextToken()); // x 좌표
            curves[i][1] = Integer.parseInt(st.nextToken()); // y 좌표
            curves[i][2] = Integer.parseInt(st.nextToken()); // 시작 방향
            curves[i][3] = Integer.parseInt(st.nextToken()); // 세대 수
        }

        br.close();

        // Solution 호출 및 결과 출력
        System.out.println(solution(n, curves));
    }
}