import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Number {
    private int x;
    private int y;
    private int step;
    private String number;

    Number(int x, int y, int step, String number) {
        this.x = x;
        this.y = y;
        this.step = step;
        this.number = number;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getStep() {
        return step;
    }

    public String getNumber() {
        return number;
    }
}

public class BOJ2210 {

    private static int[][] a = new int[5][5];
    private static Set<String> s = new HashSet<>();
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    private static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        for(int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                a[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static boolean isBound(int x, int y) {
        if (0 <= x && x < 5 && 0 <= y && y < 5) {
            return true;
        }
        return false;
    }

    private static void bfs(int i, int j) {
        Queue<Number> q = new LinkedList<>();
        q.add(new Number(i, j, 1, String.valueOf(a[i][j])));

        while (!q.isEmpty()) {
            Number num = q.poll();

            if (num.getStep() == 6) {
                s.add(num.getNumber());
                continue;
            }

            for (int k = 0; k < 4; k++) {
                int nx = num.getX() + dx[k];
                int ny = num.getY() + dy[k];

                if (isBound(nx, ny)) {
                    String newNumber = num.getNumber() + a[nx][ny];
                    int nextStep = num.getStep() + 1;
                    q.add(new Number(nx, ny, nextStep, newNumber));
                }
            }
        }
    }

    private static void solution() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                bfs(i, j);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        solution();
        System.out.println(s.size());
    }
}