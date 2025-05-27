import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class Main {
	static int N, W;
	static double M;
	static int[][] plants;
	static List<Node>[] adlist;
	static Set<Integer>[] connectedLine;

	static class Node {
		int to;
		double dis;

		public Node(int to, double dis) {
			this.to = to;
			this.dis = dis;
		}
	}

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();

		N = in.nextInt();
		W = in.nextInt();
		M = in.nextDouble();

		plants = new int[N + 1][2];
		adlist = new List[N + 1];
		connectedLine = new Set[N + 1];

		for (int i = 1; i <= N; i++) {
			adlist[i] = new ArrayList<>();
			connectedLine[i] = new HashSet<>();
		}

		for (int i = 1; i <= N; i++) {
			plants[i][0] = in.nextInt();
			plants[i][1] = in.nextInt();
		}

		for (int i = 0; i < W; i++) {
			int from = in.nextInt();
			int to = in.nextInt();

			connectedLine[from].add(to);
			adlist[from].add(new Node(to, 0));
			adlist[to].add(new Node(from, 0));
		}

		for (int i = 1; i <= N; i++) {
			for (int j = i + 1; j <= N; j++) {
				if (connectedLine[i].contains(j))
					continue;

				double dx = plants[i][0] - plants[j][0];
				double dy = plants[i][1] - plants[j][1];

				double dis = Math.sqrt(dx * dx + dy * dy);

				if (dis > M)
					continue;

				adlist[i].add(new Node(j, dis));
				adlist[j].add(new Node(i, dis));
			}
		}

		System.out.println(dijk());
	}

	static int dijk() {
		PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingDouble(e -> e.dis));
		boolean[] visit = new boolean[N + 1];

		pq.add(new Node(1, 0));

		while (!pq.isEmpty()) {
			Node cur = pq.poll();

			if (cur.to == N) {
				return (int)(cur.dis * 1000);
			}

			if (visit[cur.to])
				continue;

			visit[cur.to] = true;

			for (Node next : adlist[cur.to]) {
				if (visit[next.to])
					continue;

				pq.add(new Node(next.to, cur.dis + next.dis));
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

		double nextDouble() throws Exception {
			double n = 0, div = 1;
			byte c;
			boolean isMinus = false;
			while ((c = read()) <= 32) {
				if (size < 0)
					return -12345;
			}
			if (c == 45) {
				c = read();
				isMinus = true;
			} else if (c == 46) {
				c = read();
			}
			do
				n = (n * 10) + (c & 15);
			while (isNumber(c = read()));
			if (c == 46) {
				while (isNumber(c = read())) {
					n += (c - 48) / (div *= 10);
				}
			}
			return isMinus ? -n : n;
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