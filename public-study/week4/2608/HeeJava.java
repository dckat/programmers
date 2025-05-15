import java.io.*;

public class Main {

    // 로마 숫자 -> 아라비아 숫자 변환
    public static int romanToArabic(String roman) {
        int result = 0;
        int prevValue = 0;

        // 로마 숫자에서 각 문자의 값에 해당하는 배열
        int[] romanValues = new int[128];
        romanValues['I'] = 1;
        romanValues['V'] = 5;
        romanValues['X'] = 10;
        romanValues['L'] = 50;
        romanValues['C'] = 100;
        romanValues['D'] = 500;
        romanValues['M'] = 1000;

        // 문자열을 뒤에서부터 읽으며 아라비아 숫자로 변환
        for (int i = roman.length() - 1; i >= 0; i--) {
            int currentValue = romanValues[roman.charAt(i)];
            if (currentValue < prevValue) {
                result -= currentValue;  // 큰 값 뒤에 작은 값이 오면 빼준다 ex) IV, IX
            } else {
                result += currentValue;
            }
            prevValue = currentValue;
        }

        return result;
    }

    // 아라비아 숫자 -> 로마 숫자 변환
    public static String arabicToRoman(int num) {
        StringBuilder roman = new StringBuilder();

        // 로마 숫자 값과 기호 배열
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                num -= values[i];
                roman.append(symbols[i]);
            }
        }

        return roman.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        // 두 로마 숫자 입력 받기
        String roman1 = br.readLine();
        String roman2 = br.readLine();

        // 로마 숫자를 아라비아 숫자로 변환
        int arabic1 = romanToArabic(roman1);
        int arabic2 = romanToArabic(roman2);

        // 두 아라비아 숫자 더하기
        int sum = arabic1 + arabic2;

        // 합을 로마 숫자로 변환
        String resultRoman = arabicToRoman(sum);

        bw.write(sum + "\n" + resultRoman);

        bw.flush();
        bw.close();
        br.close();
    }
}
