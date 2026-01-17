import java.util.ArrayList;
import java.util.List;

public class Main {
	static int N, root;
	static int[] origin, after, dp;
	static List<Integer>[] tree;
	static boolean[] visit;

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		N = in.nextInt();
		root = in.nextInt();

		origin = new int[N + 1];
		after = new int[N + 1];
		dp = new int[N + 1];
		visit = new boolean[N + 1];
		tree = new List[N + 1];

		for (int i = 1; i <= N; i++) {
			tree[i] = new ArrayList<>();
			origin[i] = in.nextInt();
		}

		for (int i = 1; i <= N; i++) {
			after[i] = in.nextInt();
		}

		for (int i = 0; i < N - 1; i++) {
			int from = in.nextInt();
			int to = in.nextInt();

			tree[from].add(to);
			tree[to].add(from);
		}

		visit[root] = true;
		dfs(root);

		System.out.println(dp[root]);
	}

	static void dfs(int node) {
		int need = 0;
		int soil = origin[node] - after[node];
		for (Integer next : tree[node]) {
			if (visit[next])
				continue;

			visit[next] = true;

			dfs(next);

			need += dp[next];
		}

		if (soil >= 0)
			dp[node] = Math.max(need - soil, 0);
		else
			dp[node] = need - soil;
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
