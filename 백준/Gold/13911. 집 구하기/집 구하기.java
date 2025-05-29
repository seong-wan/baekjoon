import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class Main {
	static int V, E, M, x, S, y, ans = Integer.MAX_VALUE;
	static List<int[]>[] adlist;
	static Set<Integer> macdonald = new HashSet<>();
	static Set<Integer> starbucks = new HashSet<>();
	static int[] disFromMac;
	static int[] disFromStar;

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		V = in.nextInt();
		E = in.nextInt();

		adlist = new List[V + 1];
		disFromMac = new int[V + 1];
		disFromStar = new int[V + 1];

		Arrays.fill(disFromMac, -1);
		Arrays.fill(disFromStar, -1);
		for (int i = 1; i <= V; i++) {
			adlist[i] = new ArrayList<>();
		}

		for (int i = 0; i < E; i++) {
			int from = in.nextInt();
			int to = in.nextInt();
			int w = in.nextInt();

			adlist[from].add(new int[] {to, w});
			adlist[to].add(new int[] {from, w});
		}

		M = in.nextInt();
		x = in.nextInt();

		for (int i = 0; i < M; i++) {
			macdonald.add(in.nextInt());
		}

		S = in.nextInt();
		y = in.nextInt();

		for (int i = 0; i < S; i++) {
			starbucks.add(in.nextInt());
		}

		dijk(macdonald, disFromMac);
		dijk(starbucks, disFromStar);

		for (int i = 1; i <= V; i++) {
			if (macdonald.contains(i) || starbucks.contains(i))
				continue;

			if (disFromMac[i] <= x && disFromStar[i] <= y)
				ans = Math.min(ans, disFromMac[i] + disFromStar[i]);
		}

		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
	}

	static void dijk(Set<Integer> startList, int[] distance) {
		PriorityQueue<int[]> pq = new PriorityQueue<>((e1, e2) -> e1[1] - e2[1]);
		for (Integer num : startList) {
			pq.add(new int[] {num, 0});
		}

		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int node = cur[0];
			int w = cur[1];

			if (distance[node] != -1)
				continue;

			distance[node] = w;

			for (int[] next : adlist[node]) {
				if (distance[next[0]] != -1)
					continue;

				pq.add(new int[] {next[0], w + next[1]});
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