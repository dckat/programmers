package SSG_coding_test.week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1976 {
	
	/*
	 * [BOJ]1976 여행 가자
	 * 
	 * 동혁이가 가고자 하는 도시들이 하나의 집합에 속해 있다면 여행 가능
	 * ==> 유니온 파인드
	 */
	
	static int[] parent; // 루트 노드를 담을 배열(i : 해당 노드 / parent[i] : i 노드의 루트 노드 )
	static int[] ranks; // 트리의 깊이를 나타내는 배열(효율적인 union 연산 목적) => 깊이가 얕은 트리가 더 깊은 트리에 병합됨을 보장하기 위함
	
	static int find(int x) {
		// x가 루트 노드가 아니라면, 루트 노드를 찾으면서 경로 압축 수행
		if (parent[x] == x) return x;
		return parent[x] = find(parent[x]);
	}
	
	static void union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if (x != y) {
			if (ranks[x] > ranks[y]) { // 루트 노드 x 트리가 더 깊을 시 
				parent[y] = x; // 루트 노드 y 트리를 x 노드에 연결
			} else if (ranks[x] < ranks[y]) {
				parent[x] = y;
			} else { // 깊이가 같을 경우
				parent[y] = x; // y를 x에 붙인 후
				ranks[x]++; // x 랭크 1 추가
			}
		}
	}
	
	static boolean canTravel(int[] plan) {
		int root = find(plan[0]);
		
		for (int i = 1; i < plan.length; i++) {
			if (root != find(plan[i])) return false;
		}
		
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine()); // 전체 도시의 수
		int M = Integer.parseInt(br.readLine()); // 여행 계획에 속한 도시들의 수 M
		
		parent = new int[N + 1];
		ranks = new int[N + 1];
		
		for (int i = 1; i <= N; i++) { // 최초에는 자기 자신이 트리의 최상위 노드임
			parent[i] = i;
		}
		
		StringTokenizer st;
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
						
			for (int j = 1; j <= N; j++) {
				int num = Integer.parseInt(st.nextToken());
				
				if (num == 1) { // 연결
					union(i, j);
				}
			}
			
		}
		
		st = new StringTokenizer(br.readLine());
		
		int[] plan = new int[M]; // 계획
				
		for (int i = 0; i < M; i++) {
			plan[i] = Integer.parseInt(st.nextToken());
		}
		
		
		System.out.println(canTravel(plan) ? "YES" : "NO");
		
		br.close();
	}
}
