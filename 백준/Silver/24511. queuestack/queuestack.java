import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
	static Deque<Integer> queue = new ArrayDeque<>();
	static int[] check;
	static int N, M;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();

		N = in.nextInt();

		check = new int[N];
		for (int i = 0; i < N; i++) {
			check[i] = in.nextInt();
		}

		for (int i = 0; i < N; i++) {
			int temp = in.nextInt();

			if (check[i] == 0)
				queue.addFirst(temp);
		}

		M = in.nextInt();

		for (int i = 0; i < M; i++) {
			queue.add(in.nextInt());
			sb.append(queue.poll()).append(" ");
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