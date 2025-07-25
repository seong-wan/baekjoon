public class Main {
	static int N, M;
	static int[] input;

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		N = in.nextInt();
		M = in.nextInt();

		input = new int[N + 1];
		input[1] = in.nextInt();
		for (int i = 2; i <= N; i++) {
			input[i] = in.nextInt() + input[i - 1];
		}

		int left = 0;
		int right = 1;
		int ans = 0;

		while (right <= N) {
			if (left == right) {
				right++;
				continue;
			}

			int temp = input[right] - input[left];

			if (temp == M) {
				ans = M;
				break;
			} else if (temp < M) {
				ans = Math.max(ans, temp);
				right++;
			} else
				left++;
		}

		System.out.print(ans);
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