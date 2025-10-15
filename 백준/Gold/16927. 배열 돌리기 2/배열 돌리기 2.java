public class Main {
	static int N, M, R;
	static int[][] arrays = new int[300][300], result = new int[300][300];
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		N = in.nextInt();
		M = in.nextInt();
		R = in.nextInt();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				arrays[i][j] = in.nextInt();
			}
		}

		rotateArray(0, N - 1, M - 1);

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(result[i][j]).append(" ");
			}
			sb.append("\n");
		}

		System.out.print(sb);
	}

	static void rotateArray(int base, int maxN, int maxM) {
		if (base >= maxN || base >= maxM)
			return;

		int n = maxN - base;
		int m = maxM - base;
		int T = 2 * (n + m);

		for (int offset = 0; offset < T; offset++) {
			int r = base, c = base, moveR = base, moveC = base;
			r += findR(n, m, offset);
			c += findC(n, m, offset);

			moveR += findR(n, m, (offset + R) % T);
			moveC += findC(n, m, (offset + R) % T);

			result[moveR][moveC] = arrays[r][c];
		}

		rotateArray(base + 1, maxN - 1, maxM - 1);
	}

	static int findR(int n, int m, int offset) {
		if (n + m >= offset)
			return Math.min(n, offset);
		else {
			offset -= n + m;
			return Math.max(0, n - offset);
		}
	}

	static int findC(int n, int m, int offset) {
		if (n + m >= offset)
			return Math.max(0, offset - n);
		else {
			offset -= n + m;
			return Math.min(m - (offset - n), m);
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