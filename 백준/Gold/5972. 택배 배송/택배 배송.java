import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {// 그냥 다익스트라
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M;
	static List<int[]>[] adlist;
	static boolean[] visit;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		adlist = new List[N + 1];
		visit = new boolean[N + 1];
		for (int i = 1; i <= N; i++) {
			adlist[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			adlist[from].add(new int[] { to, cost });
			adlist[to].add(new int[] { from, cost });
		}
		dijk();
	}

	static void dijk() {
		PriorityQueue<int[]> pq = new PriorityQueue<>((e1, e2) -> e1[1] - e2[1]);
		for (int[] next : adlist[1]) {
			pq.add(next);
		}
		visit[1] = true;
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int to = cur[0];
			int cost = cur[1];
			if (to == N) {
				System.out.println(cost);
				return;
			}
			if (visit[to])
				continue;
			visit[to] = true;
			for (int[] next : adlist[to]) {
				if (!visit[next[0]])
					pq.add(new int[] { next[0], cost + next[1] });
			}
		}
	}

}