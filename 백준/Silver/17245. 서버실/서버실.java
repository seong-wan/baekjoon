public class Main {
	static int N;
	static int[][] map;
	static long[] memo = new long[10_000_001];
	static long[] sum = new long[10_000_001];
	static long findValue;

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		N = in.nextInt();

		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = in.nextInt();
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				memo[map[i][j]]++;
			}
		}

		sum[10000000] = memo[10000000];

		for (int i = 9_999_999; i > 0; i--) {
			sum[i] = memo[i] + sum[i + 1];
			memo[i] *= i;
		}

		for (int i = 2; i <= 10_000_000; i++) {
			memo[i] += memo[i - 1];
		}

		for (int i = 2; i <= 10_000_000; i++) {
			sum[i] = sum[i] * i + memo[i - 1];
		}

		findValue = (sum[10_000_000] + 1) >> 1;

		System.out.println(search());
	}

	static int search() {
		int left = 0;
		int right = 10_000_000;

		while (left <= right) {
			int mid = (left + right) >> 1;

			if (sum[mid] >= findValue)
				right = mid - 1;
			else
				left = mid + 1;

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