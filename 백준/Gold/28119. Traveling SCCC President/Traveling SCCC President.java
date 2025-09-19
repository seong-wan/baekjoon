import java.util.PriorityQueue;

public class Main {
	static int N, M, S, ans;
	static int[] parent;
	static PriorityQueue<int[]> edges = new PriorityQueue<>((e1, e2) -> e1[2] - e2[2]);

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		N = in.nextInt();
		M = in.nextInt();
		S = in.nextInt();

		make();

		for (int i = 0; i < M; i++) {
			int from = in.nextInt();
			int to = in.nextInt();
			int cost = in.nextInt();

			edges.add(new int[] {from, to, cost});
		}

		while (!edges.isEmpty()) {
			int[] cur = edges.poll();
			int from = cur[0];
			int to = cur[1];
			int cost = cur[2];

			if (union(from, to))
				ans += cost;
		}

		System.out.print(ans);
	}

	static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);

		if (rootA == rootB)
			return false;

		parent[rootB] = rootA;
		return true;
	}

	static int find(int num) {
		if (parent[num] == num)
			return num;

		return parent[num] = find(parent[num]);
	}

	static void make() {
		parent = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
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