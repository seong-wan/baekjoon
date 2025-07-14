import java.util.HashMap;
import java.util.Map;

public class Main {
	static int N, S;
	static long cnt;
	static int[] input;
	static Map<Integer, Integer> map = new HashMap<>();

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		N = in.nextInt();
		S = in.nextInt();

		input = new int[N];
		for (int i = 0; i < N; i++) {
			input[i] = in.nextInt();
		}

		left_search(0, 0);
		right_search(N / 2, 0);

		System.out.println(S != 0 ? cnt : cnt - 1);
	}

	static void left_search(int idx, int partSum) {
		if (idx == N / 2) {
			map.putIfAbsent(partSum, 0);
			map.put(partSum, map.get(partSum) + 1);

			return;
		}

		left_search(idx + 1, partSum);
		left_search(idx + 1, partSum + input[idx]);
	}

	static void right_search(int idx, int partSum) {
		if (idx == N) {
			if (map.containsKey(S - partSum))
				cnt += map.get(S - partSum);
			return;
		}

		right_search(idx + 1, partSum);
		right_search(idx + 1, partSum + input[idx]);
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