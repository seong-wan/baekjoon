public class Main {
	static int N, idx = 1;
	static int[] input;
	static boolean[] list = new boolean[400001];
	static int cnt;
	static boolean[] chk;

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();

		N = in.nextInt();
		input = new int[N];
		chk = new boolean[N];

		for (int i = 0; i < N; i++) {
			input[i] = in.nextInt();
		}

		while (idx < N) {
			for (int j = 0; j < idx; j++) {
				int sum = input[idx - 1] + input[j];

				list[sum + 200000] = true;
			}

			for (int i = idx; i < N; i++) {
				if (list[input[i] - input[idx - 1] + 200000] && !chk[i]) {
					chk[i] = true;
					cnt++;
				}
			}

			idx++;
		}

		System.out.println(cnt);
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