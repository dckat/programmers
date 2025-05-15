package SSG_coding_test.week4;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class BOJ2608 {
	// 로마 숫자
	private static final char[] ROMAN = {'I', 'V', 'X', 'L', 'C', 'D', 'M'};
    private static final int[] ARABIAN = {1, 5, 10, 50, 100, 500, 1000};
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String roman1 = br.readLine();
        String roman2 = br.readLine();
        
        Map<Character, Integer> rtaMap = new HashMap<>();
        Map<Integer, Character> atrMap = new HashMap<>();
        
        for (int i = 0; i < ROMAN.length; i++) {
        	rtaMap.put(ROMAN[i], ARABIAN[i]);
        	atrMap.put(ARABIAN[i], ROMAN[i]);
        }
    
        int arabian1 = romanToArabian(rtaMap, roman1);
        int arabian2 = romanToArabian(rtaMap, roman2);
        
        int arabianResult = arabian1 + arabian2;
        String romanResult = arabianToRoman(atrMap, arabianResult);
       
        bw.write(arabianResult + "\n" + romanResult);
        bw.flush();
        bw.close();
        br.close();
    }
    
    public static int romanToArabian(Map<Character, Integer> rtaMap, String rom){
        int total = 0;
        // 작은 숫자가 큰 숫자의 왼쪽에 오는 경우만 신경
        for (int i = 0; i < rom.length(); i++){
        	
            int curr = (int) rtaMap.get(rom.charAt(i));
            
            if ( (i < rom.length() - 1) && (curr < (int)rtaMap.get(rom.charAt(i + 1)) ) ){ // 작은 숫자가 큰 숫자의 왼쪽에 오는 경우
                total -= curr; // 총합에서 현재값만큼 뺌
            } else {
            	total += curr;
            }
            	
        }
        return total;
    }
    
    public static String arabianToRoman(Map<Integer, Character> atrMap, int ab){
    	int digit = 0;
    	int number = 1;
    	List<Character> roma = new ArrayList<>();
    	
    	while (ab > 0) {
    		digit = ab % 10;
    		
    		if (digit % 5 == 4) {
    			if (digit == 4) { 
    				roma.add(atrMap.get(5 * number)); // 역순고려
    				roma.add(atrMap.get(number));
    			} else { // 9일 때
    				roma.add(atrMap.get(10 * number));
    				roma.add(atrMap.get(number));
    			}
    		} else if (digit < 5) {
    			for (int i = 0; i < digit; i++) {
    				roma.add(atrMap.get(number));
    			}
    		} else if (digit > 5) {
    			for (int i = 0; i < digit - 5; i++) {
    				roma.add(atrMap.get(number));
    			}
    			roma.add(atrMap.get(5 * number));
    		} else if (digit == 5) {
    			roma.add(atrMap.get(5 * number));
    		}
    		
    		number *= 10;
    		
    		ab = (ab - digit) / 10;
    	}
    	
    	Collections.reverse(roma);
    	
        return roma.stream().map(String::valueOf).collect(Collectors.joining());
    }
}
