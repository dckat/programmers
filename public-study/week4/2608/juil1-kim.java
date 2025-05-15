package ssgStudy.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ2608 {
    /*
    * 로마 숫자
    * 문자열
    */

    // 로마 숫자 -> 정수
    public static int romeToInt(String[] rome){
        HashMap<String, Integer> hm = new HashMap<>();
        {
            hm.put("I", 1);
            hm.put("V", 5);
            hm.put("X", 10);
            hm.put("L", 50);
            hm.put("C", 100);
            hm.put("D", 500);
            hm.put("M", 1000);
            hm.put("IV", 4);
            hm.put("IX", 9);
            hm.put("XL", 40);
            hm.put("XC", 90);
            hm.put("CD", 400);
            hm.put("CM", 900);
        }

        if(rome.length == 1){ // 입력된 로마 숫자가 한 글자면 그 값을 바로 반환
            return hm.get(rome[0]);
        }

        int result = 0; // 중간 계산 결과 저장

        for (int i = 0; i < rome.length; i++) {
            if (i < rome.length - 1 && hm.get(rome[i]) < hm.get(rome[i + 1])) { // 현재 인덱스가 마지막 배열인지 확인 (뒤에 비교할 문자가 없기 때문에 따로 처리해야 함)
                result += hm.get(rome[i] + "" + rome[i + 1]); // 역방향이면 두 글자 합쳐서 처리
                i++; // 두 글자를 한 번에 처리했으니 인덱스 증가
            } else {
                result += hm.get(rome[i]); // 순방향이면 그대로 처리
            }
        }
        return result;
    }

    // 정수 -> 로마숫자
    public static String intToRome(int num){
        StringBuilder result = new StringBuilder();

        String[] rome = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

        int i = 0;
        while(num > 0){
            while(num >= values[i]){ // 주어진 값이 num보다 작거나 같을 때까지 반복 -> 큰 값부터 처리 가능
                num-=values[i];
                result.append(rome[i]);
            }
            i++;
        }
        return result.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] r1 = br.readLine().split("");
        String[] r2 = br.readLine().split("");

        int sum = romeToInt(r1) + romeToInt(r2);
        String result = intToRome(sum);

        System.out.println(sum);
        System.out.println(result);
    }
}
