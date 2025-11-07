import java.util.TreeSet;

public class Main {
	static int n, k, a, m, totalCnt;
	static TreeSet<Integer> panels = new TreeSet<>();

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();

		n = in.nextInt();
		k = in.nextInt();
		a = in.nextInt();
		m = in.nextInt();

		totalCnt = ((n + 1) / (a + 1));
		panels.add(0);
		panels.add(n + 1);

		for (int t = 1; t <= m; t++) {
			int target = in.nextInt();
			int downPanel = panels.lower(target);
			int upPanel = panels.higher(target);

			totalCnt -= ((upPanel - downPanel) / (a + 1));
			totalCnt += ((target - downPanel) / (a + 1));
			totalCnt += ((upPanel - target) / (a + 1));

			panels.add(target);

			if (totalCnt < k) {
				System.out.println(t);
				return;
			}
		}

		System.out.print(-1);
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