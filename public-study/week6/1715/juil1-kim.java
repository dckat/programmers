import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BJ1715 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            queue.add(Integer.parseInt(br.readLine()));
        }

        int sum = 0;
        while (queue.size() > 1){ // 큐에 값이 하나 남을때까지 반복
            int tmp = queue.poll() + queue.poll(); // 큐에서 가장 작은 두 값 저장
            sum += tmp;
            queue.add(tmp);
        }
        System.out.println(sum);
    }
}
