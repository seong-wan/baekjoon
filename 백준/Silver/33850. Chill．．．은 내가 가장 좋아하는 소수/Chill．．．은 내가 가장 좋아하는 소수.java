public class Main {
	static int n, a, b;
	static int[] dp;
	static int[][] input;
	static boolean[] isNotPrime = new boolean[200001];

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		n = in.nextInt();
		a = in.nextInt();
		b = in.nextInt();

		dp = new int[n + 1];
		input = new int[2][n + 1];

		primeCalc();

		for (int i = 1; i <= n; i++) {
			input[0][i] = in.nextInt();
		}

		for (int i = 1; i <= n; i++) {
			input[1][i] = in.nextInt();
		}

		dp[1] = isNotPrime[input[0][1] + input[1][1]] ? b : a;

		for (int i = 2; i <= n; i++) {
			int colSum = input[0][i] + input[1][i];
			int rowASum = input[0][i - 1] + input[0][i];
			int rowBSum = input[1][i - 1] + input[1][i];

			int colScore = isNotPrime[colSum] ? b : a;
			int rowScore = (isNotPrime[rowASum] ? b : a) + (isNotPrime[rowBSum] ? b : a);

			dp[i] = Math.max(dp[i - 1] + colScore, dp[i - 2] + rowScore);
		}

		System.out.print(dp[n]);
	}

	static void primeCalc() {
		isNotPrime[1] = true;

		for (int i = 2; i <= Math.sqrt(200000); i++) {
			if (isNotPrime[i])
				continue;

			for (int j = i * i; j <= 200000; j += i) {
				isNotPrime[j] = true;
			}
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