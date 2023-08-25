import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int V,E,K;
	static List<List<Edge>> list = new ArrayList<>(); //인접리스트 
	static int[] cost; //다익스트라 자료 구조 <= 이곳에 답이 정리되어 완성된다
	static boolean[] visit;
	static PriorityQueue<Edge> pqueue = new PriorityQueue<>((o1,o2) -> o1.c - o2.c);
	static StringBuilder sb = new StringBuilder();
	static final int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		K = Integer.parseInt(br.readLine());
		
		cost = new int[V+1]; //0 dummy
		visit = new boolean[V+1]; //0 dummy
		
		for(int i=0; i<=V; i++) {
			list.add(new ArrayList<>());
		}
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			list.get(a).add(new Edge(b,w));
		}
		
		Arrays.fill(cost, INF);
		dijkstra();
		
		for(int i=1; i<=V; i++) {
			sb.append(cost[i]!=INF ? cost[i] : "INF").append("\n");
		}
		
		System.out.println(sb);
	}
	
	static void dijkstra() {
		//시작 K
		cost[K] = 0;
		pqueue.offer(new Edge(K,0));
		
		while(!pqueue.isEmpty()) {
			Edge pe = pqueue.poll();
			
			if(visit[pe.v]) continue;
			visit[pe.v] = true;
			
			for(Edge ne : list.get(pe.v)) {
				if(ne.c + cost[pe.v] < cost[ne.v]) {
					cost[ne.v] = ne.c +cost[pe.v];
					ne.c = cost[ne.v];
					pqueue.offer(ne);
				}
			}
			
		}
	}
	
	static class Edge{
		int v, c;
		
		public Edge(int v, int w) {
			this.v = v;
			this.c = w;
		}
	}
}