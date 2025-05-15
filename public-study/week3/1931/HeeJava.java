package org.example.Solved;

import java.io.*;
import java.util.*;

public class 회의실배정 {
    public static void main(String[] args) throws IOException{
        /*
        입력받는 값을 오름차순으로 정렬 했는가?
        두 요소 모두 정렬을 했는가?
        동일한 값도 처리 했는가?
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[][] office = new int[N][2];

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            office[i][0] = Integer.parseInt(st.nextToken());
            office[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(office, (o1, o2) -> {
            if (o1[1] == o2[1])
                return o1[0] - o2[0]; // 4 4    2 4
            else
                return o1[1] - o2[1];

        });

        int time = 1;
        int index = 0;
        for (int i = 1; i < office.length; i++) {
            if(office[index][1] <= office[i][0]) {
                time++;
                index = i;
            }
        }

        bw.write(time + "");
        bw.flush();
        bw.close();
        br.close();
    }
}
