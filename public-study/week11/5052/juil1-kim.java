import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ5052 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        // 테이스 케이스 처리
        for (int tc=1; tc<=t; tc++) {
            int n = Integer.parseInt(br.readLine());

            String[] arr = new String[n];
            HashSet<String> set = new HashSet<>();
            boolean flag = true;

            // 전화번호 입력 및 저장
            for (int i=0; i<n; i++) {
                String s = br.readLine();
                arr[i] = s;
                set.add(s);
            }

            // 각 전화번호의 접두어 확인
            for (int i=0; i<n; i++) {
                for (int j=0; j<arr[i].length(); j++) {
                    if (set.contains(arr[i].substring(0, j))) {
                        flag = false;
                        break;
                    }
                }
            }

            if (flag == true) System.out.println("YES");
            else System.out.println("NO");
        }
    }
}