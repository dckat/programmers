package SSG_coding_test.week6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2110 {
	/*
	 * [BOJ]2110 : 공유기 설치
	 * 
	 * 이분 탐색
	 */
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 하나 "이상"의 공백을 사이에 두고 주어짐
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(stk.nextToken()); // 집의 개수
		int C = Integer.parseInt(stk.nextToken()); // 공유기의 개수

		// 집의 좌표 x를 담을 정수형(x <= 10억) 배열 생성
		int[] xArr = new int[N];
		
		// 좌표 입력 받기
		for(int i = 0; i < N; i++) {
			xArr[i] = Integer.parseInt(br.readLine()); 
		}
		
		// 이분 탐색 을 위해 좌표 오름차순 정렬하기
		Arrays.sort(xArr);

		// 최대 거리 : 가장 가까운 집에 설치, 가장 먼 집에 설치 시 거리
		int right = xArr[N - 1] - xArr[0];
		// 최소 거리 : 바로 인접한 집에 설치 시
		int left = 1;
		
		int answer = 0; // 결과값 담을 변수


		while(left <= right) {
			int mid = (right + left) / 2;
			int installedCount = 0; // 공유기 설치 횟수

			int currRouter = xArr[0];
			installedCount++;

			// mid 거리만큼 N번 설치 가능?
			for (int i = 1; i < N; i++) {
				if (xArr[i] >= (currRouter + mid)){
					installedCount++;
					currRouter = xArr[i];
				}
				if (installedCount == C) {
					break;
				}
			}

			if (installedCount != C){ // 공유기 설치 실패
				// mid 값을 줄여
				right = mid - 1;
			} else {
				// 공유기 설치 성공했으면 mid 값 늘려
				answer = mid;
				left = mid + 1;
			}
		}

		System.out.println(answer);
		br.close();
	}


}
