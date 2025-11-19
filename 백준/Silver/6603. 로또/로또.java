public class Main {
	static int N;
	static int[] tgt = new int[6];
	static int[] arr = new int[13];
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		while (true) {
			N = in.nextInt();
			if (N == 0)
				break;

			for (int i = 0; i < N; i++) {
				arr[i] = in.nextInt();
			}

			comb(0, 0);
			sb.append("\n");
		}

		System.out.print(sb);
	}

	static void comb(int idx, int cnt) {
		if (cnt == 6) {
			print();
			return;
		}

		if (idx == N)
			return;

		tgt[cnt] = arr[idx];
		comb(idx + 1, cnt + 1);
		comb(idx + 1, cnt);
	}

	static void print() {
		for (int i : tgt) {
			sb.append(i).append(" ");
		}
		sb.append("\n");
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