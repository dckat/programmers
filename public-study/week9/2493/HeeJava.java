import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class 탑 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine()); // 탑의 갯수
        StringTokenizer st = new StringTokenizer(br.readLine());

        Stack<int[]> stack = new Stack<>();
        for(int i = 1; i <= N; i++) {
            int top = Integer.parseInt(st.nextToken());
            while(!stack.isEmpty()) {
                if(stack.peek()[1] >= top) {
                    sb.append(stack.peek()[0]).append(" ");
                    break;
                } else {
                    stack.pop();
                }
            }

            if(stack.isEmpty()) {
                sb.append(0).append(" ");
            }
            stack.push(new int[]{i, top});
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}