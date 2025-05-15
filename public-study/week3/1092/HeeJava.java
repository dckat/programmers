import java.io.*;
import java.util.*;

public class 배 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        List<Integer> crane = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            crane.add(Integer.parseInt(st.nextToken()));
        }

        int M = Integer.parseInt(br.readLine());
        List<Integer> box = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++) {
            box.add(Integer.parseInt(st.nextToken()));
        }

        crane.sort(Collections.reverseOrder());
        box.sort(Collections.reverseOrder());

        if(crane.get(0) < box.get(0)) {
            bw.write(-1 + "");
            bw.flush();
            bw.close();
            br.close();
            return;
        }

        int day = 0;

        while(!box.isEmpty()) {
            int boxIdx = 0, craneIdx = 0;

            while(craneIdx < N) {
                if(boxIdx == box.size()) {
                    break;
                }  else if(box.get(boxIdx) <= crane.get(craneIdx)) {
                    box.remove(boxIdx);
                    craneIdx++;
                } else {
                    boxIdx++;
                }
            }
            day++;
        }

        bw.write(day + "");
        bw.flush();
        bw.close();
        br.close();
    }
}
