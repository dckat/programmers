import java.io.*;
import java.util.PriorityQueue;
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Long> pq = new PriorityQueue<Long>();
		for(int i = 0; i < N; i++) {
			pq.add(Long.parseLong(br.readLine()));
		}
			long num = 0;
			while(pq.size() > 1)  {
				long temp1 = pq.poll();
				long temp2 = pq.poll();
				
				num += temp1 + temp2;
				pq.add(temp1+temp2);
			}
			System.out.println(num);
	}
}
