import java.util.HashMap;
import java.util.Map;

public class Main {
	static int N;
	static Map<Integer, int[]> surfMap = new HashMap<>();
	static long[] dp;
	static int maxTime = 0;

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();

		N = in.nextInt();
		for (int i = 0; i < N; i++) {
			int m = in.nextInt();
			int f = in.nextInt();
			int w = in.nextInt();

			surfMap.put(m, new int[] {f, w});
			maxTime = Math.max(maxTime, m);
		}

		dp = new long[maxTime + 2];

		for (int i = maxTime; i >= 1; i--) {
			if (surfMap.containsKey(i)) {
				int[] surf = surfMap.get(i);
				dp[i] = Math.max(dp[i + 1], dp[Math.min(i + surf[1], maxTime + 1)] + surf[0]);
			} else {
				dp[i] = dp[i + 1];
			}
		}

		System.out.print(dp[1]);
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