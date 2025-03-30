import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
	static int C, P, start, goal1, goal2;
	static List<int[]>[] adlist;
	static boolean[] visit;

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();

		C = in.nextInt();
		P = in.nextInt();
		start = in.nextInt();
		goal1 = in.nextInt();
		goal2 = in.nextInt();

		adlist = new List[P + 1];
		visit = new boolean[P + 1];

		for (int i = 1; i <= P; i++) {
			adlist[i] = new ArrayList<>();
		}

		for (int i = 0; i < C; i++) {
			int from = in.nextInt();
			int to = in.nextInt();
			int dis = in.nextInt();

			adlist[from].add(new int[] {to, dis});
			adlist[to].add(new int[] {from, dis});
		}

		System.out.println(dijk());
	}

	static int dijk() {
		PriorityQueue<int[]> pq = new PriorityQueue<>((e1, e2) -> e1[1] - e2[1]);
		pq.add(new int[] {start, 0});

		boolean check = false;

		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int node = cur[0];
			int dis = cur[1];

			if (visit[node])
				continue;

			if (!check && (node == goal1 || node == goal2)) {
				visit = new boolean[P + 1];
				check = true;
				pq.clear();
				pq.add(cur);
			}

			visit[node] = true;

			if (visit[goal1] && visit[goal2])
				return dis;

			for (int[] next : adlist[node]) {
				if (visit[next[0]])
					continue;

				pq.add(new int[] {next[0], dis + next[1]});
			}
		}
		return -1;
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