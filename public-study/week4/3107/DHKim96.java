package SSG_coding_test.week4;

import java.io.*;

public class BOJ3107 {
    // IPv6 / 14352KB / 108ms
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String origin = br.readLine(); // 원본 IPv6 저장

        if (origin.length() == 39){
            bw.write(origin);
            bw.flush();
            bw.close();
            br.close();
            return;
        }
        
        
        if (origin.contains("::")) { // :: 이 존재하는 경우 origin 에 생략된 그룹의 갯수를 계산해서 콜론을 더해줌
        	StringBuilder ssb = new StringBuilder();
        	
        	String[] strArr1 = origin.split("::", -1);
        	
        	String left = strArr1[0];
        	String right = strArr1[1];
        	
        	int leftColonsCount = countColons(left);
        	int RightColonsCount = countColons(right);
        	
        	int neededColons = 7 - (leftColonsCount + RightColonsCount);
        	
        	ssb.append(left);
        	while (neededColons-- > 0) {
        		ssb.append(":");
        	}
        	ssb.append(right);
        	
        	origin = ssb.toString();
        }
        
             
        String[] sections = origin.split(":", -1); // 문자열이 공백으로 끝날 시 공백 취득하도록 -1
        
        StringBuilder result = new StringBuilder();
        
        fillZero(sections, result);

        bw.write(result.toString());
        bw.flush();
        bw.close();
        br.close();
    }
    
    public static void fillZero(String[] sections, StringBuilder sb) {
      String num = "";
      int length = 0;

      for (int i = 0; i < sections.length; i++){ // 4개가 아닌 경우 부족한 수 만큼 0으로 채움
          num = sections[i];
          length = num.length();
          while (length++ < 4){
              sb.append(0);
          }
          sb.append(num);
          if (i != sections.length - 1) sb.append(":"); // 마지막 그룹에는 : 추가 생략
      }
    }
    
    // 콜론의 개수 반환하는 메소드
    public static int countColons(String str) {
    	return (int)str.chars().filter(c -> c == ':').count();
    }
}
