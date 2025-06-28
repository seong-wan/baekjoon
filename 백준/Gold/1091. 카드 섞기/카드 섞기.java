public class Main {
	static int N, ans;
	static int[] target;
	static int[] shuffle;
	static int[] before;
	static int[] after;

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();

		N = in.nextInt();

		target = new int[N];
		shuffle = new int[N];
		before = new int[N];
		after = new int[N];

		for (int i = 0; i < N; i++) {
			target[i] = in.nextInt();
			before[i] = i;
		}

		for (int i = 0; i < N; i++) {
			shuffle[i] = in.nextInt();
		}

		while (!isCycle() || ans == 0) {
			if (check()) {
				System.out.print(ans);
				return;
			}

			for (int i = 0; i < N; i++) {
				after[shuffle[i]] = before[i];
			}

			before = after.clone();
			ans++;
		}

		System.out.print(-1);
	}

	static boolean isCycle() {
		for (int i = 0; i < N; i++) {
			if (before[i] != i)
				return false;
		}
		return true;
	}

	static boolean check() {
		for (int i = 0; i < N; i++) {
			if (i % 3 != target[before[i]])
				return false;
		}

		return true;
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