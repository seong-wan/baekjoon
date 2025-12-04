public class Main {
	static int sumL, sumR, maxL, maxR, N, T;
	static int[] inputR = new int[1000];

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		N = in.nextInt();
		T = in.nextInt();

		for (int i = 0; i < N; i++) {
			int L = in.nextInt();
			int R = in.nextInt();
			sumL += L;
			sumR += R;
			maxL = Math.max(maxL, L);
			maxR = Math.max(maxR, R);

			inputR[i] = R;
		}

		if (sumL > T || sumR < T) {
			System.out.print(-1);
			return;
		}

		int left = maxL;
		int right = maxR;

		while (left <= right) {
			int mid = (left + right) >> 1;
			int total = 0;

			for (int i = 0; i < N; i++) {
				total += Math.min(inputR[i], mid);
			}

			if (total >= T)
				right = mid - 1;
			else
				left = mid + 1;
		}

		System.out.print(left);
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