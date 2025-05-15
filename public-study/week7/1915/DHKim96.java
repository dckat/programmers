package BOJ.dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1915 {
	private static int[][] map;
	private static int n,m;
	private static int max;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(stk.nextToken());
		m = Integer.parseInt(stk.nextToken());
		
		map = new int[n][m];
		
		max = 0;
		
		for (int i = 0; i < n; i++) {
			String line = br.readLine();
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(line.substring(j, j+1));
			}
		}
		
		int answer = 0;
		
		dp();
		
		answer = max * max;
		
		System.out.print(answer);
		br.close();
	}
	
	public static void dp() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(i == 0 && map[0][j] == 1) {
					max = Math.max(max, 1);
					continue;
				} else if (j == 0 && map[i][0] == 1) {
					max = Math.max(max, 1);
					continue;
				} else if(i > 0 && j > 0 && isSquare(i, j)){
					int tempMax = calcMin(i, j);
					map[i][j] = tempMax + 1;
					max = Math.max(max, tempMax + 1);
				}
			}
		}
	}
	
	public static boolean isSquare(int row, int col) {
		return map[row-1][col-1] > 0 && map[row-1][col] > 0 && map[row][col-1] > 0 && map[row][col] > 0;
	}
	
	public static int calcMin(int row, int col) {
		return Math.min( Math.min(map[row-1][col-1], map[row-1][col]), map[row][col-1]);
	}
}
