import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
	static Deque<long[]> deque = new ArrayDeque<>();
	static int N, D;
	static long ans;

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		N = in.nextInt();
		D = in.nextInt();

		deque.add(new long[] {0, in.nextInt()});
		ans = deque.peek()[1];

		for (int i = 1; i < N; i++) {
			int temp = in.nextInt();
			long maxSum = temp + deque.peek()[1];
			maxSum = Math.max(maxSum, temp);

			//범위 밖 제거
			if (deque.peek()[0] + D == i)
				deque.poll();

			//최댓값이 아닌 값 제거
			while (!deque.isEmpty() && deque.peekLast()[1] <= maxSum) {
				deque.pollLast();
			}

			deque.add(new long[] {i, maxSum});
			ans = Math.max(ans, deque.peek()[1]);
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