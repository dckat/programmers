package SSG_coding_test.week5;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ14503 {
    /*
        * [BOJ]14503 : 로봇 청소기
           => dfs
    */
    private static final int[] dRow = {-1, 0, 1, 0}; // 북 동 남 서
    private static final int[] dCol = {0, 1, 0, -1}; // 북 동 남 서

    private static int[][] room;
    private static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // row N
        int M = Integer.parseInt(st.nextToken()); // col M

        room = new int[N][M];
        count = 0;

        st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken()); // 로봇 청소기가 위치한 행
        int c = Integer.parseInt(st.nextToken()); // 로봇 청소기가 위치한 열
        int d = Integer.parseInt(st.nextToken()); // 로봇 청소기의 방향

        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++){
                room[i][j] = Integer.parseInt(st.nextToken()); // 0 : 미청소 / 1 : 벽
                // 방의 가장 북쪽, 가장 남쪽, 가장 서쪽, 가장 동쪽 줄 중 하나 이상에 위치한 모든 칸에는 벽
            }
        }

        dfs(r, c, d);

        // 작동을 멈출 때까지 청소하는 칸의 갯수 출력
        bw.write(count + "");
        bw.flush();
        bw.close();
        br.close();
    }

    public static void dfs(int r, int c, int d){
        // 현재 칸이 아직 청소되지 않은 경우 현재 칸 청소
        if (canClean(r, c)) {
            count++;
            room[r][c] = 2; // 청소 처리
        }

        boolean canClean = false;

        // 청소 가능 여부 확인
        for (int i = 0; i < 4; i++){
            int nextRow = r + dRow[i];
            int nextCol = c + dCol[i];

            if (canClean(nextRow, nextCol)){
                canClean = true;
                break;
            }
        }

        // 청소되지 않은 빈 칸이 없는 경우
        if (!canClean){
            // 후진 = 반대 방향으로 이동 => (d + 2) % 4 인 인덱스
            // 후진은 곧 방향은 그대로
            int reverseRow = r + dRow[(d + 2) % 4];
            int reverseCol = c + dCol[(d + 2) % 4];
            // 후진 가능 여부 확인
            if (canMove(reverseRow, reverseCol)){
                dfs(reverseRow, reverseCol, d);
            } else {
                // 뒤쪽 칸이 벽이라 후진할 수 없다면 작동 중단
                return;
            }
        } else {
            // 청소되지 않은 빈 칸이 존재할 경우
            // 반시계 방향으로 회전
            d = ((d - 1) + 4 ) % 4;
            // 앞쪽 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진
            int nextRow = r + dRow[d];
            int nextCol = c + dCol[d];

            while(!canClean(nextRow, nextCol)){
                d = ((d - 1) + 4 ) % 4;
                nextRow = r + dRow[d];
                nextCol = c + dCol[d];
            }

            // 탈출하면 곧 해당 row, col 은 청소 가능 칸
            dfs(nextRow, nextCol, d);
        }
    }

    // 주변에 청소 가능 칸 존재 여부
    public static boolean canClean(int r, int c){
        return r < room.length && r >= 0 && c < room[r].length && c >= 0 && room[r][c] == 0;
    }

    // 이동 가능 여부
    public static boolean canMove(int r, int c){
        return r < room.length && r >= 0 && c < room[r].length && c >= 0 && room[r][c] != 1;
    }
}

