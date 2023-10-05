import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, E, v1, v2;
	static List<int[]>[] adlist;
	static boolean[] visit;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		adlist = new List[N + 1];

		for (int i = 1; i <= N; i++) {
			adlist[i] = new ArrayList<>();
		} // 인접리스트 초기화

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			adlist[from].add(new int[] { to, cost });
			adlist[to].add(new int[] { from, cost });
		} // 인접리스트 입력

		st = new StringTokenizer(br.readLine());
		v1 = Integer.parseInt(st.nextToken());
		v2 = Integer.parseInt(st.nextToken());

		int n1 = dijkstra(1, v1) + dijkstra(v1, v2) + dijkstra(v2, N);
		int n2 = dijkstra(1, v2) + dijkstra(v2, v1) + dijkstra(v1, N);
		if (n1 < 0 && n2 < 0)
			System.out.println(-1);
		else if (n1 > 0 && n2 > 0)
			System.out.println(Math.min(n1, n2));
		else if (n1 > 0 && n2 < 0)
			System.out.println(n1);
		else if (n2 > 0 && n1 < 0)
			System.out.println(n2);
	}

	static int dijkstra(int s, int d) {
		visit = new boolean[N + 1];
		PriorityQueue<int[]> pq = new PriorityQueue<>((e1, e2) -> e1[1] - e2[1]);
		pq.add(new int[] { s, 0 });
		visit[s] = true;
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int from = cur[0];
			int cc = cur[1];

			if (from == d) {
				return cc;
			}
			visit[from] = true;
			for (int[] next : adlist[from]) {
				int to = next[0];
				int cost = next[1];
				if (!visit[to])
					pq.add(new int[] { to, cc + cost });

			}
		}
		return -5000000;
	}

}