import org.w3c.dom.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    static class TrieNode {
        Map<Character, TrieNode> childNode = new HashMap<>();
        boolean isEnd;

        public TrieNode() {}

        public void insert(String word) {
            TrieNode trieNode = this;
            for(int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);

                // child node 에 c 가 없을 경우 추가한다.
                // putIfAbsent 는 Key 있을 경우 Value 반환, Key 없을 경우 Key, Value 를 Map 에 저장
                trieNode.childNode.putIfAbsent(c, new TrieNode());

                // 자식 trieNode 에 값을 가져온다.
                trieNode = trieNode.childNode.get(c);

                if(i == word.length() - 1) {
                    // 문자의 끝이면 여기가 끝이라고 표시한다. (색칠)
                    trieNode.isEnd = true;
                    return;
                }
            }
        }

        public boolean contains(String word) {
            TrieNode trieNode = this;
            for(int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);

                TrieNode node = trieNode.childNode.get(c);
                if(node == null) { // 자식이 없을 경우
                    return false;
                }

                // 찾은 노드로 내려간다.
                trieNode = node;
            }

            // 찾는 단어로 끝났던 단어가 있는 경우(색칠된 경우)
            if(trieNode.isEnd) {
                // 자기 자신의 단어일 경우
                if(trieNode.childNode.isEmpty()) {
                    return false;
                }
            }
            return true;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC = Integer.parseInt(br.readLine());

        while (TC-- > 0) {
            TrieNode trieNode = new TrieNode();
            boolean isEnd = true;
            int n = Integer.parseInt(br.readLine());

            List<String> tels = new ArrayList<>();
            for(int i = 0; i < n; i++) {
                String tel = br.readLine();
                trieNode.insert(tel);
                tels.add(tel);
            }

            for(String key : tels) {
                if(trieNode.contains(key)) {
                    isEnd = false;
                    break;
                }
            }

            System.out.println(isEnd ? "YES" : "NO");
        }
    }
}