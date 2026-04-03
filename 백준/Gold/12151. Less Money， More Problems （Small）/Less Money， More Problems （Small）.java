public class Main {
	static int T;
	static StringBuilder sb = new StringBuilder();
	static boolean[] check;
	static int[] input;

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();

		T = in.nextInt();
		for (int t = 1; t <= T; t++) {
			int C = in.nextInt(); //무조건 1 안써도 되는 값
			int D = in.nextInt();
			int V = in.nextInt();
			int ans = 0;

			check = new boolean[V + 1];

			input = new int[D];
			for (int i = 0; i < D; i++) {
				input[i] = in.nextInt();
			}

			subset(input[0], 1);
			subset(0, 1);

			for (int i = 1; i <= V; i++) {
				if (check[i])
					continue;

				ans++;
				for (int j = V - i; j >= 0; j--) {
					if (check[j])
						check[i + j] = true;
				}
			}

			sb.append("Case #").append(t).append(": ").append(ans).append("\n");
		}

		System.out.print(sb);
	}

	static void subset(int sum, int idx) {
		if (idx == input.length) {
			if (sum < check.length)
				check[sum] = true;
			return;
		}

		subset(sum + input[idx], idx + 1);
		subset(sum, idx + 1);
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