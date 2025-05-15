import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ2493 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        Stack<int[]> stack = new Stack<>();
        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) { // 탑은 0번부터가 아닌 1번부터 시작
            int top = Integer.parseInt(st.nextToken()); // 현재 확인중인 탑
            while (!stack.isEmpty()) {
                if (stack.peek()[1] >= top) { // 현재 탑보다 높은 이전 탑 발견
                    bw.write(stack.peek()[0] + " "); // 해당 탑 번호 출력
                    break;
                }
                stack.pop(); // 현재 탑보다 낮은 높이의 이전 탑 제거
            }
            if (stack.isEmpty()) { // 신호 받는 탑이 없는 경우
                bw.write("0 ");
            }
            stack.push(new int[]{i, top}); // 현재 탐색중인 탑 추가
        }
        bw.flush(); // 출력
        bw.close();
        br.close();
    }
}