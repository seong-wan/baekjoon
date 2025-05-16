import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

public class Main {
	static int N, M, ans;
	static PriorityQueue<Integer> pq = new PriorityQueue<>();
	static Deque<Integer> queue = new ArrayDeque<>();

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		N = in.nextInt();
		M = in.nextInt();

		for (int i = 0; i < N; i++) {
			int length = in.nextInt();

			if (length < 10)
				continue;

			if (length == 10)
				ans++;
			else if (length % 10 == 0)
				pq.add(length);
			else
				queue.add(length);
		}

		while (!pq.isEmpty() && M != 0) {
			int temp = pq.poll();

			int cutCount = (temp / 10) - 1;

			if (M >= cutCount) {
				ans += cutCount + 1;
				M -= cutCount;
			} else {
				ans += M;
				M = 0;
			}
		}

		while (!queue.isEmpty() && M != 0) {
			int temp = queue.poll();

			int cutCount = (temp / 10);

			if (M >= cutCount) {
				ans += cutCount;
				M -= cutCount;
			} else {
				ans += M;
				M = 0;
			}
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