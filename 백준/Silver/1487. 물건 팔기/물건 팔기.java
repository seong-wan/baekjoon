import java.util.Arrays;

public class Main {
	static int N, ans, max;
	static int[][] price; //가격, 배송비

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		N = in.nextInt();

		price = new int[N][2];
		int cnt = N;
		for (int i = 0; i < N; i++) {
			int p = in.nextInt();
			int d = in.nextInt();

			if (p - d <= 0) {
				cnt--;
				continue;
			}

			price[i][0] = p;
			price[i][1] = d;
		}

		Arrays.sort(price, (e1, e2) -> e2[0] - e1[0]);

		for (int i = 0; i < cnt; i++) {
			int tempP = price[i][0];
			int tempSum = 0;

			for (int j = 0; j <= i; j++) {
				tempSum += Math.max(tempP - price[j][1], 0);
			}

			if (tempSum >= max) {
				max = tempSum;
				ans = tempP;
			}
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