import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;
import java.util.Arrays;


public class Main{
    /*
       [BOJ] 1197 : 최소 스패닝 트리
       정점의 개수 최대 1만
       간선의 개수 최대 10만 
    */
	
	static int[] parent;
	static int[] rank;
	
	static int find(int x) {
		if (parent[x] == x) return x;
		return parent[x] = find(parent[x]);
	}
	
	static void union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x != y) {
			if (rank[x] > rank[y]) {
				parent[y] = x;
			} else if (rank[x] < rank[y]) {
				parent[x] = y;
			} else {
				parent[y] = x;
				rank[x]++;
			}
		}
	}
	
	static boolean isConnected(int x, int y) {
		return find(x) == find(y);
	}
    
    static int solution(int V, int[][] edges){    	
    	parent = new int[V + 1]; // 유니온 파인드 루트 노드 저장하기 위한 배열
    	rank = new int[V + 1];
    	int totalWeight = 0;
    	int edgeCount = 0;
    	
    	for (int i = 0; i <= V; i++) {
    		parent[i] = i;
    	}
    	
    	Arrays.sort(edges, (o1, o2) -> o1[2] - o2[2]); // 간선 가중치 오름차순 정렬
    	
        for (int[] edge : edges) {
        	int from = edge[0];
        	int to = edge[1];
        	int weight = edge[2];
        	
        	if (!isConnected(from, to)) { // 싸이클 없을 시
        		totalWeight += weight; // MST 에 넣고
        		union(from, to); // 두 정점의 집합을 병합
        		
        		if (++edgeCount == V - 1) break; // 신장 트리 조건 만족 시 반복문 종료
        	}
        }
        
        
        return totalWeight;
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int V = Integer.parseInt(st.nextToken()); // 정점의 개수
        int E = Integer.parseInt(st.nextToken()); // 간선의 개수
        
        int[][] edges = new int[E][3];
        
        for (int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            
            edges[i][0] = Integer.parseInt(st.nextToken()); // from
            edges[i][1] = Integer.parseInt(st.nextToken()); // to
            edges[i][2] = Integer.parseInt(st.nextToken()); // weight
        }
        
        System.out.println(solution(V, edges));
                           
        br.close();
    }
}