package SSG_coding_test.week3;

import java.io.*;
import java.util.Arrays;

public class BOJ1092 {
    public static void main(String[] args) throws IOException {
        // 배
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine()); // 크레인의 개수
        String[] input = br.readLine().split(" ");
        int[] cranes = new int[N]; // 크레인의 무게 제한 담은 배열
        for (int i = 0; i < N; i++) {
            cranes[i] = Integer.parseInt(input[i]);
        }

        int M = Integer.parseInt(br.readLine()); // 박스의 개수
        input = br.readLine().split(" ");
        int[] boxes = new int[M]; // 박스의 무게 담은 배열
        for (int i = 0; i < M; i++) {
            boxes[i] = Integer.parseInt(input[i]);
        }

        int answer = solution(cranes, boxes);

        bw.write(answer + "");
        bw.flush();
        bw.close();
        br.close();
    }


    public static int solution(int[] cranes, int[] boxes){
        int N = cranes.length;
        int M = boxes.length;

        // 크레인 배열과 박스 배열 정렬
        Arrays.sort(cranes);
        Arrays.sort(boxes);

        // 박스의 최대 무게가 크레인이 실을 수 있는 무게보다 크면 옮길 수 없음
        if (cranes[N - 1] < boxes[M - 1]) {
            return -1;
        }

        int movedBoxes = 0;
        int time = 0;
        boolean[] moved = new boolean[M]; // 적재 여부

        // 가장 큰 크레인부터 가장 큰 무게들을 싣게 되면 그게 곧 최적해
        while (movedBoxes < M) {
            int boxIdx = M - 1;

            for (int i = N - 1; i >= 0 ; i--){
                while(boxIdx >= 0){
                    if (!moved[boxIdx] && cranes[i] >= boxes[boxIdx]){
                        movedBoxes++;
                        moved[boxIdx] = true;
                        break;
                    } else boxIdx--;
                }
            }
            time++;
        }

        return time;
    }

}
