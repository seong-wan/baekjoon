import java.util.PriorityQueue;

public class Main {
	static int N, K;
	static int[][] T;
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		N = in.nextInt();
		K = in.nextInt();

		T = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				T[i][j] = in.nextInt();
			}
		}

		visited = new boolean[N][1 << N];

		dijk();
	}

	static void dijk() {
		//현재 노드, 누적 시간, 방문 마스크
		PriorityQueue<int[]> pq = new PriorityQueue<>((e1, e2) -> e1[1] - e2[1]);
		pq.add(new int[] {K, 0, 1 << K});

		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int node = cur[0];
			int time = cur[1];
			int mask = cur[2];

			if (visited[node][mask])
				continue;

			visited[node][mask] = true;

			if (mask == (1 << N) - 1) {
				System.out.print(time);
				return;
			}

			for (int i = 0; i < N; i++) {
				if (i == node)
					continue;

				int nextTime = time + T[node][i];
				int nextMask = mask | (1 << i);

				if (visited[i][nextMask])
					continue;

				pq.add(new int[] {i, nextTime, nextMask});
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