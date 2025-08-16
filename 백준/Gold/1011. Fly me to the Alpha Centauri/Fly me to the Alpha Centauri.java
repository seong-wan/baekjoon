public class Main {
	static int T;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();

		T = in.nextInt();
		for (int t = 0; t < T; t++) {
			int x = in.nextInt();
			int y = in.nextInt();

			sb.append(calc(y - x)).append("\n");
		}

		System.out.print(sb);
	}

	static int calc(int dist) {
		long left = 1;
		long right = 70000;

		while (left <= right) {
			long v = (left + right) / 2;
			long movedDist = (v + 1) * v;

			if (movedDist >= dist)
				right = v - 1;
			else
				left = v + 1;
		}

		long result = (left + 1) * left;

		if (result - left >= dist)
			return (int)(2 * left - 1);

		return (int)(2 * left);
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