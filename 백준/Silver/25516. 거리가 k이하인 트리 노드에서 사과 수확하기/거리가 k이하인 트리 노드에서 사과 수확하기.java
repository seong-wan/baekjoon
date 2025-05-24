import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Main {
	static int n, k;
	static boolean[] apple;
	static List<Integer>[] tree;

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();

		n = in.nextInt();
		k = in.nextInt();

		apple = new boolean[n];
		tree = new List[n];
		for (int i = 0; i < n; i++) {
			tree[i] = new ArrayList<>();
		}

		for (int i = 0; i < n - 1; i++) {
			int p = in.nextInt();
			int c = in.nextInt();

			tree[p].add(c);
		}

		for (int i = 0; i < n; i++) {
			if (in.nextInt() == 1)
				apple[i] = true;
		}

		System.out.println(bfs());
	}

	static int bfs() {
		Deque<Integer> queue = new ArrayDeque<>();
		int ans = 0;
		if (apple[0])
			ans++;

		queue.add(0);
		int depth = 1;

		while (!queue.isEmpty()) {
			if (depth > k)
				break;

			int size = queue.size();
			for (int i = 0; i < size; i++) {
				int cur = queue.poll();

				for (Integer next : tree[cur]) {
					if (apple[next])
						ans++;

					queue.add(next);
				}
			}
			depth++;
		}

		return ans;
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