import java.util.HashMap;
import java.util.Map;

public class Main {
	static int N, M;
	static long ans;
	static Map<Long, Integer>[] maps;
	static int[] inc;

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();

		N = in.nextInt();
		M = in.nextInt();

		maps = new Map[M];
		for (int i = 0; i < M; i++) {
			maps[i] = new HashMap<>();
		}

		inc = new int[M];
		for (int i = 0; i < M; i++) {
			inc[i] = in.nextInt();
		}

		for (int i = 0; i < N; i++) {
			int X = in.nextInt();
			int Y = in.nextInt();

			for (int j = 0; j < M; j++) {
				long temp = Y - (long)X * inc[j];

				if (!maps[j].containsKey(temp))
					maps[j].put(temp, 1);
				else
					maps[j].put(temp, maps[j].get(temp) + 1);
			}
		}

		for (int i = 0; i < M; i++) {
			for (Integer value : maps[i].values()) {
				ans += (long)value * (value - 1);
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