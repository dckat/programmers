import java.io.*;
import java.util.*;

public class 행복유치원 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        List<Integer> tall = new ArrayList<>();
        List<Integer> subTall = new ArrayList<>();

        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++) {
            tall.add(Integer.parseInt(st.nextToken()));
        }
        for(int i = 0; i < N-1; i++) {
            subTall.add(tall.get(i+1)-tall.get(i));
        }

        int answer = subTall.stream().sorted(Collections.reverseOrder()).skip(K-1).mapToInt(x -> x).sum();

        bw.write(answer + "");
        bw.flush();
        bw.close();
        br.close();
    }
}
