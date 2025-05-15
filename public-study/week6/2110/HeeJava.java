import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int[] nArr = new int[N];
        for(int i = 0; i < nArr.length; i++) {
            nArr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(nArr);

        int result = 0;
        int left = 0;
        int right = nArr[N-1] - nArr[0];

        while(left <= right) {
            int cnt = 1;
            int before_pos = nArr[0];

            int mid = (left+right)/2;

            for(int i = 1; i < N; i++) {
                if(nArr[i] - before_pos >= mid) {
                    cnt++;
                    before_pos = nArr[i];
                }
            }

            if(cnt >= C) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(result);
        br.close();
    }
}
