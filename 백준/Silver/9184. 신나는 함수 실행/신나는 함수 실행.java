import java.util.Arrays;

public class Main {
	static int a, b, c;
	static int[][][] memo = new int[21][21][21];
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();

		for (int i = 0; i <= 20; i++) {
			for (int j = 0; j <= 20; j++) {
				Arrays.fill(memo[i][j], Integer.MAX_VALUE);
			}
		}

		while (true) {
			a = in.nextInt();
			b = in.nextInt();
			c = in.nextInt();

			if (a == -1 && b == -1 && c == -1)
				break;

			int ans = dfs(a, b, c);

			sb.append(String.format("w(%d, %d, %d) = %d", a, b, c, ans)).append("\n");
		}

		System.out.println(sb);
	}

	static int dfs(int a, int b, int c) {
		if (a <= 0 || b <= 0 || c <= 0)
			return 1;

		if (a > 20 || b > 20 || c > 20)
			return dfs(20, 20, 20);

		if (memo[a][b][c] != Integer.MAX_VALUE)
			return memo[a][b][c];

		if (a < b && b < c)
			return memo[a][b][c] = dfs(a, b, c - 1) + dfs(a, b - 1, c - 1) - dfs(a, b - 1, c);

		return memo[a][b][c] =
			dfs(a - 1, b, c) + dfs(a - 1, b - 1, c) + dfs(a - 1, b, c - 1) - dfs(a - 1, b - 1, c - 1);
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