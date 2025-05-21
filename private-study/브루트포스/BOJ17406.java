import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int[][] arr = null;      // 초기 2차원 배열
    private static int[][] rot = null;   // 회전 연산 정보
    private static int n, m, k;             // 배열크기. 회전연산 횟수
    private static int answer = 5001;

    private static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int[n+1][m+1];
        rot = new int[k][3];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                rot[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static void rotate(int[][] copyArr, int r1, int c1, int r2, int c2) {
        int tmp = copyArr[r1][c1];

        // 좌측열을 위로 한 칸 이동
        for (int i = r1 + 1; i <= r2; i++) {
            copyArr[i - 1][c1] = copyArr[i][c1];
        }
        // 하단행을 왼쪽으로 한 칸 이동
        for (int j = c1 + 1; j <= c2; j++) {
            copyArr[r2][j - 1] = copyArr[r2][j];
        }
        // 우측열을 아래로 한 칸 이동
        for (int i = r2 - 1; i >= r1; i--) {
            copyArr[i + 1][c2] = copyArr[i][c2];
        }
        // 상단 행을 오른쪽으로 한 칸 이동
        for (int j = c2 - 1; j >= c1; j--) {
            copyArr[r1][j + 1] = copyArr[r1][j];
        }
        copyArr[r1][c1 + 1] = tmp;
    }

    private static int[][] go(int[] order) {
        int[][] copyArr = new int[n+1][m+1];

        for (int i = 1; i <= n; i++) {
            System.arraycopy(arr[i], 1, copyArr[i], 1, m);
        }

        // 순서에 따라 배열을 회전
        for (int i = 0; i < order.length; i++) {
            int cur = order[i];

            int R = rot[cur][0];
            int C = rot[cur][1];
            int S = rot[cur][2];

            for (int L = 1; L <= S; L++) {
                rotate(copyArr, R-L, C-L, R+L, C+L);
            }
        }
        return copyArr;
    }

    // 배열 값 찾기
    private static int calc(int[][] a) {
        int result = 5001;

        // 가장 합이 작은 행의 값을 찾기
        for (int i = 1; i <= n; i++) {
            int sum = 0;

            for (int j = 1; j <= m; j++) {
                sum += a[i][j];
            }

            if (sum < result) {
                result = sum;
            }
        }
        return result;
    }

    private static void permutation(int[] order, boolean[] used,  int depth) {
        // k개의 순서가 모두 정해진 경우
        if (depth == k) {
            // 회전한 배열의 결과 저장 후 배열의 값 확인
            int[][] copyArr = go(order);
            int result = calc(copyArr);

            // 최솟값 갱신
            if (answer > result) {
                answer = result;
            }
            return;
        }

        for (int i = 0; i < k; i++) {
            if (!used[i]) {
                used[i] = true;
                order[depth] = i;
                permutation(order, used, depth + 1);
                used[i] = false;
            }
        }
    }

    private static void solution() {
        // 순열을 사용하기 위해 사용할 변수 (순서. 사용여부)
        int[] order = new int[k];
        boolean[] used = new boolean[k];
        permutation(order, used, 0);
    }

    public static void main(String[] args) throws Exception {
        input();
        solution();
        System.out.println(answer);
    }
}