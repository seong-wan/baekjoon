import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
	static int N, M, A, B, C;
	static int ans = 1001;
	static List<int[]>[] adlist;
	static boolean[][] visit;

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		N = in.nextInt();
		M = in.nextInt();
		A = in.nextInt();
		B = in.nextInt();
		C = in.nextInt();

		adlist = new List[N + 1];
		for (int i = 1; i <= N; i++) {
			adlist[i] = new ArrayList<>();
		}

		visit = new boolean[N + 1][1000];

		for (int i = 0; i < M; i++) {
			int from = in.nextInt();
			int to = in.nextInt();
			int cost = in.nextInt();

			adlist[from].add(new int[] {to, cost});
			adlist[to].add(new int[] {from, cost});
		}

		dijk();

		System.out.print(ans == 1001 ? -1 : ans);
	}

	static void dijk() {
		//node,totalCost,maximumCost
		PriorityQueue<int[]> pq = new PriorityQueue<>((e1, e2) -> e1[1] - e2[1]);
		pq.add(new int[] {A, 0, 0});

		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int node = cur[0];
			int cost = cur[1];
			int maxCost = cur[2];

			if (visitCheck(node, maxCost))
				continue;

			if (node == B) {
				ans = Math.min(ans, maxCost);
				continue;
			}

			visit[node][maxCost] = true;

			for (int[] next : adlist[node]) {
				int nextNode = next[0];
				int newTotalCost = cost + next[1];
				int newMaxCost = Math.max(maxCost, next[1]);

				if (newTotalCost <= C && !visitCheck(nextNode, newMaxCost)) {
					pq.add(new int[] {nextNode, newTotalCost, newMaxCost});
				}
			}
		}
	}

	static boolean visitCheck(int node, int maxCost) {
		for (int i = maxCost; i >= 0; i--) {
			if (visit[node][i])
				return true;
		}

		return false;
	}

	static class Reader {
		final int SIZE = 1 << 13;
		byte[] buffer = new byte[SIZE];
		int index, size;

		int nextInt() throws Exception {
			int n = 0;
			byte c;
			boolean isMinus = false;
			while ((c = read()) <= 32) {
				if (size < 0)
					return -1;
			}
			if (c == 45) {
				c = read();
				isMinus = true;
			}
			do
				n = (n << 3) + (n << 1) + (c & 15);
			while (isNumber(c = read()));
			return isMinus ? ~n + 1 : n;
		}

		boolean isNumber(byte c) {
			return 47 < c && c < 58;
		}

		byte read() throws Exception {
			if (index == size) {
				size = System.in.read(buffer, index = 0, SIZE);
				if (size < 0)
					buffer[0] = -1;
			}
			return buffer[index++];
		}
	}
}