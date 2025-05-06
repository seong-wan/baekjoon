public class Main {
	static int N, root;
	static int[][] memo = new int[10001][2];//높이당 최소 최대 열의 값
	static int[][] tree;
	static int[] ans = new int[2];
	static int[] parent;

	public static void main(String[] args) throws Exception {
		for (int i = 1; i <= 10000; i++) {
			memo[i][0] = Integer.MAX_VALUE;
		}

		Reader in = new Reader();

		N = in.nextInt();
		tree = new int[N + 1][2];
		parent = new int[N + 1];

		for (int i = 0; i < N; i++) {
			int node = in.nextInt();
			int left = in.nextInt();
			int right = in.nextInt();

			tree[node][0] = left;
			tree[node][1] = right;

			if (left != -1)
				parent[left] = node;
			if (right != -1)
				parent[right] = node;
		}

		for (int i = 1; i <= N; i++) {
			if (parent[i] == 0) {
				root = i;
				break;
			}
		}

		dfs(root, 1, 0);

		for (int i = 1; i <= 10000; i++) {
			int diff = memo[i][1] - memo[i][0] + 1;

			if (diff > ans[1]) {
				ans[0] = i;
				ans[1] = diff;
			}
		}

		System.out.println(ans[0] + " " + ans[1]);
	}

	static int dfs(int node, int h, int cnt) {
		int left = tree[node][0];
		int right = tree[node][1];

		if (left != -1)
			cnt = dfs(left, h + 1, cnt);

		memo[h][0] = Math.min(memo[h][0], cnt);
		memo[h][1] = Math.max(memo[h][1], cnt);

		cnt += 1;

		if (right != -1)
			cnt = dfs(right, h + 1, cnt);

		return cnt;
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