package org.example.Solved;

import java.io.*;
import java.util.StringTokenizer;

public class 로봇청소기14503 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        StringTokenizer st = new StringTokenizer(br.readLine());
//        N x M 사이즈 map 생성
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        boolean[][] visited = new boolean[N][M];

//        로봇 청소기 위치와 방향 입력(r, c, d)
        st = new StringTokenizer(br.readLine());
        int robotR = Integer.parseInt(st.nextToken());
        int robotC = Integer.parseInt(st.nextToken());
        int robotD = Integer.parseInt(st.nextToken());

//        map 생성~
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) visited[i][j] = true;
            }
        }

        int result = 0;
        boolean flag = false;
        Loop : while (true) {
            // first step
            if (!visited[robotR][robotC]) {
                visited[robotR][robotC] = true;
                result++;
            }

            // second step
            flag = false;
            for (int i = 0; i < 4; i++) {
                int nextR = robotR + dr[i];
                int nextC = robotC + dc[i];

                if (nextR >= 0 && nextR < N && nextC >= 0 && nextC < M) {
                    if (!visited[nextR][nextC]) {
                        flag = true;
                        break;
                    }
                }
            }

            if(flag) { // 상하좌우에 빈칸이 있을 경우
                // 0 북 1 동 2 남 3 서 반시계방향으로 돌려준다.
                robotD--;
                if(robotD < 0) {robotD = 3;}
                switch(robotD) {
                    case 0: // 북
                        if(map[robotR-1][robotC] == 0 && !visited[robotR-1][robotC]) {
                            robotR--;
                        }
                        break;
                    case 1: // 동
                        if(map[robotR][robotC+1] == 0 && !visited[robotR][robotC+1]) {
                            robotC++;
                        }
                        break;
                    case 2: // 남
                        if(map[robotR+1][robotC] == 0 && !visited[robotR+1][robotC]) {
                            robotR++;
                        }
                        break;
                    case 3: // 서
                        if(map[robotR][robotC-1] == 0 && !visited[robotR][robotC-1]) {
                            robotC--;
                        }
                        break;
                }
            } else { // 상하좌우에 빈칸이 없을 경우
                switch(robotD) {
                    case 0: // 북
                        robotR++;
                        if(map[robotR][robotC] == 1) {
                            break Loop;
                        }
                        break;
                    case 1: // 동
                        robotC--;
                        if(map[robotR][robotC] == 1) {
                            break Loop;
                        }
                        break;
                    case 2: // 남
                        robotR--;
                        if(map[robotR][robotC] == 1) {
                            break Loop;
                        }
                        break;
                    case 3: // 서
                        robotC++;
                        if(map[robotR][robotC] == 1) {
                            break Loop;
                        }
                        break;
                }
            } // else 문 종료
        } // while 문 종료
        bw.write(result + "");
        bw.flush();
        bw.close();
        br.close();
    }
}
