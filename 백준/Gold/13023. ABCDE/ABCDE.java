import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N,M;
	static List<Integer>[] adjList;
	static int ans,flag;
	static boolean[] check;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		adjList = new ArrayList[N];

		
		for(int i=0; i<N; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adjList[a].add(b);
			adjList[b].add(a);
		}
		
		for(int i=0; i<N; i++) {
			check = new boolean[N];
			check[i] = true;
			if(ans == 1)break;
			dfs(i,0);
		}
		
		System.out.println(ans);
	}
	
	static void dfs(int start, int depth) {
		if(depth == 4) {
			ans = 1;
			flag = 1;
			return;
		}
		
		if(flag == 1) return;
		
		List<Integer> list = adjList[start];
		for (Integer i : list) {
			if(check[i]) continue;
			check[i] = true;
			dfs(i, depth+1);
			check[i] = false;
		}
	}
}