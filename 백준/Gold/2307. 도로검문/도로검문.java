import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
	static int N, M, originTime, ans;
	static List<int[]>[] adlist;
	static int[] visit;
	static List<int[]> route = new ArrayList<>();

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
			int time = in.nextInt();

			adlist[from].add(new int[] {to, time});
			adlist[to].add(new int[] {from, time});
		}

		originTime = dijk(0, 0);

		int temp = N;
		while (temp != 1) {
			route.add(new int[] {temp, visit[temp]});

			temp = visit[temp];
		}

		for (int[] block : route) {
			int afterBlockTime = dijk(block[0], block[1]);

			if (afterBlockTime == -1) {
				System.out.println(-1);
				return;
			}

			ans = Math.max(ans, afterBlockTime - originTime);
		}

		System.out.println(ans);
	}

	static int dijk(int blockA, int blockB) {
		visit = new int[N + 1];
		PriorityQueue<int[]> pq = new PriorityQueue<>((e1, e2) -> e1[2] - e2[2]);
		pq.add(new int[] {-1, 1, 0});

		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int pre = cur[0];
			int node = cur[1];
			int time = cur[2];

			if ((blockA == pre && blockB == node) || (blockA == node && blockB == pre))
				continue;

			if (visit[node] != 0)
				continue;

			visit[node] = pre;

			if (node == N)
				return time;

			for (int[] next : adlist[node]) {
				if (visit[next[0]] != 0)
					continue;

				pq.add(new int[] {node, next[0], time + next[1]});
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