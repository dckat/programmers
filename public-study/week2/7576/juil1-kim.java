package ssgStudy.week2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BJ7576 {
    /*
    * 토마토
    * bfs 이용.
    * 토마토가 모두 익을 때까지의 최소 날짜 출력.
    */
    static int N, M;
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");

        M = Integer.parseInt(str[0]);
        N = Integer.parseInt(str[1]);
        int[][] map = new int[N][M];

        Queue<Point> q = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            str = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(str[j]);
                if (map[i][j] == 1)
                    // 큐에 익은 토마토 위치 저장.
                    q.add(new Point(i, j));
            }
        }
        bfs(map, q);
    } // main

    public static void bfs(int[][] map, Queue<Point> q) {
        while (!q.isEmpty()) {
            Point p = q.poll(); // 현재 위치
            for (int i = 0; i < 4; i++) {
                // 다음 위치
                int nw = p.x + dx[i];
                int ny = p.y + dy[i];

                // 범위 벗어난 경우
                if (nw < 0 || ny < 0 || nw >= N || ny >= M) {
                    continue;
                }
                // 다음 위치가 익지 않은 토마토인 경우
                if (map[nw][ny] != 0) {
                    continue;
                }
                map[nw][ny] = map[p.x][p.y] + 1;
                q.add(new Point(nw, ny));
            }
        } // while

        int max = 0;    // 모든 토마토가 익는데 걸린 일수
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) { // 토마토가 모두 안 익은 경우
                    System.out.println(-1);
                    return;
                }
                // max와 map[i][j] 중 더 큰 값 반환 => 토마토가 다 익은 마지막 날
                max = Math.max(max, map[i][j]);
            }
        }
        //출력, 익어있던 토마토는 1일차로 시작
        System.out.println(max - 1);

    }
}

// 좌표 저장 클래스
class Point {
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
