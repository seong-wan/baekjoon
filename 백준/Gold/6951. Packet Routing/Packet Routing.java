public class Main {
	static int N, W, P;
	static StringBuilder sb = new StringBuilder();
	static int[][] matrix = new int[101][101];

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();

		N = in.nextInt();
		W = in.nextInt();
		P = in.nextInt();

		for (int i = 1; i <= W; i++) {
			int a = in.nextInt();
			int b = in.nextInt();
			int time = in.nextInt();

			matrix[a][b] = matrix[b][a] = time;
		}

		floyd();

		for (int i = 0; i < P; i++) {
			sb.append(matrix[in.nextInt()][in.nextInt()]).append("\n");
		}

		System.out.print(sb);
	}

	static void floyd() {
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				if (i == k)
					continue;
				for (int j = 1; j <= N; j++) {
					if (i == j || j == k)
						continue;

					if (matrix[i][j] != 0)
						continue;

					if (matrix[i][k] == 0 || matrix[k][j] == 0)
						continue;

					matrix[i][j] = matrix[j][i] = matrix[i][k] + matrix[k][j];
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