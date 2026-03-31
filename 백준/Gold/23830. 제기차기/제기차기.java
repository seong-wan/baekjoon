public class Main {
	static int N, p, q, r;
	static long sum, S;
	static int[] scores;
	static boolean avail;

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();

		N = in.nextInt();

		scores = new int[N];
		for (int i = 0; i < N; i++) {
			scores[i] = in.nextInt();
			sum += scores[i];
		}

		p = in.nextInt();
		q = in.nextInt();
		r = in.nextInt();
		S = in.nextLong();

		int left = 1;
		int right = 1100001;
		while (left <= right) {
			int mid = (left + right) >> 1;
			long temp = sum;

			for (int i = 0; i < N; i++) {
				if (scores[i] < mid)
					temp += q;
				else if (scores[i] > mid + r)
					temp -= p;
			}

			if (temp >= S) {
				avail = true;
				right = mid - 1;
			} else
				left = mid + 1;
		}

		System.out.print(avail ? left : -1);
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