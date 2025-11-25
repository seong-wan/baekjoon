import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class Main {
	static int N;
	static long base, wayCnt = 1, min, max;
	static Map<Integer, List<Integer>> diffs = new HashMap<>();
	static final int div = 998244353;
	static StringBuilder sb = new StringBuilder();
	static int[] way = new int[100000];

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		N = in.nextInt();

		for (int i = 1; i <= N; i++) {
			int c = in.nextInt();
			int a = in.nextInt();
			int diff = c - a;

			base += (long)a * (N - 1);
			if (!diffs.containsKey(diff))
				diffs.put(diff, new ArrayList<>());

			diffs.get(diff).add(i);
		}

		min = base;
		max = base;

		TreeSet<Integer> keys = new TreeSet<>(diffs.keySet());

		int minIdx = N - 1;
		int maxIdx = 0;
		for (Integer key : keys) {
			List<Integer> list = diffs.get(key);
			wayCnt = (wayCnt * fact(list.size())) % div;
			for (Integer i : list) {
				way[minIdx] = i;
				min += (long)key * minIdx--;
				max += (long)key * maxIdx++;
			}
		}

		sb.append(min).append(" ").append(wayCnt).append("\n");
		for (int i = 0; i < N; i++) {
			sb.append(way[i]).append(" ");
		}
		sb.append("\n").append(max).append(" ").append(wayCnt).append("\n");
		for (int i = N - 1; i >= 0; i--) {
			sb.append(way[i]).append(" ");
		}

		System.out.print(sb);
	}

	static long fact(int n) {
		long result = 1;
		for (int i = 2; i <= n; i++) {
			result = (result * i) % div;
		}
		return result;
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