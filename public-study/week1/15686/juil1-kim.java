import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ15686 {
    static int[][] status;      // 도시 상태 저장
    static boolean[] visited;   //
    static int n, m, answer;
    static ArrayList<Point> home;
    static ArrayList<Point> chicken;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        home = new ArrayList<>();
        chicken = new ArrayList<>();
        status = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                status[i][j] = Integer.parseInt(st.nextToken());

                if (status[i][j] == 1) {
                    home.add(new Point(i, j));
                } else if (status[i][j] == 2) {
                    chicken.add(new Point(i, j));
                }
            }
        }

        answer = Integer.MAX_VALUE;
        visited = new boolean[chicken.size()];

        solution(0, 0);
        bw.write(answer + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static void solution(int start, int depth) {
        if (depth == m) {
            int street = 0;
            // 가장 가까운 치킨 거리를
            for (int i = 0; i < home.size(); i++) {
                int temp = Integer.MAX_VALUE;
                for (int j = 0; j < chicken.size(); j++) {
                    if (visited[j]) {
                        int dist = Math.abs(home.get(i).x - chicken.get(j).x) + Math.abs(home.get(i).y - chicken.get(j).y);

                        temp = Math.min(temp, dist);
                    }
                }
                street += temp;
            }
            answer = Math.min(answer, street);
            return;
        }

        for (int i = start; i < chicken.size(); i++) {
            visited[i] = true;
            solution(i + 1, depth + 1);
            visited[i] = false;
        }
    }

    // 도시 좌표 지정
    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
