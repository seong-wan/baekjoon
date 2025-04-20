import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Main {
	static int N, M, left = Integer.MAX_VALUE, right, start, end;
	static List<int[]>[] adlist;

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		N = in.nextInt();
		M = in.nextInt();

		adlist = new List[N + 1];
		for (int i = 1; i <= N; i++) {
			adlist[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			int from = in.nextInt();
			int to = in.nextInt();
			int c = in.nextInt();

			left = Math.min(c, left);
			right = Math.max(c, right);

			adlist[from].add(new int[] {to, c});
			adlist[to].add(new int[] {from, c});
		}

		start = in.nextInt();
		end = in.nextInt();

		while (left <= right) {
			int mid = (left + right) >> 1;
			boolean result = bfs(mid);

			if (result)
				left = mid + 1;
			else
				right = mid - 1;
		}

		System.out.println(right);
	}

	static boolean bfs(int weight) {
		Deque<Integer> queue = new ArrayDeque<>();
		boolean[] visit = new boolean[N + 1];

		visit[start] = true;
		queue.add(start);

		while (!queue.isEmpty()) {
			int cur = queue.poll();

			for (int[] next : adlist[cur]) {
				if (visit[next[0]] || next[1] < weight)
					continue;

				if (next[0] == end)
					return true;

				visit[next[0]] = true;
				queue.add(next[0]);
			}
		}

		return false;
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