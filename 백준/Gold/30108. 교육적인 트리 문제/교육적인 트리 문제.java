import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
	static int N;
	static List<Integer>[] tree;
	static int[] values;
	static long[] maxValues;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();

		N = in.nextInt();

		values = new int[N + 1];
		maxValues = new long[N + 1];
		tree = new List[N + 1];
		for (int i = 1; i <= N; i++) {
			tree[i] = new ArrayList<>();
		}

		for (int i = 2; i <= N; i++) {
			int parent = in.nextInt();

			tree[parent].add(i);
		}

		for (int i = 1; i <= N; i++) {
			values[i] = in.nextInt();
		}

		search();

		for (int i = 1; i <= N; i++) {
			sb.append(maxValues[i]).append("\n");
		}

		System.out.print(sb);
	}

	static void search() {
		PriorityQueue<int[]> pq = new PriorityQueue<>((e1, e2) -> e2[1] - e1[1]);
		pq.add(new int[] {1, values[1]});
		int cnt = 0;

		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int node = cur[0];
			int value = cur[1];
			cnt++;

			maxValues[cnt] = maxValues[cnt - 1] + value;

			for (Integer next : tree[node]) {
				pq.add(new int[] {next, values[next]});
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