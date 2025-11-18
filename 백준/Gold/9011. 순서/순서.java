public class Main {
	static int[] arr = new int[100], ans = new int[100];
	static int T, N;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		T = in.nextInt();

		for (int t = 0; t < T; t++) {
			N = in.nextInt();
			boolean avail = true;
			boolean[] visited = new boolean[N + 1];

			for (int i = 0; i < N; i++) {
				arr[i] = in.nextInt();
			}

			for (int i = N - 1; i >= 0; i--) {
				int findSeq = arr[i] + 1;

				int cnt = 0;
				for (int j = 1; j <= N; j++) {
					if (!visited[j])
						cnt++;

					if (cnt == findSeq) {
						ans[i] = j;
						visited[j] = true;
						break;
					}

					if (j == N)
						avail = false;
				}

				if (!avail)
					break;
			}

			if (!avail) {
				sb.append("IMPOSSIBLE");
			} else {
				for (int i = 0; i < N; i++) {
					sb.append(ans[i]).append(" ");
				}
			}
			sb.append("\n");
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