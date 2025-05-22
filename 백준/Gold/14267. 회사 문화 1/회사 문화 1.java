import java.util.ArrayList;
import java.util.List;

public class Main {
	static int n, m;
	static List<Integer>[] tree;
	static int[] dp;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		n = in.nextInt();
		m = in.nextInt();

		tree = new List[n + 1];
		dp = new int[n + 1];

		for (int i = 1; i <= n; i++) {
			tree[i] = new ArrayList<>();
		}

		in.nextInt();

		for (int i = 2; i <= n; i++) {
			int parent = in.nextInt();
			tree[parent].add(i);
		}

		for (int i = 0; i < m; i++) {
			int node = in.nextInt();
			int w = in.nextInt();

			dp[node] += w;
		}

		dfs(1);

		for (int i = 1; i <= n; i++) {
			sb.append(dp[i]).append(" ");
		}

		System.out.println(sb);
	}

	static void dfs(int node) {
		for (Integer next : tree[node]) {
			dp[next] += dp[node];
			dfs(next);
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