package BOJ.dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11049 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[][] matrixs = new int[N+1][2];

        for (int i = 1; i <= N; i++){
            StringTokenizer stk = new StringTokenizer(br.readLine());
            matrixs[i][0] = Integer.parseInt(stk.nextToken());
            matrixs[i][1] = Integer.parseInt(stk.nextToken());
        }

        // 행렬의 크기 => 곱셈에 대한 결합법칙 성립하기 때문에 순서에 따라 결과가 달라지지 않음 즉, 순서에 따라 크기 변하지 않음
        // 그러나 연산횟수는 달라짐

        
        /*
        A ~ D 
        1. ((AB)C)D || (A(BC))D : ABC 의 최소 + A.row * C.col * D.col
        2. (AB)(CD) : AB 행렬 연산 횟수 + CD 행렬 연산 횟수 + A.row * B.col * D.col
        3. A((BC)D) || A(B(CD)) : BCD 의 최소 + A.row * C.col * D.col
        
        A ~ C
        1. (AB)C : AB 행렬 연산 횟수 + A.row * B.col * c.col(AB행렬과 C 행렬의 곱셈 연산 횟수)
        2. A(BC) : BC 행렬 연산 횟수 + A.row * A.col * c.col
        
        => 점화식 
        dp[i][j] == i번째 행렬 ~ j번째 행렬 까지의 행렬 곱셈 최소 연산 횟수
        = dp[i][k] + dp[k+1][j] + matrix[i][0] * matrix[k][1] * matrix[j][1]
        */
        
        int[][] dp = new int[N+1][N+1]; // 1 ~ N까지
        
        
        for (int range = 1; range < N; range++) { // 햏렬 범위 크기
        	for (int start = 1; start + range <= N; start++) {
        		// i : 시작 행렬
        		int end = start + range; // 끝 행렬
        		dp[start][end] = Integer.MAX_VALUE;
        		for (int k = start; k < end; k++) { // k : 분할 지점
        			dp[start][end] = Math.min(
        										dp[start][end], 
        										dp[start][k] + dp[k+1][end] + matrixs[start][0] * matrixs[k][1] * matrixs[end][1]
        									 );
        			
        			
        		}
        		
        	}
        }
        
        System.out.println(dp[1][N]);


        br.close();
    }
}
