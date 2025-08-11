import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
	static int T;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();

		T = in.nextInt();
		for (int t = 0; t < T; t++) {
			int N = in.nextInt();
			int P = in.nextInt();
			int M = in.nextInt();
			int ans = -1;

			List<int[]>[] adlist = new List[N + 1];
			for (int i = 1; i <= N; i++) {
				adlist[i] = new ArrayList<>();
			}

			int[][] friends = new int[P][2];
			for (int i = 0; i < P; i++) {
				friends[i][0] = in.nextInt();
				friends[i][1] = in.nextInt();
			}

			for (int i = 0; i < M; i++) {
				int D = in.nextInt();
				int L = in.nextInt();

				int from = in.nextInt();
				for (int j = 1; j < L; j++) {
					int to = in.nextInt();

					adlist[from].add(new int[] {to, D});
					adlist[to].add(new int[] {from, D});
					from = to;
				}
			}

			long[][] dist = new long[P][N + 1];
			for (int i = 0; i < P; i++) {
				Arrays.fill(dist[i], Integer.MAX_VALUE + 1L);
			}

			for (int i = 0; i < P; i++) {
				dijk(adlist, dist[i], friends[i]);
			}

			for (int i = 1; i <= N; i++) {
				long temp = 0;
				for (int j = 0; j < P; j++) {
					temp = Math.max(temp, dist[j][i]);
				}

				if (temp <= Integer.MAX_VALUE) {
					if (ans == -1)
						ans = (int)temp;
					else
						ans = Math.min(ans, (int)temp);
				}
			}

			sb.append("Case #").append(t + 1).append(": ").append(ans).append("\n");
		}

		System.out.print(sb);
	}

	static void dijk(List<int[]>[] adlist, long[] dist, int[] freind) {
		PriorityQueue<int[]> pq = new PriorityQueue<>((e1, e2) -> e1[1] - e2[1]);
		pq.add(new int[] {freind[0], 0});

		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int node = cur[0];
			int cost = cur[1];

			if (dist[node] <= cost) {
				continue;
			}

			dist[node] = cost;
			for (int[] next : adlist[node]) {
				int nextNode = next[0];
				int nextCost = next[1] * freind[1];

				if (dist[nextNode] > cost + nextCost) {
					pq.add(new int[] {nextNode, cost + nextCost});
				}
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