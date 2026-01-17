public class Main {
	static int N, P, Q, J, K;
	static StringBuilder sb = new StringBuilder();
	static int[][] dp;

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();

		while (true) {
			N = in.nextInt();
			if (N == 0)
				break;

			P = in.nextInt();
			Q = in.nextInt();
			J = in.nextInt();
			K = in.nextInt();

			dp = new int[N][K + 1];
			dp[J][0] = 1;

			for (int level = 0; level < K; level++) {
				for (int i = 0; i < N; i++) {
					if (dp[i][level] == 0)
						continue;

					if (i - 1 >= 0)
						dp[i - 1][level + 1] += dp[i][level];
					if (i + 1 < N)
						dp[i + 1][level + 1] += dp[i][level];
				}
			}

			int ans = 0;
			for (int i = P; i <= Q; i++) {
				ans += dp[i][K];
			}

			sb.append(ans).append("\n");
		}

		System.out.print(sb);
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