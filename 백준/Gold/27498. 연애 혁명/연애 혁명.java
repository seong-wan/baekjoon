import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {// 스패닝 트리를 확실한 관계 우선,그 다음 가중치가 높은 간선 우선으로 만들면 됨
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, sum;
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

		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			int confirm = Integer.parseInt(st.nextToken());
			adlist[from].add(new int[] { to, cost, confirm });
			adlist[to].add(new int[] { from, cost, confirm });
			sum += cost;
		} // 인접리스트 입력

		prim();
	}

	static void prim() {
		PriorityQueue<int[]> pq = new PriorityQueue<>((e1, e2) -> e1[2] == e2[2] ? e2[1] - e1[1] : e2[2] - e1[2]);
		int cnt = 0, ans = 0;
		pq.add(new int[] { 1, 0, 0 });
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int to = cur[0];
			if (visit[to])
				continue;
			int cost = cur[1];
			cnt++;
			ans += cost;
			if (cnt == N) {
				System.out.println(sum - ans);
				return;
			}
			visit[to] = true;
			for (int[] next : adlist[to]) {
				if (visit[next[0]])
					continue;
				pq.add(next);
			}
		}
	}
}