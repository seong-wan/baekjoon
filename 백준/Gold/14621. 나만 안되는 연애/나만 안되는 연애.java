import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static List<int[]>[] edge;
	static boolean[] visit;
	static char[] gender;
	static int N, M, ans;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		edge = new List[N + 1];
		visit = new boolean[N + 1];
		gender = new char[N + 1];// 0은 더미

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			edge[i] = new ArrayList<>();
			gender[i] = st.nextToken().charAt(0);
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			edge[from].add(new int[] { to, cost });
			edge[to].add(new int[] { from, cost });
		} // 정점별 간선 정보 입력

		prim();
		System.out.println(ans);
	}

	static void prim() {
		PriorityQueue<int[]> pq = new PriorityQueue<>((e1, e2) -> e1[1] - e2[1]);
		int cnt = 0;
		pq.add(new int[] { 1, 0 });
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int to = cur[0];
			if (visit[to])
				continue;
			int cost = cur[1];
			cnt++;
			ans += cost;
			if (cnt == N)
				return;
			visit[to] = true;
			for (int[] next : edge[to]) {
				int nextn = next[0];
				if (!visit[nextn] && gender[to] != gender[nextn])
					pq.add(new int[] { nextn, next[1] });
			}
		}
		ans = -1;
	}
}