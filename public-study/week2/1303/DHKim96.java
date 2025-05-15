package SSG_coding_test.week2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class BOJ1303 {
    /*
        * 전쟁 - 전투
        *   N명이 뭉쳐있을 때(대각선 제외) N^2의 위력
        *   => 뭉쳐있는 인원 파악 ==> dfs
     */
    static char[][] battle; // 전쟁터
    static boolean[][] visited; // 방문 배열
    static int count;
    static int[] bRow = {-1, 1, 0, 0};  // 행의 상 하 좌 우
    static int[] bCol = {0, 0, -1, 1}; // 열의 상하좌우

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");

        int N = Integer.parseInt(input[0]); // 전쟁터 세로
        int M = Integer.parseInt(input[1]); // 전쟁터 가로
        
        battle = new char[M][N];
        visited = new boolean[M][N];

        count = 0;
        
        List<Integer> whitePowerList = new ArrayList<>();
        List<Integer> bluePowerList = new ArrayList<>();
        
        // 전쟁터 입력 받기
        for(int i = 0; i < M; i++) {
            String line = br.readLine();
            for(int j = 0; j < N; j++) {
               battle[i][j] = line.charAt(j);
            }
        }
        
        
        // 단절된 노드들까지 모두 확인하기 위한 반복문(프로그래머스 네트워크 문제 참고)
        for(int i = 0; i < M; i++) {
            for(int j = 0; j < N; j++) {
               if (battle[i][j] == 'W' && !visited[i][j]) {
            	   dfs(i, j, 'W');
            	   whitePowerList.add(count);
            	   count = 0;
               } else if (battle[i][j] == 'B' && !visited[i][j]) {
            	   dfs(i, j, 'B');
            	   bluePowerList.add(count);
            	   count = 0;
               }
            }
        }
        
        int powerOfWhite = 0;
        int powerOfBlue = 0;
        
        for (int num : whitePowerList) {
        	powerOfWhite += num * num;
        }
        
        for (int num : bluePowerList) {
        	powerOfBlue += num * num;
        }
        
        bw.write(powerOfWhite + " " + powerOfBlue);
        bw.flush();
        bw.close();
        br.close();
    }

    public static void dfs(int x, int y, char type) {
    	visited[x][y] = true;
    	count++;
    	
    	// 상하좌우 이동 가능 여부 판별 (프로그래머스 게임맵최단거리 참고)
    	for (int i = 0; i < 4; i++) {
    		int nextRow = x + bRow[i];
    		int nextCol = y + bCol[i];
    		if (canMove(nextRow, nextCol, type)) {
    			dfs(nextRow, nextCol, type);
    		}
    	}
    }

    // 상하좌우 중 하나라도 움직일 수 있는지 체크(프로그래머스 게임맵최단거리 참고)
    public static boolean canMove(int x, int y, char type){        	
    	return x >= 0 && y >= 0 && x < battle.length && y < battle[0].length 
    				&& !visited[x][y]  && battle[x][y] == type;
    	
    }

}
