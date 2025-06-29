import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
	static int n, m, s, e, ans;
	static List<int[]>[] adlist;
	static int[] chk;

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();

		n = in.nextInt();
		m = in.nextInt();
		s = in.nextInt();
		e = in.nextInt();

		chk = new int[n + 1];
		Arrays.fill(chk, Integer.MAX_VALUE);

		adlist = new List[n + 1];
		for (int i = 1; i <= n; i++) {
			adlist[i] = new ArrayList<>();
		}

		for (int i = 0; i < m; i++) {
			int from = in.nextInt();
			int to = in.nextInt();
			int c = in.nextInt();
			int t = in.nextInt();

			adlist[from].add(new int[] {to, c, t});
			adlist[to].add(new int[] {from, c, t});
		}

		dijk();

		System.out.println(ans);
	}

	static void dijk() {
		PriorityQueue<int[]> pq = new PriorityQueue<>((e1, e2) -> e1[1] == e2[1] ? e1[2] - e2[2] : e1[1] - e2[1]);
		pq.add(new int[] {s, 0, 0});

		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int node = cur[0];
			int cost = cur[1];
			int time = cur[2];

			if (chk[node] <= time)
				continue;

			chk[node] = time;

			if (node == e) {
				ans++;
				continue;
			}

			for (int[] next : adlist[node]) {
				int nextC = cost + next[1];
				int nextT = time + next[2];

				if (chk[next[0]] <= nextT)
					continue;

				pq.add(new int[] {next[0], nextC, nextT});
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