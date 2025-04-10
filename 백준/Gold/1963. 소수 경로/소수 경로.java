import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
	static int T, from, to;
	static boolean[] notPrime = new boolean[10000];
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		initPrime();

		Reader in = new Reader();
		T = in.nextInt();

		for (int t = 0; t < T; t++) {
			from = in.nextInt();
			to = in.nextInt();

			int result = from == to ? 0 : bfs();
			sb.append(result == -1 ? "Impossible" : result).append("\n");
		}

		System.out.println(sb);
	}

	static int bfs() {
		Deque<Integer> queue = new ArrayDeque<>();
		int depth = 1;
		boolean[] visit = new boolean[10000];

		visit[from] = true;
		queue.add(from);

		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				int cur = queue.poll();

				//1000
				int a = (cur / 1000) * 1000;
				cur -= a;

				for (int j = 1; j < 10; j++) {
					int temp = j * 1000 + cur;

					if (temp == to)
						return depth;

					if (!notPrime[temp] && !visit[temp]) {
						visit[temp] = true;
						queue.add(temp);
					}
				}

				int b = (cur / 100) * 100;
				cur -= b;
				int c = (cur / 10) * 10;
				cur -= c;
				int d = cur;

				cur += a + c;
				for (int j = 0; j < 10; j++) {
					int temp = j * 100 + cur;

					if (temp == to)
						return depth;

					if (!notPrime[temp] && !visit[temp]) {
						visit[temp] = true;
						queue.add(temp);
					}
				}

				cur += b - c;
				for (int j = 0; j < 10; j++) {
					int temp = j * 10 + cur;

					if (temp == to)
						return depth;

					if (!notPrime[temp] && !visit[temp]) {
						visit[temp] = true;
						queue.add(temp);
					}
				}

				cur += c - d;
				for (int j = 0; j < 10; j++) {
					int temp = j + cur;

					if (temp == to)
						return depth;

					if (!notPrime[temp] && !visit[temp]) {
						visit[temp] = true;
						queue.add(temp);
					}
				}
			}
			depth++;
		}

		return -1;
	}

	static void initPrime() {
		for (int i = 2; i <= 100; i++) {
			if (notPrime[i])
				continue;

			int temp = 2 * i;
			while (temp < 10000) {
				notPrime[temp] = true;
				temp += i;
			}
		}
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