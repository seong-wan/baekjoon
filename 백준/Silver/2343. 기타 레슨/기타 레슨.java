public class Main {
	static int N, M;
	static int[] input;

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		N = in.nextInt();
		M = in.nextInt();

		input = new int[N];
		for (int i = 0; i < N; i++) {
			input[i] = in.nextInt();
		}

		System.out.println(search());
	}

	static int search() {
		int left = 0;
		int right = 1_000_000_000;

		while (left <= right) {
			int mid = (left + right) >> 1;

			int temp = 1;
			int cSize = 0;
			for (int i = 0; i < N; i++) {
				//모든 강의를 담을 수 없는 경우 크기를 크게 만든다.
				if (input[i] > mid) {
					temp = 1000000;
					break;
				}

				if (temp > M)
					break;

				if (input[i] + cSize > mid) {
					temp++;
					cSize = input[i];
				} else
					cSize += input[i];
			}

			if (temp <= M) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
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