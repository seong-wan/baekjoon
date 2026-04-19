import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class Main {
	static int n, m, q;
	static List<Integer>[] adlist;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		n = in.nextInt();
		m = in.nextInt();

		adlist = new List[n + 1];
		for (int i = 1; i <= n; i++) {
			adlist[i] = new ArrayList<>();
		}

		for (int i = 0; i < m; i++) {
			int from = in.nextInt();
			int to = in.nextInt();

			adlist[from].add(to);
			adlist[to].add(from);
		}

		q = in.nextInt();

		for (int i = 0; i < q; i++) {
			int from = in.nextInt();
			int to = in.nextInt();

			adlist[from].add(to);
			adlist[to].add(from);

			bfs();
		}

		System.out.print(sb);
	}

	static void bfs() {
		Deque<Integer> queue = new ArrayDeque<>();
		int[] visit = new int[n + 1];
		Arrays.fill(visit, -1);
		int time = 0;

		visit[1] = time;
		queue.add(1);

		while (!queue.isEmpty()) {
			int size = queue.size();
			time++;
			for (int i = 0; i < size; i++) {
				int cur = queue.poll();

				for (Integer next : adlist[cur]) {
					if (visit[next] != -1)
						continue;

					queue.add(next);
					visit[next] = time;
				}
			}
		}

		for (int i = 1; i <= n; i++) {
			sb.append(visit[i]).append(" ");
		}
		sb.append("\n");
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
