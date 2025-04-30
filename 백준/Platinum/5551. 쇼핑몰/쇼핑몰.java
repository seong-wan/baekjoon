import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
	static int N, M, K, ans;
	static List<int[]>[] adlist;
	static PriorityQueue<int[]> queue = new PriorityQueue<>((e1, e2) -> e1[1] - e2[1]);
	static int[] memo;

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();

		N = in.nextInt();
		M = in.nextInt();
		K = in.nextInt();

		adlist = new List[N + 1];
		memo = new int[N + 1];
		Arrays.fill(memo, Integer.MAX_VALUE);

		for (int i = 1; i <= N; i++) {
			adlist[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			int from = in.nextInt();
			int to = in.nextInt();
			int dis = in.nextInt();

			adlist[from].add(new int[] {to, dis});
			adlist[to].add(new int[] {from, dis});
		}

		for (int i = 0; i < K; i++) {
			int shop = in.nextInt();
			memo[shop] = 0;
			queue.add(new int[] {shop, 0});
		}

		dijk();

		for (int i = 1; i <= N; i++) {
			for (int[] to : adlist[i]) {
				if (to[0] < i)
					continue;

				ans = Math.max(ans, (memo[i] + memo[to[0]] + to[1] + 1) / 2);
			}
		}

		System.out.println(ans);
	}

	static void dijk() {
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int node = cur[0];
			int dist = cur[1];

			if (dist > memo[node])
				continue;

			for (int[] next : adlist[node]) {
				int nextDist = dist + next[1];
				if (memo[next[0]] <= nextDist)
					continue;

				memo[next[0]] = nextDist;
				queue.add(new int[] {next[0], nextDist});
			}
		}
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