import java.util.Arrays;

public class Main {
	static int N, M, ans;
	static int[] X, Y;

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		N = in.nextInt();
		M = in.nextInt();

		X = new int[M];
		Y = new int[M];

		for (int i = 0; i < M; i++) {
			X[i] = in.nextInt();
			Y[i] = in.nextInt();
		}

		Arrays.sort(X);
		Arrays.sort(Y);

		int focusX = X[(M + 1) / 2 - 1];
		int focusY = Y[(M + 1) / 2 - 1];

		for (int i = 0; i < M; i++) {
			ans += Math.abs(focusX - X[i]);
			ans += Math.abs(focusY - Y[i]);
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