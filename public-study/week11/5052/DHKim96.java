import java.io.*;
import java.util.Arrays;

public class Main {
    static class Trie {
        static class Node {
            Node[] children;
            boolean isEnd;

            public Node() {
                this.children = new Node[10]; // 0 ~ 9
                this.isEnd = false; // 단어의 끝 여부
            }
        }

        Node root;

        public Trie() {
            root = new Node();
        }

        boolean insert(String word) {

            Node curr = root;

            for (char ch : word.toCharArray()) {
//                int idx = Integer.parseInt(String.valueOf(ch));
                int idx = ch - '0';

                if (curr.children[idx] == null ){ // 현재 노드의 자식 노드 중 해당 번호가 없으면 새로 생성
                    curr.children[idx] = new Node();
                }

                curr = curr.children[idx]; // 자식 노드로 이동

                if (curr.isEnd) {
                    return false; // 접두어 조건 위반
                }

            }

            curr.isEnd = true; // 끝까지 이동하면 해당 노드에 끝 여부에 true 저장
            return true;
        }
    }



    static String solution(int n, String[] phonebook){

        Arrays.sort(phonebook); // 사전 정렬 시 효과적인 탐색 가능

        Trie trie = new Trie();

        // 전화번호 목록의 일관성 여부
        // => 한 번호가 다른 번호의 접두어인 경우가 없어야 함 => 트라이
        // 911 , 91 121 이 있을 때 911 까지 탐색 후 마지막 1의 노드가 끝 노드가 아닐 시 일관성 x
        for (String phone : phonebook) {
            if (!trie.insert(phone)) {
                return "NO";
            }
        }

        return "YES";
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine()); // 테스트케이스의 수

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < t; i++){
            int n = Integer.parseInt(br.readLine()); // 전화번호의 수

            String[] phonebook = new String[n];

            for (int j = 0; j < n; j++){
                phonebook[j] = br.readLine();
            }

            sb.append(solution(n, phonebook)).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();

    }
}
