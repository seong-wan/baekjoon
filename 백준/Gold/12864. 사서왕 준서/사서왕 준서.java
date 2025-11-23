public class Main {
	static int N, ans;

	static class Book {
		double num;
		int weight;

		Book() {
			this.num = 0;
			this.weight = 0;
		}

		Book(double num, int weight) {
			this.num = num;
			this.weight = weight;
		}
	}

	static Book[] books = new Book[5000];
	static int totalWeight, maxWeight;
	static int[] dp = new int[5000];

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		N = in.nextInt();

		for (int i = 0; i < N; i++) {
			Book book = new Book();
			book.num = in.nextDouble();
			books[i] = book;
		}

		for (int i = 0; i < N; i++) {
			books[i].weight = in.nextInt();
			totalWeight += books[i].weight;
		}

		for (int i = 0; i < N; i++) {
			dp[i] = books[i].weight;
			for (int j = 0; j < i; j++) {
				if (books[i].num >= books[j].num) {
					dp[i] = Math.max(dp[i], dp[j] + books[i].weight);
				}
			}

			maxWeight = Math.max(dp[i], maxWeight);
		}

		System.out.print(totalWeight - maxWeight);
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

		double nextDouble() throws Exception {
			double n = 0, div = 1;
			byte c;
			boolean isMinus = false;
			while ((c = read()) <= 32) {
				if (size < 0)
					return -12345;
			}
			if (c == 45) {
				c = read();
				isMinus = true;
			} else if (c == 46) {
				c = read();
			}
			do
				n = (n * 10) + (c & 15);
			while (isNumber(c = read()));
			if (c == 46) {
				while (isNumber(c = read())) {
					n += (c - 48) / (div *= 10);
				}
			}
			return isMinus ? -n : n;
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