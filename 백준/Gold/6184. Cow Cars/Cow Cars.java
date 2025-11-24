import java.util.Arrays;

public class Main {
	static int N, M, D, L, ans;
	static final int MAX_SPEED = 1_000_000;
	static int[] cow;

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		N = in.nextInt();
		M = in.nextInt();
		D = in.nextInt();
		L = in.nextInt();

		cow = new int[N];
		for (int i = 0; i < N; i++) {
			cow[i] = in.nextInt();
		}

		Arrays.sort(cow);

		for (int i = 0; i < N; i++) {
			if (cow[i] < L)
				continue;

			ans += Math.min(M, N - i);
			i = i + M - 1;
			L += D;

			if (L > MAX_SPEED)
				break;
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