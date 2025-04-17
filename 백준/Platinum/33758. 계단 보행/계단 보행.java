import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
	static int N, M;
	static Map<Integer, List<Integer>>[] adlist;
	// static Set<Integer>[] visit;
	static int[] memoi;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();

		N = in.nextInt();
		M = in.nextInt();

		memoi = new int[N + 1];
		adlist = new Map[N + 1];
		// visit = new Set[N + 1];

		for (int i = 1; i <= N; i++) {
			adlist[i] = new HashMap<>();
			// visit[i] = new HashSet<>();
		}

		for (int i = 0; i < M; i++) {
			int from = in.nextInt();
			int to = in.nextInt();
			int num = in.nextInt();

			adlist[from].computeIfAbsent(num, k -> new ArrayList<>()).add(to);
			adlist[to].computeIfAbsent(num, k -> new ArrayList<>()).add(from);
		}

		bfs();

		for (int i = 1; i <= N; i++) {
			sb.append(memoi[i] == 0 ? -1 : memoi[i]).append(" ");
		}

		System.out.println(sb);
	}

	static void bfs() {
		Deque<int[]> queue = new ArrayDeque<>();

		for (Integer num : adlist[1].keySet()) {
			for (Integer to : adlist[1].get(num)) {
				queue.add(new int[] {to, num});

				memoi[to] = 1;
			}
		}

		adlist[1].clear();

		int depth = 2;

		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				int[] cur = queue.poll();
				int node = cur[0];
				int num = cur[1];

				if (adlist[node].containsKey(num - 1)) {
					for (Integer to : adlist[node].get(num - 1)) {
						if (memoi[to] == 0)
							memoi[to] = depth;

						queue.add(new int[] {to, num - 1});
					}

					adlist[node].remove(num - 1);
				}

				if (adlist[node].containsKey(num + 1)) {
					for (Integer to : adlist[node].get(num + 1)) {
						if (memoi[to] == 0)
							memoi[to] = depth;

						queue.add(new int[] {to, num + 1});
					}

					adlist[node].remove(num + 1);
				}
			}
			depth++;
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