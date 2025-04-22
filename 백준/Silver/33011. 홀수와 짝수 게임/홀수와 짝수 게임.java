public class Main {
	static int T, N;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();

		T = in.nextInt();
		for (int t = 0; t < T; t++) {
			N = in.nextInt();
			int oddCount = 0;
			int evenCount = 0;

			for (int i = 0; i < N; i++) {
				int temp = in.nextInt();
				if (temp % 2 == 0)
					evenCount++;
				else
					oddCount++;
			}

			if (evenCount == oddCount)
				sb.append("heeda0528").append("\n");
			else {
				int pickCount = Math.max(evenCount, oddCount);

				if (pickCount % 2 == 0)
					sb.append("heeda0528").append("\n");
				else
					sb.append("amsminn").append("\n");
			}
		}

		System.out.println(sb);
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
