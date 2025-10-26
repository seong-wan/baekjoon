import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
	static int N, L;
	static int[] input = new int[5000000];
	static Deque<Integer> deque = new ArrayDeque<>();
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();

		N = in.nextInt();
		L = in.nextInt();

		for (int i = 0; i < N; i++) {
			input[i] = in.nextInt();

			//범위 밖의 인덱스 제거
			if (!deque.isEmpty() && deque.peek() == i - L)
				deque.poll();

			//범위 안의 최솟값이 아닌 인덱스 제거
			while (!deque.isEmpty() && input[deque.peekLast()] > input[i]) {
				deque.pollLast();
			}

			deque.add(i);

			sb.append(input[deque.peek()]).append(" ");
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