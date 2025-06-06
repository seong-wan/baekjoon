import java.util.HashMap;
import java.util.Map;

public class Main {
	static int N, K;
	static long ans;
	static Map<Long, Integer> count = new HashMap<>();

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		N = in.nextInt();
		K = in.nextInt();

		for (int i = 0; i < N; i++) {
			long x = in.nextInt();
			long y = in.nextInt();
			long intercept = y - K * x;

			if (count.containsKey(intercept))
				count.put(intercept, count.get(intercept) + 1);
			else
				count.put(intercept, 1);
		}

		for (Long i : count.keySet()) {
			long num = count.get(i);

			ans += num * (num - 1);
		}

		System.out.println(ans);
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