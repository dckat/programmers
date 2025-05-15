import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static boolean check[];
    static ArrayList<Integer>[] tree;
    static int result, delNode;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine()); // 노드 갯수
        check = new boolean[N];
        tree = new ArrayList[N];

        for(int i = 0; i < N; i++) {
            tree[i] = new ArrayList<>();
        }

        int root = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            int head = Integer.parseInt(st.nextToken());
            if(head == -1) root = i;
            else {
                tree[head].add(i);
            }
        }
        delNode = Integer.parseInt(br.readLine());

        dfs(root);

        bw.write(result +"");
        bw.flush();
        bw.close();
        br.close();
    }

    public static void dfs(int idx) {
        int depth = 0;
        if(idx == delNode) return;

        for(int x : tree[idx]) {
            if(!(x == delNode)) {
                dfs(x);
                depth++;
            }
        }

        if(depth == 0) {
            result++;
        }
    }
}
