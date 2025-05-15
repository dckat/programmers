package ssgStudy.week4;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BJ3107 {
    /*
     * IPv6
     * 문자열
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String list[] = str.split(":");
        List<String> conList = new ArrayList<>(); // 변환된 IPv6 주소를 그룹별로 저장할 리스트
        List<String> finList = new ArrayList<>(); // 최종적으로 변환된 IPv6의 주소를 저장
        int group = 0;   // IPv6 그룹의 개수

        for (String s : list) {
            int len = s.length();
            if (len > 0) { // 빈 문자열이 아닐 시
                group++;
                if (len < 4) { // 그룹의 길이가 4자리가 되지 않을 시
                    while (len < 4) {
                        s = "0" + s; // 앞에 0을 붙여줌
                        len++;
                    }
                }
            } else s = "zero"; // 빈 문자열 -> '::'인 부분을 "zero"로 표시
            conList.add(s);
        }

        boolean flag = false;
        for (String s : conList) {
            if (s.equals("zero")) { // "zero" 문자열을 만나면
                if (flag) continue; // 이미 "zero"를 만나 처리했을 경우 넘어감
                int empList = 8 - group;
                while (empList > 0) { // 빈 그룹에 0000 추가
                    finList.add("0000");
                    empList--;
                }
                flag = true;
            } else finList.add(s);
        }

        //마지막이 ::일때 못잡기 때문
        while (group < 8) {
            finList.add("0000");
            group++;
        }

        // 출력
        for (int i = 0; i < 8; i++) {
            System.out.print(finList.get(i));
            if (i < 7) System.out.print(":");
        }
        br.close();
    }
}
