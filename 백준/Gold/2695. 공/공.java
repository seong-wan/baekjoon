public class Main {
	static int P;
	static int[][] dp = new int[51][1001];
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		init();

		P = in.nextInt();
		for (int i = 0; i < P; i++) {
			sb.append(dp[in.nextInt()][in.nextInt()]).append("\n");
		}

		System.out.println(sb);
	}

	static void init() {
		for (int i = 1; i <= 1000; i++) {
			dp[1][i] = i;
		}

		for (int i = 2; i <= 50; i++) {
			dp[i][1] = 1;
			for (int j = 2; j <= 1000; j++) {
				dp[i][j] = Integer.MAX_VALUE;
				for (int k = 1; k <= (j + 1) / 2; k++) {
					dp[i][j] = Math.min(dp[i][j], Math.max(dp[i - 1][k - 1], dp[i][j - k]) + 1);
				}
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
