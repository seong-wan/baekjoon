import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static int N,M;
	static boolean[] visit1, visit2;
	static ArrayList<ArrayList<Integer>> adjList1 = new ArrayList<>();
	static ArrayList<ArrayList<Integer>> adjList2 = new ArrayList<>();
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < N+1; i++) { // 0 dummy
			adjList1.add(new ArrayList<Integer>());
			adjList2.add(new ArrayList<Integer>());
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adjList1.get(a).add(b);
			adjList2.get(b).add(a);
		}
		int cnt = 0;
		for (int i = 1; i <= N; i++) {
			visit1 = new boolean[N+1];
			visit2 = new boolean[N+1];
			if(N-1 == (dfs(i) + dfs2(i))) cnt++;
		}
		System.out.println(cnt);

	}
	
	static int dfs(int node) {
		
		int cnt=0;
		for (int now : adjList1.get(node)) {
			if( !visit1[now]) {
				visit1[now] = true;
				cnt += dfs(now)+1;
			}
		}
		
		return cnt;
	}
	static int dfs2(int node) {
		
		int cnt=0;
		for (int now : adjList2.get(node)) {
			if( !visit2[now]) {
				visit2[now] = true;
				cnt += dfs2(now)+1;
			}
		}
		
		return cnt;
	}

}