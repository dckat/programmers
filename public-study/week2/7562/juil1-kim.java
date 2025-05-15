package ssgStudy.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BJ7562 {
    /*
    * 나이트의 이동
    * bfs 이용.
    */

    // 나이트가 이동할 수 있는 경우의 수
    static int[] dx = {-1, -2, -2, -1, 1, 2, 2, 1};
    static int[] dy = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[][] map;
    static boolean[][] visited;
    static Queue<int[]> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            q = new LinkedList<>();
            int length = Integer.parseInt(br.readLine());
            map = new int[length][length];
            visited = new boolean[length][length];

            String[] str = br.readLine().split(" ");
            int x1 = Integer.parseInt(str[0]);
            int y1 = Integer.parseInt(str[1]);

            String[] str2 = br.readLine().split(" ");
            int x2 = Integer.parseInt(str2[0]);
            int y2 = Integer.parseInt(str2[1]);

            q.offer(new int[]{x1, y1});     //시작 위치 큐에 추가
            visited[x1][y1] = true;         //시작 위치 방문 처리

            while (!q.isEmpty()) {
                int[] temp = q.poll();  // 현재 위치
                int x = temp[0];
                int y = temp[1];

                if (x == x2 && y == y2) // 현재 위치 == 목표 위치
                    break;

                for (int j = 0; j < 8; j++) {
                    // 다음 위치
                    int nx = x + dx[j];
                    int ny = y + dy[j];

                    // 체스판 범위 벗어나는지 확인
                    if (nx < 0 || ny < 0 || nx >= length || ny >= length) continue;
                    // 새로운 위치가 이미 방문 된 경우
                    if (visited[nx][ny] || map[nx][ny] != 0) continue;
                    map[nx][ny] = map[x][y] + 1;    // 이동 횟수 저장
                    q.offer(new int[]{nx, ny});     // 새로운 위치 큐에 저장
                }
            }
            System.out.println(map[x2][y2]);
        } // for
    } // main
}
