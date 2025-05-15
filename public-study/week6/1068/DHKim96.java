package SSG_coding_test.week6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BOJ1068{
	// [BOJ] 1068 : 트리
    
    static List<List<Integer>> tree; // 트리 저장할 인접 리스트
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int N = Integer.parseInt(br.readLine()); // 트리의 노드의 개수
                
        int count  = 0;
        
        initGraph(N); // 트리 생성
        
        String[] input = br.readLine().split(" "); // 각 노드의 부모 노드
        
        int root = -1;
        
        for (int i = 0; i < N; i++){
            int childNode = i;
            int parentNode = Integer.parseInt(input[i]);
            
            if (parentNode == - 1) { // 루트가 꼭 0이 아닐 수 있음을 주의
            	root = i;
            	continue;
            }
            
            tree.get(parentNode).add(childNode);
            // 처음에는 양방향 그래프로 구현했으나
            // 1. 자식 노드에서 부모 노드로 궅이 거슬러 올라갈 필요가 없었으며
            // 2. 양방향일 경우 리프 노드 구별이 어려웠기에
            // ==> 단방향으로 구현
        }
        
        int deleteNum = Integer.parseInt(br.readLine());
        
        if (deleteNum == root) { // 삭제 노드가 루트 노드일 경우 리프 노드는 없으므로 바로 0 리턴
        	bw.write(count + "");
        	bw.flush();
        	bw.close();
        	br.close();
        	return;
        }
        
        deleteNode(deleteNum);
        
        Queue<Integer> q = new LinkedList<>();
                
        q.add(root);
                
        while(!q.isEmpty()) { // 너비 우선 탐색 방식 사용
        	int currNode = q.poll();
        	
    		if (!tree.get(currNode).isEmpty()) { // 리프노드 == 자식 노드가 없음
        		for (int childNode : tree.get(currNode)) {
            		q.add(childNode);
            	}
        	} else {
        		count++;
        	}
        }
        
        bw.write(count + "");
        bw.flush();
        bw.close();
        br.close();
    }
    
    public static void deleteNode(int deleteNode) {
    	// 삭제할 노드가 지닌 모든 자손들을 삭제할 필요 없이 삭제할 노드의 부모 노드에서 해당 노드로의 연결만 끊어주면 됨
    	
    	// tree.remove(deleteNode) 시 오답
    	// ==> 이는 부모 노드에서 해당 노드로 단방향 연결되어 있기 때문에
    	// ==> 해당 노드를 삭제하더라도 부모 노드에서 해당 노드 정보를 가지고 있어서 접근 시도
    	// ==> 따라서 해당 노드의 부모 노드를 찾아 해당 부모 노드의 리스트에서 삭제할 노드를 찾아 삭제해야 함
    	
    	for (List<Integer> parent : tree) {
            parent.removeIf(child -> child == deleteNode); // 부모 노드에서 삭제
        }
    }
    
    
    public static void initGraph(int N){
    	tree = new ArrayList<>();
    	
        for (int i = 0; i < N; i++){
            tree.add(new ArrayList<>());
        }
    }

}