import java.util.Arrays;

public class Main {
	static int T;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();

		T = in.nextInt();
		for (int t = 0; t < T; t++) {
			int N = in.nextInt();
			int P = in.nextInt();
			int M = in.nextInt();
			int ans = -1;

			long[][] adlist = new long[N + 1][N + 1];

			for (int i = 1; i <= N; i++) {
				Arrays.fill(adlist[i], Integer.MAX_VALUE);
				adlist[i][i] = 0;
			}

			int[][] friends = new int[P][2];
			for (int i = 0; i < P; i++) {
				friends[i][0] = in.nextInt();
				friends[i][1] = in.nextInt();
			}

			for (int i = 0; i < M; i++) {
				int D = in.nextInt();
				int L = in.nextInt();

				int from = in.nextInt();
				for (int j = 1; j < L; j++) {
					int to = in.nextInt();

					adlist[from][to] = D;
					adlist[to][from] = D;
					from = to;
				}
			}

			for (int k = 1; k <= N; k++) {
				for (int i = 1; i <= N; i++) {
					for (int j = 1; j <= N; j++) {
						adlist[i][j] = Math.min(adlist[i][j], adlist[i][k] + adlist[k][j]);
					}
				}
			}

			for (int i = 1; i <= N; i++) {
				long temp = 0;
				for (int j = 0; j < P; j++) {
					temp = Math.max(temp, (long)adlist[friends[j][0]][i] * friends[j][1]);
				}

				if (temp <= Integer.MAX_VALUE) {
					if (ans == -1)
						ans = (int)temp;
					else
						ans = Math.min(ans, (int)temp);
				}
			}

			sb.append("Case #").append(t + 1).append(": ").append(ans == Integer.MAX_VALUE ? -1 : ans).append("\n");
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