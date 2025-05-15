package SSG_coding_test.week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ1715 {
	 public static void main(String[] args) throws IOException {
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        
	        int N = Integer.parseInt(br.readLine());
	        
	        PriorityQueue<Integer> queue = new PriorityQueue<>(); // 낮은 숫자가 우선
	        
	        for (int i = 0; i < N; i++){
	            queue.add(Integer.parseInt(br.readLine()));
	        }
	                
	        int theOnePair = 0;
	        int anotherPair = 0;
	        
	        // N == 1 경우 카드 묶음을 비교할 필요가 없으므로 0이 나오도록 해줘야 함  
	        // 최악의 경우 나올 수 있는 경우의 수는 약 16억 6천만번 즉, int 범위 내임
	        int result = 0;
	        
	        while(queue.size() >= 2){
	            theOnePair = queue.poll();
	            anotherPair = queue.poll();
	            
	            int sum = theOnePair + anotherPair;
	            
	            queue.add(sum);
	            
	            result += sum;
	        }
	        
	        System.out.print(result);
	        
	        br.close();
	    }
}
