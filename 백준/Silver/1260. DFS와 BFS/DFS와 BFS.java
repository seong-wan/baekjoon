

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N,M,V;
	static List<List<Integer>> adjList = new ArrayList<>();
	static StringBuilder sb = new StringBuilder();
	static boolean[] select;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		
		select = new boolean[N+1];
		
		for(int i=0; i<=N; i++) { //0 dummy
			adjList.add(new ArrayList<Integer>());
		}
	
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adjList.get(a).add(b);
			adjList.get(b).add(a);
		}
		
		for(int i=0; i<adjList.size(); i++) {
			Collections.sort(adjList.get(i));
		}
		
		select[V] = true;
		dfs(V);
		sb.append("\n");
		select = new boolean[N+1];

		bfs(V);
		
		System.out.println(sb);
	}
	
	static void dfs(int start) {
		sb.append(start).append(" ");
		
		List<Integer> list = adjList.get(start);
		for(Integer i : list) {
			if(select[i]) continue;
			select[i] = true;
			dfs(i);
		}
		
		
	}
	
	static void bfs(int start) {
		Queue<Integer> q = new ArrayDeque<>();
		
		q.offer(start);
		select[start] = true;
		
		while(!q.isEmpty()) {
			int v = q.poll();
			sb.append(v).append(" ");
			
			List<Integer> list = adjList.get(v);
			for(Integer i : list) {
				if(select[i]) continue;
				q.offer(i);
				select[i] = true;
			}
		}
	}

}
