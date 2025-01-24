public class Main {
	static int N;
	static long K;
	static long[] input;

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		N = in.nextInt();
		K = in.nextLong();

		input = new long[N];
		for (int i = 0; i < N; i++) {
			input[i] = in.nextLong();
		}

		System.out.println(search());
	}

	static long search() {
		long left = 0;
		long right = 1000_000_000_000L;

		while (left <= right) {
			long mid = (left + right) >> 1;
			long temp = 0;
			for (int i = 0; i < N; i++) {
				if (mid < input[i]) {
					temp += input[i] - mid;

					if (temp > K)
						break;
				}
			}

			if (temp > K) {
				left = mid + 1;
			} else
				right = mid - 1;
		}

		return left;
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

		long nextLong() throws Exception {
			long n = 0;
			byte c;
			boolean isMinus = false;
			while ((c = read()) <= 32)
				;
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