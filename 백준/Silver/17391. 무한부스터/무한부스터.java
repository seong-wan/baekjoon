import java.util.Arrays;

public class Main {
	static int N, M;
	static int[][] map;
	static int[][] dp;

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		N = in.nextInt();
		M = in.nextInt();

		map = new int[N][M];
		dp = new int[N][M];

		for (int i = 0; i < N; i++) {
			Arrays.fill(dp[i], Integer.MAX_VALUE);
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = in.nextInt();
			}
		}

		dp[0][0] = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				int count = map[i][j];
				for (int k = 1; k <= count; k++) {
					if (i + k < N)
						dp[i + k][j] = Math.min(dp[i + k][j], dp[i][j] + 1);
					if (j + k < M)
						dp[i][j + k] = Math.min(dp[i][j + k], dp[i][j] + 1);
				}
			}
		}

		System.out.println(dp[N - 1][M - 1]);
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