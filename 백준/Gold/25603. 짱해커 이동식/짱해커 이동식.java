public class Main {
	static int N, K;
	static int[] costs = new int[100000];

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		N = in.nextInt();
		K = in.nextInt();

		for (int i = 0; i < N; i++) {
			costs[i] = in.nextInt();
		}

		int left = 1;
		int right = 1_000_000_000;
		while (left <= right) {
			int mid = (left + right) >> 1;
			int cnt = K;
			boolean possible = true;

			for (int i = 0; i < N; i++) {
				//기준값 아래인 경우
				if (costs[i] <= mid)
					cnt = K;
				else
					cnt--;

				if (cnt == 0) {
					possible = false;
					break;
				}
			}

			if (possible)
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