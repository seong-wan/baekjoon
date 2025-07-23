import java.util.HashMap;
import java.util.Map;

public class Main {
	static int N, K;
	static int[] input;
	static Map<Integer, Integer> map = new HashMap<>();
	static boolean isAns;

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		N = in.nextInt();

		input = new int[N];
		for (int i = 0; i < N; i++) {
			input[i] = in.nextInt();
		}

		K = in.nextInt();

		leftSearch(0, 0, 0);
		rightSearch(N / 2, 0, 0);
	}

	static void leftSearch(int idx, int mask, int partSum) {
		if (idx == N / 2) {
			//같은 합인 경우 1개만 저장
			if (!map.containsKey(partSum))
				map.put(partSum, mask);
			return;
		}

		leftSearch(idx + 1, mask, partSum);
		leftSearch(idx + 1, (mask | (1 << N / 2 - 1 - idx)), partSum + input[idx]);
	}

	static void rightSearch(int idx, int mask, int partSum) {
		//이미 답이 나온 경우
		if (isAns)
			return;

		if (idx == N) {
			if (map.containsKey(K - partSum)) {
				String front = String.format("%" + N / 2 + "s", Integer.toBinaryString(map.get(K - partSum)))
					.replace(' ', '0');

				String back = String.format("%" + (N - N / 2) + "s", Integer.toBinaryString(mask))
					.replace(' ', '0');

				System.out.print(front + back);
				isAns = true;
			}
			return;
		}

		rightSearch(idx + 1, mask, partSum);
		rightSearch(idx + 1, (mask | (1 << N - 1 - idx)), partSum + input[idx]);
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