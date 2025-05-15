import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] sequence = new int[N+1];
        for(int i = 0; i < N; i++)
            sequence[i] = Integer.parseInt(st.nextToken());

        int sum = 0, left = 0, right = 0;
        int min = Integer.MAX_VALUE;

        while(left <= N && right <= N) {
            if(sum >= S) {
                sum -= sequence[left++];
                int calc = right - left;
                if(calc < min) min = calc;
            } else {
                sum += sequence[right++];
            }
        }

        System.out.println(min == Integer.MAX_VALUE ? "0" : min+1);
    }
}
