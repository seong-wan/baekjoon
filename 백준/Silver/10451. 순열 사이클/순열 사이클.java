public class Main {
	static int T, N;
	static StringBuilder sb = new StringBuilder();
	static int[] input;
	static boolean[] visit;

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		T = in.nextInt();

		for (int i = 0; i < T; i++) {
			N = in.nextInt();
			input = new int[N + 1];
			visit = new boolean[N + 1];
			int cnt = 0;

			for (int j = 1; j <= N; j++) {
				input[j] = in.nextInt();
			}

			for (int j = 1; j <= N; j++) {
				if (visit[j])
					continue;

				cnt++;
				check(j);
			}

			sb.append(cnt).append("\n");
		}

		System.out.print(sb);
	}

	static void check(int num) {
		visit[num] = true;
		int next = input[num];

		if (visit[next])
			return;

		check(next);
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