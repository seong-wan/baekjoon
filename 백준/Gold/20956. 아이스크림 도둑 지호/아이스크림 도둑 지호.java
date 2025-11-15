import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class Main {
	static int N, M;
	static Map<Integer, Deque<Integer>> idxs = new HashMap<>(100000);
	static StringBuilder sb = new StringBuilder();
	static int[] icecream;
	static boolean isReverse;

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		N = in.nextInt();
		M = in.nextInt();

		icecream = new int[N];
		for (int i = 0; i < N; i++) {
			icecream[i] = in.nextInt();
			if (!idxs.containsKey(icecream[i]))
				idxs.put(icecream[i], new ArrayDeque<>());

			idxs.get(icecream[i]).add(i);
		}

		Arrays.sort(icecream);

		for (int i = 0; i < M; i++) {
			int eatAmount = icecream[N - 1 - i];

			if (isReverse)
				sb.append(idxs.get(eatAmount).pollLast() + 1);
			else
				sb.append(idxs.get(eatAmount).poll() + 1);

			if (eatAmount % 7 == 0)
				isReverse = !isReverse;

			sb.append("\n");
		}

		System.out.print(sb);
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