package SSG_coding_test.week1;

import java.io.*;

public class BOJ12919{
    /*
        [BOJ] A와 B 2
        => 주어진 조건을 활용해서 S를 T로 만들 수 있는지 없는지 알아내는 프로그램
            ==> 재귀적 브루트포스
        => S에서 문자열을 하나씩 늘려가며 T가 되는지 확인하면 지수 시간의 시간복잡도(2^n) 소요 따라서 시간초과 발생
            ==> 매번 두 가지의 선택지가 존재하며 가능한 모든 경우의 수 탐색해야하기 때문
        => T에서 문자열을 하나씩 줄여가면 상수 시간의 시간 복잡도(O(n)) 소요
            ==> 문자열의 길이가 n이라면 길이가 1씩 줄어들며 총 n번의 재귀 호출
     */
    static String S, T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // S 입력 받기
        S = br.readLine(); // 초기 문자열
        T = br.readLine(); // 목표 문자열

        // T에서 S로 변형 가능한지 확인
        System.out.print(canTransform(T) ? 1 : 0);

        br.close();
    }

    // 문자열 T에서 S로 변형 가능한지 재귀적으로 확인하는 함수
    public static boolean canTransform(String t) {
        // T의 길이가 S의 길이와 같아졌을 때 재귀 탈출
        if (t.length() == S.length()) {
            return t.equals(S);
        }

        // 마지막 문자가 'A'인 경우: 'A'를 제거하고 계속 진행
        if (t.charAt(t.length() - 1) == 'A') {
            if (canTransform(t.substring(0, t.length() - 1))) return true;
        }

        // 첫 번째 문자가 'B'인 경우: 문자열을 뒤집고 'B'를 제거한 후 계속 진행
        if (t.charAt(0) == 'B') {
            String reversed = new StringBuilder(t).reverse().toString();
            if (canTransform(reversed.substring(0, reversed.length() - 1))) return true;
        }

        // 변환 불가한 경우 false 반환
        return false;
    }
}
