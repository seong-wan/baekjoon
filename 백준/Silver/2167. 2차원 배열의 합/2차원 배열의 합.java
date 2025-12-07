public class Main {
	static int N, M, K;
	static int[][] input = new int[301][301];
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		N = in.nextInt();
		M = in.nextInt();

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				input[i][j] = input[i][j - 1] + input[i - 1][j] - input[i - 1][j - 1] + in.nextInt();
			}
		}

		K = in.nextInt();

		for (int i = 0; i < K; i++) {
			int startR = in.nextInt();
			int startC = in.nextInt();
			int endR = in.nextInt();
			int endC = in.nextInt();

			sb.append(
					input[endR][endC] + input[startR - 1][startC - 1]
						- input[endR][startC - 1] - input[startR - 1][endC])
				.append("\n");
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