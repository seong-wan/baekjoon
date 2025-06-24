import java.util.Arrays;

public class Main {
	static int N, M;
	static int[] dots;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		N = in.nextInt();
		M = in.nextInt();

		dots = new int[N];
		for (int i = 0; i < N; i++) {
			dots[i] = in.nextInt();
		}
		Arrays.sort(dots);

		for (int i = 0; i < M; i++) {
			int start = in.nextInt();
			int end = in.nextInt();

			double locS = Arrays.binarySearch(dots, start);
			double locE = Arrays.binarySearch(dots, end);

			if (locS >= 0)
				locS -= 1;
			else {
				if (locE >= 0)
					locS = locS * -1 - 2.5;
				else
					locS = locS * -1 - 1.5;
			}

			if (locE < 0)
				locE = locE * -1 - 1.5;

			sb.append((int)(locE - locS)).append("\n");
		}

		System.out.println(sb);
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