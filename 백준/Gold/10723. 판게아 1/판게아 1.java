import java.util.ArrayList;
import java.util.List;

public class Main {
	static int n, m, T;
	static StringBuilder sb = new StringBuilder();
	static List<int[]> edges;
	static int[] parent;

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();

		T = in.nextInt();

		for (int t = 0; t < T; t++) {
			n = in.nextInt();
			m = in.nextInt();
			long ans = 0;

			edges = new ArrayList<>();

			for (int i = 1; i < n; i++) {
				int to = in.nextInt();
				int cost = in.nextInt();

				edges.add(new int[] {i, to, cost});
			}

			for (int i = 0; i < m; i++) {
				int from = in.nextInt();
				int to = in.nextInt();
				int cost = in.nextInt();

				edges.add(new int[] {from, to, cost});

				ans ^= kruskal();
			}

			sb.append(ans).append("\n");
		}

		System.out.print(sb);
	}

	static void make() {
		parent = new int[n];
		for (int i = 0; i < n; i++) {
			parent[i] = i;
		}
	}

	static int find(int num) {
		if (parent[num] == num)
			return num;

		return parent[num] = find(parent[num]);
	}

	static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);

		if (rootA == rootB)
			return false;

		parent[rootB] = rootA;
		return true;
	}

	static long kruskal() {
		make();
		long result = 0;

		edges.sort((e1, e2) -> e1[2] - e2[2]);

		for (int[] edge : edges) {
			int from = edge[0];
			int to = edge[1];
			int cost = edge[2];

			if (union(from, to))
				result += cost;
		}

		return result;
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