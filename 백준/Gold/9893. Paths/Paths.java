import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
	static int n, m;
	static List<int[]>[] adlist;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		n = in.nextInt();
		m = in.nextInt();

		visited = new boolean[n];
		adlist = new List[n];
		for (int i = 0; i < n; i++) {
			adlist[i] = new ArrayList<>();
		}

		for (int i = 0; i < m; i++) {
			int from = in.nextInt();
			int to = in.nextInt();
			int cost = in.nextInt();

			adlist[from].add(new int[] {to, cost});
		}

		System.out.print(dijk());
	}

	static int dijk() {
		PriorityQueue<int[]> pq = new PriorityQueue<>((e1, e2) -> e1[1] == e2[1] ? e1[2] - e2[2] : e1[1] - e2[1]);
		pq.add(new int[] {0, 0, 0});//to, cnt, cost

		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int to = cur[0];
			int cnt = cur[1];
			int cost = cur[2];

			if (to == 1)
				return cost;

			if (visited[to])
				continue;

			visited[to] = true;

			for (int[] next : adlist[to]) {
				if (visited[next[0]])
					continue;

				pq.add(new int[] {next[0], cnt + 1, cost + next[1]});
			}
		}

		return -1;
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