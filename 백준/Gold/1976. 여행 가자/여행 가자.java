public class Main {
	static int N, M;
	static boolean[][] graph;
	static int[] route;

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		N = in.nextInt();
		M = in.nextInt();

		graph = new boolean[N + 1][N + 1];
		route = new int[M];

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				graph[i][j] = in.nextInt() == 1;

				if (i == j)
					graph[i][j] = true; // 자기 자신으로 가는 길은 항상 존재
			}
		}

		for (int i = 0; i < M; i++) {
			route[i] = in.nextInt();
		}

		floyd();

		for (int i = 0; i < M - 1; i++) {
			if (!graph[route[i]][route[i + 1]]) {
				System.out.print("NO");
				return;
			}
		}

		System.out.print("YES");
	}

	static void floyd() {
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (graph[i][k] && graph[k][j]) {
						graph[i][j] = true;
					}
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