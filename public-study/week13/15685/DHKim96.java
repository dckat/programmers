import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;

public class Main {

    // 방향(우 상 좌 하)
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};

    static boolean[][] board;

    static void drawCurve(int x, int y, int d, int g){

        List<Integer> direction = new ArrayList<>(); // 방향을 담을 리스트

        direction.add(d);

        board[x][y] = true;

        // 이전 세대의 끝점을 기준으로 90도 회전
        // 즉 시작점을 기준으로 90도 회전한 것과는 다르다
        // => (현재 방향 + 1) % 4 를 사용

        // 예시에 따르면 방향은
        // 0
        // 0 1
        // 0 1 2 1
        // 0 1 2 1 2 3 2 1
        // => 최근에 진행된 방향을 기준으로 먼저 방향 진행됨(역순)

        for(int i = 0; i < g; i++){
            int size = direction.size();
            for(int j = size - 1; j >= 0; j--){
                direction.add((direction.get(j) + 1) % 4);
            }
        }

        for (int dir : direction) {
            x += dx[dir];
            y += dy[dir];

            board[x][y] = true;
        }
    }

    static int solution(int [][] curves) {

        int answer = 0;

        board = new boolean[101][101]; // 격자

        for (int[] curve : curves) {
            drawCurve(curve[0], curve[1], curve[2], curve[3]);
        }

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
               if (board[i][j] && board[i + 1][j] && board[i + 1][j + 1] && board[i][j + 1]) {
                   answer++;
               }
            }
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // 드래곤 커브의 개수
        int[][] curves = new int[N][4];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            curves[i][0] = Integer.parseInt(st.nextToken()); // 드래곤커브의 x 시작점
            curves[i][1] = Integer.parseInt(st.nextToken()); // 드래곤커브의 y 시작점
            curves[i][2] = Integer.parseInt(st.nextToken()); // 시작 방향
            curves[i][3] = Integer.parseInt(st.nextToken()); // 세대
        }

        System.out.println(solution(curves));

        br.close();
    }
}
