import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ17135 {

    private static int[][] arr = null;
    private static int n, m, d;

    private static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        arr = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static int calc(int l1, int l2, int l3) {
        int result = 0;
        int[][] copyArr = new int[n][m];

        // 임시 배열 복사
        for (int i = 0; i < n; i++) {
            System.arraycopy(arr[i], 0, copyArr[i], 0, m);
        }

        // 적이 모두 없어질때까지 게임 반복
        while (true) {
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    cnt += copyArr[i][j];
                }
            }

            // 적이 없는 경우 턴 종료
            if (cnt == 0) {
                break;
            }

            // 궁수가 제거한 적의 정보를 담은 배열 초기화
            int[][] deleted = new int[3][2];
            for (int i = 0; i < 3; i++) {
                deleted[i][0] = deleted[i][1] = -1;
            }

            int[] archer = {l1, l2, l3};

            // 각 궁수별로 공격 가능한 적을 탐색
            for (int k = 0; k < 3; k++) {
                // 궁수는 N번째 행에 존재한다고 가정
                int rx = n;
                int ry = archer[k];

                int x, y, dist;
                x = y = dist = -1;

                // 맨 왼쪽부터 거리 이내에 있는 적 탐색
                for (int j = 0; j < m; j++) {
                    for (int i = 0; i < n; i++) {
                        // 적을 발견한 경우 거리를 계산
                        if (copyArr[i][j] != 0) {
                            int dx = Math.abs(rx - i);
                            int dy = Math.abs(ry - j);
                            int dd = dx + dy;

                            // 공격이 가능한 적인 경우
                            if (dd <= d) {
                                if (dist == -1 || dist > dd) {
                                    x = i;
                                    y = j;
                                    dist = dd;
                                }
                            }
                        }
                    }
                }

                // 각 궁수별로 공격이 가능한 적의 좌표 정보 저장
                deleted[k][0] = x;
                deleted[k][1] = y;
            }

            // 공격 진행
            for (int i = 0; i < 3; i++) {
                int x = deleted[i][0];
                int y = deleted[i][1];

                // 공격할 대상이 없는 경우
                if (x == -1) {
                    continue;
                }

                if (copyArr[x][y] != 0) {
                    result++;
                }

                // 같은 적을 공격하는 경우가 있으므로 0으로 변경
                copyArr[x][y] = 0;
            }

            // 적을 아래로 한칸씩 이동
            for (int i = n-1; i >= 0; i--) {
                for (int j = 0; j < m; j++) {
                    if (i == 0) {
                        copyArr[i][j] = 0;
                    }
                    else {
                        copyArr[i][j] = copyArr[i-1][j];
                    }
                }
            }
        }

        return result;
    }

    private static int solution() {
        int answer = -1;

        // 궁수를 m칸 중 3칸에 배치
        for (int l1 = 0; l1 < m; l1++) {
            for (int l2 = l1+1; l2 < m; l2++) {
                for (int l3 = l2+1; l3 < m; l3++) {
                    // 최댓값을 찾아 갱신
                    int result = calc(l1, l2, l3);

                    if (result > answer) {
                        answer = result;
                    }
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) throws Exception {
        input();
        int answer = solution();
        System.out.println(answer);
    }
}