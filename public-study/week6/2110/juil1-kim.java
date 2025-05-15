import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ2110 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 집의 개수
        int C = Integer.parseInt(st.nextToken()); // 설치할 공유기 수

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr); // 집 위치 오름차순 정렬

        int low = 1; // 공유기 간 최소 거리의 하한값
        int high = arr[N - 1]; // 공유기 간 최소 거리의 상한값 (집들 간 최대 거리)
        while (low <= high) {
            int dis = (low + high) / 2; // 최소 거리 후보

            int cLoc = 0; // 공유기 설치 위치 (첫번째 집)
            int cnt = 1; // 설치된 공유기 수
            for (int i = 1; i < N; i++) {
                if (arr[i] - arr[cLoc] >= dis) { // 이전에 공유기를 설치한 집과의 거리가 dis 이상인 경우 공유기 설치
                    cLoc = i;
                    cnt++;
                }
            }
            if (cnt < C) {
                high = dis - 1; // 설치된 공유기가 부족 => 최소 거리 줄임.
            }else {
                low = dis + 1; // 공유기 설치 가능 => 최소 거리 늘림.
            }
        }
        System.out.println(low - 1); // low=dis+1 후 while문이 종료되기에 -1
    }
}
