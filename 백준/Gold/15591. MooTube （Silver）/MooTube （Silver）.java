import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N,Q;
	static ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
	static boolean[] visit;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N+1; i++) {
			graph.add(new ArrayList<int[]>());
		}
		
		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			graph.get(p).add(new int[] {q,r});
			graph.get(q).add(new int[] {p,r});
		}
		for (int i = 0; i < Q; i++) {
			visit = new boolean[N+1]; // 0 dummy
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int cnt = 0;
			
			visit[v] = true;
			Queue<int[]> queue = new ArrayDeque<>();
			
			for (int[] now : graph.get(v)) {
				if( !visit[now[0]] && now[1] >= k) {
					queue.offer(now);
					visit[now[0]] = true;
					cnt++;
				}
			}

			while(! queue.isEmpty()) {
				int[] now = queue.poll();
				
				for (int[] temp : graph.get(now[0])) {
					if( !visit[temp[0]] && temp[1] >= k) {
						cnt++;
						queue.offer(temp);
						visit[temp[0]] = true;
						
					}
				}
				
			}
			
			System.out.println(cnt);
			
		}
	}
}