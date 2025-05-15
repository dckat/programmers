import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder sb = new StringBuilder();
        String[] ip = br.readLine().split(":");

        int text = 8;
        for (String s : ip) {
            if (!(s.isEmpty())) text--;
        }

        boolean flag = false;
        for(int i = 0; i < ip.length; i++) {
            if(ip[i].isEmpty() && !flag) {
                for(int j = 0; j < text; j++) {
                    sb.append("0000").append(":");
                }
                flag = true;
                continue;
            }
            if(ip[i].isEmpty()) continue;

            sb.append("0".repeat(4-ip[i].length()));

            if(i == ip.length-1)
                sb.append(ip[i]);
            else
                sb.append(ip[i]).append(":");
        }

        int result = sb.toString().split(":").length;
        if(result < 8) {
            sb.append(":0000".repeat(8-result));
        }

        System.out.println(sb);
        bw.flush();
        bw.close();
        br.close();
    }
}
