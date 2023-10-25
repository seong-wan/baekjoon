import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, start;
	static List<int[]>[] edge;
	static boolean[] visit;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		edge = new List[N + 1];

		for (int i = 1; i <= N; i++) {
			edge[i] = new ArrayList<>();
		}
		st = new StringTokenizer(br.readLine());
		st.nextToken();
		st.nextToken();
		start = Integer.parseInt(st.nextToken());// 0에서 1까지의 오르막길 내리막길 여부

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			edge[from].add(new int[] { to, cost });
			edge[to].add(new int[] { from, cost });
		}

		System.out.println(prim(0) - prim(1));

	}

	static int prim(int num) {
		PriorityQueue<int[]> pq = new PriorityQueue<>((e1, e2) -> num == 0 ? e1[1] - e2[1] : e2[1] - e1[1]);
		visit = new boolean[N + 1];
		int ans = 0;
		int cnt = 0;
		pq.add(new int[] { 1, start });
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int to = cur[0];
			if (visit[to])
				continue;
			int cost = cur[1] == 0 ? 1 : 0;
			cnt++;
			ans += cost;
			if (cnt == N)
				return ans * ans;
			visit[to] = true;
			for (int[] next : edge[to]) {
				if (!visit[next[0]])
					pq.add(next);
			}
		}

		return -1;
	}
}