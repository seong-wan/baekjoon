import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static List<int[]>[] adlist = new List[10001];
	static int cnt = 1;
	static int start = 1, maxCost;

	public static void main(String[] args) throws Exception {
		for (int i = 1; i <= 10000; i++) {
			adlist[i] = new ArrayList<>();
		}

		String input = "";
		while ((input = br.readLine()) != null && !input.isEmpty()) {
			cnt++;

			StringTokenizer st = new StringTokenizer(input);

			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			adlist[from].add(new int[] {to, cost});
			adlist[to].add(new int[] {from, cost});
		}

		dijk(1, false); //임의의 점 1번을 기준으로 가장 먼 지점을 찾음
		maxCost = 0;
		dijk(start, true);

		System.out.print(maxCost);
	}

	static void dijk(int node, boolean mode) {
		PriorityQueue<int[]> pq = new PriorityQueue<>((e1, e2) -> e2[1] - e1[1]);
		pq.add(new int[] {node, 0});
		boolean[] visit = new boolean[cnt + 1];

		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int to = cur[0];
			int cost = cur[1];

			if (visit[to])
				continue;

			visit[to] = true;

			if (cost > maxCost) {
				maxCost = cost;

				if (!mode)
					start = to;
			}

			for (int[] next : adlist[to]) {
				if (visit[next[0]])
					continue;

				pq.add(new int[] {next[0], cost + next[1]});
			}
		}
	}
}