import java.util.ArrayList;
import java.util.List;

public class Main {
	static int N, C, cnt;
	static int[] input;
	static List<Integer> sumList = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();

		N = in.nextInt();
		C = in.nextInt();

		input = new int[N];

		for (int i = 0; i < N; i++) {
			input[i] = in.nextInt();
		}

		leftSearch(0, 0);
		sumList.sort((e1, e2) -> e1 - e2);
		rightSearch(N / 2, 0);

		System.out.print(cnt);
	}

	static void leftSearch(int idx, int partSum) {
		if (partSum > C)
			return;

		if (idx == N / 2) {
			sumList.add(C - partSum);
			return;
		}

		leftSearch(idx + 1, partSum);
		leftSearch(idx + 1, partSum + input[idx]);
	}

	static void rightSearch(int idx, int partSum) {
		if (partSum > C)
			return;

		if (idx == N) {
			search(partSum);
			return;
		}

		rightSearch(idx + 1, partSum);
		rightSearch(idx + 1, partSum + input[idx]);
	}

	static void search(int partSum) {
		int left = 0;
		int right = sumList.size() - 1;

		while (left <= right) {
			int mid = (left + right) >> 1;

			if (sumList.get(mid) >= partSum)
				right = mid - 1;
			else
				left = mid + 1;
		}

		cnt += sumList.size() - left;
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