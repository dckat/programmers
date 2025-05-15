import java.io.*;

public class Main {
    static String S,T;
    static int sLength;
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        S = br.readLine();
        T = br.readLine();
        sLength = S.length();

        dfs(T);
        System.out.println(answer);

        bw.flush();
        bw.close();
        br.close();
    }

    public static void dfs(String t) {
        if(t.length() == sLength) {
            if (t.equals(S)) {
                answer = 1;
            }
        }

        if(t.endsWith("A")) {
            dfs(t.substring(0, t.length()-1));
        }

        if(t.startsWith("B")) {
            dfs(new StringBuilder(t.substring(1)).reverse().toString());
        }
    }
}
