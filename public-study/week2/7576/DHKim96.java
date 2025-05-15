package SSG_coding_test.week2;

import java.io.*;
import java.util.*;

public class BOJ7576 {
	/**
	 *  * 토마토
	 *  	* 토마토 : 노드
	 *  	* 인접 토마토가 익음 : 노드가 연결(그래프)
	 *  	* 토마토가 혼자 저절로 익는 경우는 없다 : 단절 노드 X
	 *  	* 하루가 지나면 토마토의 인접 토마토들이 익게 됨 : 너비 우선 탐색(가장 가까운 노드부터 탐색)
	 *  	* 한 노드에서 시작하는 게 아니라 여러 노드에서 탐색 시작
	 */
	static class Tomato{
		int row;
		int col;
		int day;
		
		Tomato(int row, int col, int day){
			this.row = row;
			this.col = col;
			this.day = day;
		}
	}
	
	private static int[] mRow = {-1, 1, 0, 0}; // 상 하 좌 우
	private static int[] mCol = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] input = br.readLine().split(" ");
		
		int M = Integer.parseInt(input[0]);// 상자의 가로 칸 수(열)
		int N = Integer.parseInt(input[1]);// 상자의 세로 칸 수(행)
		
		int[][] box = new int[N][M];
		
		Queue<Tomato> queue = new LinkedList<>(); // 익은 토마토 위치 담은 리스트
		
		for (int i = 0; i < N; i++) {
			String[] line = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				int tomato = Integer.parseInt(line[j]);
				if (tomato == 1) queue.add(new Tomato(i, j, 0)); // 모든 시작점(익은 토마토)을 큐에 넣고 시작 => 여러 노드에서 bfs 시작
				box[i][j] = tomato;
			}
		}

		bw.write(bfs(box, queue) + "");
		bw.flush();
		bw.close();
		br.close();

	}

	public static int bfs(int[][] box, Queue<Tomato> queue) {

		if (queue.isEmpty()) return -1; // 익은 토마토가 없다면 바로 리턴

		int finalDay  = 0;
			
		while(!queue.isEmpty()) {
			Tomato tomato = queue.poll();
			int currRow = tomato.row;
			int currCol = tomato.col;
			
			// 현재 토마토의 상하좌우 탐색
			for (int i = 0; i < 4; i++) {
				int nextRow = currRow + mRow[i];
				int nextCol = currCol + mCol[i];

				if (canMove(box, nextRow, nextCol)) {
					queue.add(new Tomato(nextRow, nextCol, tomato.day + 1)); // 익은 토마토를 큐에 넣기
					box[nextRow][nextCol] = tomato.day + 1; // 익는 소요일 저장
				}
			}

			finalDay  = tomato.day;
		}

		return checkTomatoes(box) ? finalDay : -1;
	}
	
	
	public static boolean canMove(int[][] box, int x, int y) {
		return x >= 0 && x < box.length && y >= 0 && y < box[0].length && box[x][y] == 0;
	}

	// 토마토가 모두 익지 못하는 상황 == box에 0 이 잔존
	public static boolean checkTomatoes(int[][] box) {

		for (int[] rows : box) {
			for (int cell : rows) {
				if (cell == 0) return false;
			}
		}

		return true;
	}
}
