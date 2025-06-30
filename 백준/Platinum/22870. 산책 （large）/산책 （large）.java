import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class Main {
	static int N, M, S, E, ans;
	static List<int[]>[] adlist;
	static int[] chk;

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();

		N = in.nextInt();
		M = in.nextInt();

		adlist = new List[N + 1];
		chk = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			adlist[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			int from = in.nextInt();
			int to = in.nextInt();
			int c = in.nextInt();

			adlist[from].add(new int[] {to, c});
			adlist[to].add(new int[] {from, c});
		}

		S = in.nextInt();
		E = in.nextInt();

		dijk();
		removeVisit();
		dijk();

		System.out.println(ans);
	}

	static void removeVisit() {
		Set<Integer> set = new HashSet<>();
		int temp = E;

		while (chk[temp] != S) {
			set.add(chk[temp]);
			temp = chk[temp];
		}

		chk = new int[N + 1];
		for (Integer i : set) {
			chk[i] = -1;
		}
	}

	static void dijk() {
		PriorityQueue<int[]> pq = new PriorityQueue<>((e1, e2) -> e1[2] == e2[2] ? e1[1] - e2[1] : e1[2] - e2[2]);
		chk[S] = -1;
		for (int[] next : adlist[S]) {
			if (chk[next[0]] != 0)
				continue;

			pq.add(new int[] {S, next[0], next[1]});
		}

		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int pre = cur[0];
			int node = cur[1];
			int c = cur[2];

			if (chk[node] != 0)
				continue;

			chk[node] = pre;

			if (node == E) {
				ans += c;
				return;
			}

			for (int[] next : adlist[node]) {
				if (chk[next[0]] != 0)
					continue;

				pq.add(new int[] {node, next[0], c + next[1]});
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