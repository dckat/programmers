package SSG_coding_test.week2;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ7562 {
    /*
        * 나이트의 이동
            : 나이트가 최소 몇 번만에 이동할 수 있는가? => 최단거리 산출
                ==> bfs
                * BFS는 가까운 노드부터 탐색을 진행하므로,
                * 나이트가 목적지에 도달하면 그때의 경로가 최소 이동 횟수가 됨.
    */
    static class Knight{
        int row;
        int col;
        int moves;

        public Knight(int x, int y, int moves){
            this.row = x;
            this.col = y;
            this.moves = moves;
        }
    }

    // 나이트의 이동 가능 범위
    private static int[] mRow = {-2, -1, 1, 2, -2, -1, 1, 2}; // 절반은 대칭 구조
    private static int[] mCol = {1, 2, 2, 1, -1, -2, -2, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine()); // 테스트케이스의 개수

        int[] destination = new int[2];

        StringBuilder sb = new StringBuilder();

        while(T-- > 0){
            // 체스판의 한 변의 길이(체스판은 정사각형)
            int length = Integer.parseInt(br.readLine());
            boolean[][] board = new boolean[length][length]; // 방문 배열

            // 나이트가 현재 있는 칸(0,0 ~ length - 1,length - 1)
            String[] input = br.readLine().split(" ");
            int x = Integer.parseInt(input[0]);
            int y = Integer.parseInt(input[1]);

            // 나이트가 이동하려는 칸
            String[] input2 = br.readLine().split(" ");
            destination[0] = Integer.parseInt(input2[0]);
            destination[1] = Integer.parseInt(input2[1]);

            sb.append(bfs(board, x, y, destination)).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    // 탐색
    public static int bfs(boolean[][] board, int x, int y, int[] destination){
        Queue<Knight> q = new LinkedList<>();

        q.add(new Knight(x,y,0));

        board[x][y] = true;

        int destinationX = destination[0];
        int destinationY = destination[1];

        while(!q.isEmpty()){

            Knight knight = q.poll();

            int currRow = knight.row;
            int currCol = knight.col;
            int moves = knight.moves;

            // 목적지 도착 확인
            if (currRow == destinationX && currCol == destinationY) {
                return moves;
            }

            for (int i = 0; i < 8; i++){
                int nextRow = currRow + mRow[i];
                int nextCol = currCol + mCol[i];

                if (canMove(board, nextRow, nextCol)){
                    q.add(new Knight(nextRow, nextCol, moves+1));
                    board[nextRow][nextCol] = true;
                }
            }
        }

        return -1; // 이 라인은 실행되지 않음(목적지에 도착하지 않는 경우가 존재하지 않기에)
    }

    // 이동 가능 여부
    public static boolean canMove(boolean[][] board, int x, int y){
        return x >= 0 && y >= 0 && x < board.length && y < board.length && !board[x][y];
    }
}
