public class Main {
	static int N, M, K, X;
	static StringBuilder sb = new StringBuilder();
	static int[] cnt;

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		N = in.nextInt();
		M = in.nextInt();
		K = in.nextInt();
		X = in.nextInt();

		cnt = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			X += in.nextInt();

			cnt[i] = cnt[i - 1];

			if (X < K)
				cnt[i]++;
		}

		for (int i = 0; i < M; i++) {
			int l = in.nextInt();
			int r = in.nextInt();

			sb.append(cnt[r - 1] - cnt[l - 1]).append("\n");
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