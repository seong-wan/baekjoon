import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class Main {
	static int V, E, location;
	static List<int[]>[] adlist;
	static boolean[] visit;
	static int[] yaRoute = new int[10];
	static long[] dp;
	static long[] time = new long[10];
	static TreeSet<Integer> availLocation = new TreeSet<>();

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		V = in.nextInt();
		E = in.nextInt();

		adlist = new List[V + 1];
		dp = new long[V + 1];
		for (int i = 1; i <= V; i++) {
			adlist[i] = new ArrayList<>();
		}

		Arrays.fill(dp, Long.MAX_VALUE);

		for (int i = 0; i < E; i++) {
			int from = in.nextInt();
			int to = in.nextInt();
			int w = in.nextInt();

			adlist[from].add(new int[] {to, w});
			adlist[to].add(new int[] {from, w});
		}

		for (int i = 0; i < 10; i++) {
			yaRoute[i] = in.nextInt();
		}

		location = in.nextInt();

		int startPoint = 0;
		for (int i = 1; i < 10; i++) {
			visit = new boolean[V + 1];
			time[i] = dijk(yaRoute[startPoint], yaRoute[i]);

			if (time[i] != -1)
				startPoint = i;
		}

		startPoint = 0;

		for (int i = 1; i < 10; i++) {
			if (time[i] == -1)
				continue;

			time[i] += time[startPoint];
			startPoint = i;
		}

		search();

		for (int i = 0; i < 10; i++) {
			int temp = yaRoute[i];

			long myTime = dp[yaRoute[i]];
			long yaTime = time[i];

			if (myTime <= yaTime)
				availLocation.add(temp);
		}

		System.out.println(availLocation.isEmpty() ? -1 : availLocation.pollFirst());
	}

	static void search() {
		PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingLong(e -> e.w));
		pq.add(new Node(location, 0));

		while (!pq.isEmpty()) {
			Node cur = pq.poll();

			if (dp[cur.loc] != Long.MAX_VALUE)
				continue;

			dp[cur.loc] = cur.w;

			for (int[] next : adlist[cur.loc]) {
				if (dp[next[0]] != Long.MAX_VALUE)
					continue;

				pq.add(new Node(next[0], cur.w + next[1]));
			}
		}
	}

	static long dijk(int start, int end) {
		PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingLong(e -> e.w));
		pq.add(new Node(start, 0));

		while (!pq.isEmpty()) {
			Node cur = pq.poll();

			if (cur.loc == end)
				return cur.w;

			if (visit[cur.loc])
				continue;

			visit[cur.loc] = true;

			for (int[] next : adlist[cur.loc]) {
				if (visit[next[0]])
					continue;

				pq.add(new Node(next[0], cur.w + next[1]));
			}
		}
		return -1;
	}

	static class Node {
		int loc;
		long w;

		public Node(int loc, long w) {
			this.loc = loc;
			this.w = w;
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
