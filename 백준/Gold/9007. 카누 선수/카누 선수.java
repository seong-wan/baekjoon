import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
	static int T, k, n;
	static int[][] input;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		T = in.nextInt();

		for (int t = 0; t < T; t++) {
			k = in.nextInt();
			n = in.nextInt();
			Set<Integer> partSum = new HashSet<>();

			int diff = 1_000_000_000;

			input = new int[4][n];
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < n; j++) {
					input[i][j] = in.nextInt();
				}
			}

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					partSum.add(input[0][i] + input[1][j]);
				}
			}

			List<Integer> partSumList = new ArrayList<>(partSum);
			partSumList.sort((e1, e2) -> e1 - e2);

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					int sum = input[2][i] + input[3][j];

					int left = 0;
					int right = partSumList.size() - 1;
					while (left <= right) {
						int mid = (left + right) >> 1;

						if (sum + partSumList.get(mid) > k)
							right = mid - 1;
						else if (sum + partSumList.get(mid) < k)
							left = mid + 1;
						else {
							diff = 0;
							break;
						}
					}

					//작은 쪽
					int rightDiff = right >= 0 ? sum + partSumList.get(right) - k : -1_000_000_000;
					//큰 쪽
					int leftDiff = left < partSumList.size() ? sum + partSumList.get(left) - k : 1_000_000_000;

					int bestDiff = 0;

					if (Math.abs(rightDiff) < Math.abs(leftDiff))
						bestDiff = rightDiff;
					else if (Math.abs(rightDiff) > Math.abs(leftDiff))
						bestDiff = leftDiff;
					else {
						if (rightDiff < 0)
							bestDiff = rightDiff;
						else
							bestDiff = leftDiff;
					}

					if (Math.abs(bestDiff) < Math.abs(diff))
						diff = bestDiff;
					else if (Math.abs(bestDiff) == Math.abs(diff)) {
						if (bestDiff < 0)
							diff = bestDiff;
					}

					if (diff == 0)
						break;
				}
				if (diff == 0)
					break;
			}

			sb.append(k + diff).append("\n");
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