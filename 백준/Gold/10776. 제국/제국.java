import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
	static int K, N, M, start, end;
	static List<int[]>[] adlist;
	static int[] check;

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();

		K = in.nextInt();
		N = in.nextInt();
		M = in.nextInt();

		adlist = new List[N + 1];
		for (int i = 1; i <= N; i++) {
			adlist[i] = new ArrayList<>();
		}

		check = new int[N + 1];
		Arrays.fill(check, K);

		for (int i = 0; i < M; i++) {
			int A = in.nextInt();
			int B = in.nextInt();
			int time = in.nextInt();
			int h = in.nextInt();

			adlist[A].add(new int[] {B, time, h});
			adlist[B].add(new int[] {A, time, h});
		}

		start = in.nextInt();
		end = in.nextInt();

		dijk();
	}

	static void dijk() {
		PriorityQueue<int[]> pq = new PriorityQueue<>((e1, e2) -> e1[1] == e2[1] ? e1[2] - e2[2] : e1[1] - e2[1]);
		pq.add(new int[] {start, 0, 0});

		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int node = cur[0];
			int time = cur[1];
			int h = cur[2];

			if (check[node] <= h)
				continue;

			if (node == end) {
				System.out.println(time);
				return;
			}

			check[node] = h;

			for (int[] next : adlist[node]) {
				int nTime = time + next[1];
				int nH = h + next[2];

				if (check[next[0]] <= nH)
					continue;

				pq.add(new int[] {next[0], nTime, nH});
			}
		}

		System.out.println(-1);
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