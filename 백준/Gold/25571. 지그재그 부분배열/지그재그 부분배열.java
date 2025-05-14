public class Main {
	static long[] sum = new long[100000];
	static int T, n;
	static StringBuilder sb = new StringBuilder();
	static int[] input;

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		init();

		T = in.nextInt();

		for (int t = 0; t < T; t++) {
			n = in.nextInt();

			input = new int[n];
			for (int i = 0; i < n; i++) {
				input[i] = in.nextInt();
			}

			int right = 1;
			long ans = 0;
			int status = 0; //0-중립, 1-i+1<i, 2-i+1>i
			int cur = 1;

			while (right < n) {
				if (input[right - 1] == input[right]) {
					ans += sum[cur - 1];
					cur = 1;
					status = 0;
				}
				//다음이 더 작은 상황
				else if (input[right] < input[right - 1]) {
					//지그재그인 상황
					if (status != 1) {
						cur++;
						status = 1;
					}
					//지그재그가 아닌 상황
					else {
						ans += sum[cur - 1];
						cur = 2;
					}
				}
				//다음이 더 큰 상황
				else {
					//지그재그인 상황
					if (status != 2) {
						cur++;
						status = 2;
					}
					//지그재그가 아닌 상황
					else {
						ans += sum[cur - 1];
						cur = 2;
					}
				}
				right++;
			}

			ans += sum[cur - 1];
			sb.append(ans).append("\n");
		}

		System.out.println(sb);
	}

	static void init() {
		for (int i = 1; i < 100000; i++) {
			sum[i] = i + sum[i - 1];
		}
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