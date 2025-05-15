package SSG_coding_test.week3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1931 {
	  public static void main(String[] args) throws IOException{
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	       
	        // N 입력 받기
	        int N = Integer.parseInt(br.readLine());
	        
	        int[][] table = new int[N][2]; // 시간표
	        
	        for(int i = 0; i < N; i++) {
	    		StringTokenizer st = new StringTokenizer(br.readLine()); // => split 사용시 556ms
	    		// 시작 시간
	    		table[i][0] = Integer.parseInt(st.nextToken());
	    		// 종료 시간
	    		table[i][1] = Integer.parseInt(st.nextToken());
	    	}
	        
	        // 종료 시간 오름차순으로 정렬
	        Arrays.sort(table, (o1, o2) -> {
	        	if (o1[1] == o2[1]) return o1[0] - o2[0];
	        	else return o1[1] - o2[1];
	        });
	        
	        // 탐색
	        int count = 0; // 회의의 개수
	        int endTime = 0; // 현 회의의 종료 시간
	        
	        for (int i = 0; i < N; i++){
	            // 다음 회의의 시작 시간이 현 회의의 종료 시간 이후라면 사용 가능
	            if (table[i][0] >= endTime){
	                count++; // 회의 개수 증가
	                endTime = table[i][1]; // 종료 시간 갱신
	            }
	        }
	       
	        bw.write(count + "");
	        bw.flush();
	        bw.close();
	        br.close();
	    }
}
