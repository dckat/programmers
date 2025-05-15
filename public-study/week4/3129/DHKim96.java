package SSG_coding_test.week4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class BOJ3129 {
	// 백준 3129번 상범이의 은밀한 메세지
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// 입력 받기
		String encryptedMessage = br.readLine();
		String partialDecryptedMessage = br.readLine();

		// 키 추출
		String key = extractKey(encryptedMessage, partialDecryptedMessage);

		// 원래 메시지 복호화
		String originalMessage = decryptMessage(encryptedMessage, key);

		bw.write(originalMessage);
		bw.flush();
		bw.close();
		br.close();
	}

	private static String extractKey(String encryptedMessage, String partialDecryptedMessage) {
		int encryptedMessageLen = encryptedMessage.length();
		int partialDecryptedMessageLen = partialDecryptedMessage.length();

		for (int i = 0; i < encryptedMessageLen - partialDecryptedMessageLen + 1; i++) {
			String subEncryptedMessage = encryptedMessage.substring(i, partialDecryptedMessageLen + i);

			String candidateKey = checkCandidate(i, subEncryptedMessage, partialDecryptedMessage);

			if (!candidateKey.isEmpty() && verifyKey(i, candidateKey, subEncryptedMessage, partialDecryptedMessage)) {
				return candidateKey;
			}
		}

		return "";
	}

	private static String checkCandidate(int startIdx, String subEncryptedMessage, String partialDecrypted) {
		StringBuilder keyPatternSb = new StringBuilder();

		// partialDecrypted와 비교하여 키 패턴을 생성
		for (int i = 0; i < partialDecrypted.length(); i++) {
			char curr = calcForDecrypt(subEncryptedMessage.charAt(i), partialDecrypted.charAt(i));
			keyPatternSb.append(curr);
		}

		String keyPattern = keyPatternSb.toString();

		// 키 패턴에서 주기성을 확인
		for (int period = 1; period <= keyPattern.length() / 2; period++) {
			String temp1 = keyPattern.substring(0, period);
			boolean isRepeated = true;

			// 주어진 period 길이만큼 반복되는지 확인
			for (int i = period; i <= keyPattern.length() - period; i += period) {
				String temp2 = keyPattern.substring(i, i + period);
				if (!temp1.equals(temp2)) {
					isRepeated = false;
					break;
				}
			}

			// 반복 패턴이 발견된 경우, 해당 주기로 키를 반환
			if (isRepeated) {
				StringBuilder finalKeyPattern = new StringBuilder(temp1);

				// 키를 주기에 맞게 재정렬
				int keyLen = finalKeyPattern.length();
				char[] chArr = new char[keyLen];
				int shift = keyLen - (startIdx % keyLen);

				for (int idx = 0; idx < keyLen; idx++) {
					chArr[idx] = finalKeyPattern.charAt((shift + idx) % keyLen);
				}

				return new String(chArr);
			}
		}

		return "";
	}

	private static boolean verifyKey(int start, String candidateKey, String subEncryptedMessage, String partialDecryptedMessage) {
		int keyLen = candidateKey.length();

		for (int idx = 0; idx < partialDecryptedMessage.length(); idx++) {
			if (calcForEncrypt(partialDecryptedMessage.charAt(idx), candidateKey.charAt((start + idx) % keyLen)) != subEncryptedMessage.charAt(idx)) {
				return false;
			}
		}
		return true;
	}

	private static String decryptMessage(String encryptedMessage, String key) {
		int ecnLen = encryptedMessage.length();
		int keyLen = key.length();

		char[] result = new char[ecnLen];

		for (int i = 0; i < ecnLen; i++) {
			result[i] = decrypt(encryptedMessage.charAt(i), key.charAt(i % keyLen));
		}

		return new String(result);
	}

	private static char decrypt(char a, char keyChar) {
		return (char) (((a - keyChar + 26) % 26) + 'a');
	}

	private static char calcForDecrypt(char a, char b) {
		return (char) (((a - b + 26) % 26) + 'a');
	}

	private static char calcForEncrypt(char a, char keyChar) {
		return (char) ((((a - 'a') + (keyChar - 'a')) % 26) + 'a');
	}
}
