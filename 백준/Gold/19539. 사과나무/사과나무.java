public class Main {
	static int N;
	static int[] soil;

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();

		N = in.nextInt();
		soil = new int[N];

		int sum = 0;
		for (int i = 0; i < N; i++) {
			soil[i] = in.nextInt();
			sum += soil[i];
		}

		//전체가 3의 배수가 아니면 불가능
		if (sum % 3 != 0) {
			System.out.print("NO");
			return;
		}

		int twoCount = 0;
		int oneCount = 0;

		for (int i = 0; i < N; i++) {
			twoCount += soil[i] / 2;
			oneCount += soil[i] % 2;
		}

		System.out.print(twoCount >= oneCount ? "YES" : "NO");
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