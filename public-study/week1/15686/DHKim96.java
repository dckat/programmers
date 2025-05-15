package SSG_coding_test.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ15686 {
	/*
	 *  BOJ NO.15686 : 치킨 배달
	 *  => 백트래킹
	 */
	
	public static boolean[][] closed; // 폐업 여부 배열
	public static List<int[]> homes; // 집의 위치를 저장하는 리스트
	public static List<int[]> chickens; // 치킨집의 위치를 저장하는 리스트
	public static int[][] distance; // 각 집에서 모든 치킨집까지의 거리를 저장한 이차원 배열
	public static int min; // 최소 치킨 거리 저장하는 변수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		
		int N = Integer.parseInt(input[0]); // 도시의 한 변의 길이 
		int M = Integer.parseInt(input[1]); // 폐업시키지 않을 치킨집의 갯수
				
		closed = new boolean[N + 1][N + 1];
		
		homes = new ArrayList<>();
		
		chickens = new ArrayList<>();

		min = Integer.MAX_VALUE;
		
		// 도시 정보 입력
		for(int i = 1; i <= N; i++) {
			String[] cityLine = br.readLine().split(" ");
			for(int j = 1; j <= N; j++) {
				int info = Integer.parseInt(cityLine[j - 1]);
				if (info == 1) {
					homes.add(new int[]{i,j}); // 집 위치 저장
				} else if (info == 2) {
					chickens.add(new int[]{i, j}); // 치킨집 위치 저장
				}
			}
		}
		
		// 치킨 거리 구하기
		distance = new int[homes.size()][chickens.size()];
		calcAllDistance(); 
		
		// 하나씩 폐업시키며 최솟값 산출
		System.out.println(closeKfc(chickens.size(), 0, M));
		
		br.close();
		
	}
	/**
	 * 치킨집을 폐업시키며 최소 치킨 거리 반환하는 메소드
	 * @param depth : 남은 치킨집의 수
	 * @param start : 다음 탐색할 치킨집
	 * @param M : 폐업시키지 않을 치킨집의 갯수
	 * @return 최소 치킨 거리
	 */
	public static int closeKfc(int depth, int start, int M) {
	    if (depth == M) {
	        return sumAllDistance();
	    }

	    for (int i = start; i < chickens.size(); i++) {
	        int[] xy = chickens.get(i);
	        closed[xy[0]][xy[1]] = true; // 폐업 처리
	        min = Math.min(min, closeKfc(depth - 1, i + 1, M));
	        closed[xy[0]][xy[1]] = false; // 복구
	    }
	    
	    return min;
	}
	
	public static int sumAllDistance() {
		int sum = 0;
		
		for(int i = 0; i < distance.length; i++) {
			int chickenDistance = Integer.MAX_VALUE;
			for(int j = 0; j < distance[i].length; j++) {
				int[] xy = chickens.get(j);
				if(!closed[xy[0]][xy[1]]) {// 닫혀있지 않다면
					chickenDistance = Math.min(chickenDistance, distance[i][j]);					
				}
			}
			sum += chickenDistance;
		}
		return sum;
	}
	
	public static void calcAllDistance() {
		for(int i = 0; i < distance.length; i++) {
			for(int j = 0; j < distance[i].length; j++) {
				int[] x1y1 = homes.get(i);
				int[] x2y2 = chickens.get(j);
				
				distance[i][j] = calcDistance(x1y1[0], x1y1[1], x2y2[0], x2y2[1]); // 각 거리 계산
			}
		}
	}
	
	public static int calcDistance(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}
}
