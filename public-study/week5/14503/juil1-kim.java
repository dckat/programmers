package ssgStudy.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ14503 {
    /*
    * 로봇 청소기
    */
    static int N, M;
    static int[] dx = {-1, 0, 1, 0}; // 북 동 남 서
    static int[] dy = {0, 1, 0, -1}; // 북 동 남 서

    static int[][] room;
    static int count; // 로봇이 청소한 칸의 개수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        room = new int[N][M];

        st = new StringTokenizer(br.readLine());

        // 시작 좌표, 시작 방향 (0: 북, 1: 동, 2: 남, 3: 서)
        int startX = Integer.parseInt(st.nextToken());
        int startY = Integer.parseInt(st.nextToken());
        int dir = Integer.parseInt(st.nextToken());

        // 방 상태 저장
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 로봇 시작 위치 -> 청소하고 count++
        if(room[startX][startY] == 0) {
            room[startX][startY] = 2; // 2: 청소 처리
            count++;
        }

        search(startX, startY, dir);
        System.out.println(count);
        br.close();
    }

    public static void search(int x, int y, int dir) {
        if(room[x][y] == 0) {
            room[x][y] = 2;
            count++;
        }

        boolean flag = false;
        int nx = 0;
        int ny = 0;

        for (int i = 0; i < 4; i++) { // 북 동 남 서, 네 방향 탐색
            nx = x + dx[i];
            ny = y + dy[i];

            // 청소되지 않은 빈 칸이 있는지 확인.
            // 1. 세로 좌표 방의 범위 확인
            // 2. 가로 좌표 방의 범위 확인
            // 3. 해당 칸이 빈 칸인지 확인
            if(0 <= nx && nx < N && 0 <= ny && ny < M && room[nx][ny] == 0) {
                flag = true;
            }
        }

        if(flag){  //현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우
            // dir (0: 북, 1: 동, 2: 남, 3: 서)
            dir = (dir + 3) % 4;  //반시계 90도 회전
            nx = x + dx[dir]; //회전 후 이동
            ny = y + dy[dir];

            // 청소하지 않은 칸 만날떄까지 회전시켜주기
            while(room[nx][ny] != 0){
                dir = (dir + 3) % 4;
                nx = x + dx[dir];
                ny = y + dy[dir];
            }

            if(0 <= nx && nx < N && 0 <= ny && ny < M && room[nx][ny] == 0){
                room[nx][ny] = 2;
                count++;
                search(nx, ny, dir);
            } // if
        } // if
        else {    //현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우
            nx = x - dx[dir];
            ny = y - dy[dir];
            if(0 <= nx && nx < N && 0 <= ny && ny < M
                    && room[nx][ny] != 1){
                //바라보는 방향을 유지한 채로 한 칸 후진할 수 있다면 한 칸 후진하고 1번으로 돌아간다.
                search(nx, ny, dir);
            }
        } // else

    } // search
}
