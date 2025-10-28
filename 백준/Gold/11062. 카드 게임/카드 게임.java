public class Main {
	static int T, N;
	static StringBuilder sb = new StringBuilder();
	static int[][] dp = new int[1000][1000];
	static int[] sum = new int[1000];

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		T = in.nextInt();
		for (int t = 0; t < T; t++) {
			N = in.nextInt();

			for (int i = 0; i < N; i++) {
				dp[i][i] = in.nextInt();
			}

			sum[0] = dp[0][0];

			for (int i = 1; i < N; i++) {
				sum[i] = sum[i - 1] + dp[i][i];
			}

			for (int term = 1; term <= N - 1; term++) {
				for (int i = 0; i + term < N; i++) {
					int rangeSum = sum[i + term] - (i - 1 >= 0 ? sum[i - 1] : 0);
					dp[i][i + term] = rangeSum - Math.min(dp[i][i + term - 1], dp[i + 1][i + term]);
				}
			}

			sb.append(dp[0][N - 1]).append("\n");
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