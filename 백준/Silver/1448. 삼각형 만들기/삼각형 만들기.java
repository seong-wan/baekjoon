import java.util.Arrays;

public class Main {
	static int N, ans = -1;
	static int[] lines;

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();

		N = in.nextInt();

		lines = new int[N];
		for (int i = 0; i < N; i++) {
			lines[i] = in.nextInt();
		}

		Arrays.sort(lines);

		for (int i = N - 1; i >= 2; i--) {
			if (lines[i] < lines[i - 1] + lines[i - 2]) {
				ans = lines[i] + lines[i - 1] + lines[i - 2];
				break;
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