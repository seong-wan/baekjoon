import java.util.ArrayList;
import java.util.List;

public class Main {
	static int t, n, K;
	static List<Integer> input = new ArrayList();
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		t = in.nextInt();

		for (int i = 0; i < t; i++) {
			n = in.nextInt();
			K = in.nextInt();

			for (int j = 0; j < n; j++) {
				input.add(in.nextInt());
			}

			input.sort((e1, e2) -> e1 - e2);

			int left = 0;
			int right = n - 1;
			int ans = 0;
			int diff = Integer.MAX_VALUE;

			while (left < right) {
				int sum = input.get(left) + input.get(right);
				int value = K - sum;
				int absValue = Math.abs(value);

				if (absValue == diff)
					ans++;

				if (absValue < diff) {
					diff = absValue;
					ans = 1;
				}

				if (value < 0)
					right--;
				else
					left++;
			}

			sb.append(ans).append("\n");
			input.clear();
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