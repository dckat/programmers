package SSG_coding_test.week10;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class BOJ4386 {
    /*
    [BOJ]4386 : 별자리 만들기
    */

    static float solution(int n, float[][] stars) {

        float answer = 0;

        List<List<float[]>> graph = new ArrayList<>();

        for (int i = 0; i <= n; i++){
            graph.add(new ArrayList<>());
        }

        boolean[] visited = new boolean[n];

        for (int i = 0; i < n - 1; i++){
            float currX = stars[i][0];
            float currY = stars[i][1];
            for (int j = i + 1; j < n; j++){
                float nextX = stars[j][0];
                float nextY = stars[j][1];
                float weight = (float) Math.sqrt(Math.pow(currX - nextX, 2) + Math.pow(currY - nextY, 2));
                graph.get(i).add(new float[]{j, weight});
                graph.get(j).add(new float[]{i, weight});
            }
        }

        PriorityQueue<float[]> pq = new PriorityQueue<>(Comparator.comparingDouble(o -> o[1]));

        pq.offer(new float[]{0, 0});

        while(!pq.isEmpty()) {
            float[] curr = pq.poll();

            if (visited[(int) curr[0]]) continue;

            visited[(int) curr[0]] = true;

            answer += curr[1];

            for (float[] neighbor : graph.get((int) curr[0])) {
                if (!visited[(int) neighbor[0]]) {
                    pq.offer(new float[]{neighbor[0], neighbor[1]});
                }
            }

        }

        return (float) (Math.round(answer*100)/100.0);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine()); // 별의 개수

        float[][] stars = new float[n][2];

        for (int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            stars[i][0] = Float.parseFloat(st.nextToken());
            stars[i][1] = Float.parseFloat(st.nextToken());
        }

        System.out.print(solution(n, stars));

        br.close();
    }
}