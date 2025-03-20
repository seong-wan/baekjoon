import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
	static int N, M, K, T, Q;
	static int[][] map;
	static int[][][] teleport;
	static boolean[][][] visit;
	static int[][] memo;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		N = in.nextInt();
		M = in.nextInt();
		K = in.nextInt();
		T = in.nextInt();
		Q = in.nextInt();

		map = new int[N + 1][M + 1];
		teleport = new int[N + 1][M + 1][2];
		memo = new int[N + 1][M + 1];
		visit = new boolean[N + 1][M + 1][T + 1];

		for (int i = 1; i <= N; i++) {
			Arrays.fill(memo[i], Integer.MAX_VALUE);
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				map[i][j] = in.nextInt();
			}
		}

		for (int i = 0; i < K; i++) {
			int r = in.nextInt();
			int c = in.nextInt();
			int nr = in.nextInt();
			int nc = in.nextInt();

			teleport[r][c][0] = nr;
			teleport[r][c][1] = nc;
		}

		dijk();

		for (int i = 0; i < Q; i++) {
			int p = in.nextInt();
			int endR = in.nextInt();
			int endC = in.nextInt();

			if (memo[endR][endC] <= p)
				sb.append(1).append("\n");
			else
				sb.append(0).append("\n");
		}

		System.out.println(sb);
	}

	static void dijk() {
		PriorityQueue<int[]> pq = new PriorityQueue<>((e1, e2) -> e1[2] - e2[2]);
		pq.add(new int[] {1, 1, 0, T});

		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int cr = cur[0];
			int cc = cur[1];
			int p = cur[2];
			int availT = cur[3];

			if (visit[cr][cc][availT])
				continue;

			visit[cr][cc][availT] = true;

			memo[cr][cc] = Math.min(memo[cr][cc], p);

			for (int dir = 0; dir < 4; dir++) {
				int nr = cr + dr[dir];
				int nc = cc + dc[dir];

				if (canGo(nr, nc) && !visit[nr][nc][availT]) {
					pq.add(new int[] {nr, nc, p + map[nr][nc], availT});
				}
			}

			//텔레포트가 가능한 지점일 때
			if (teleport[cr][cc][0] != 0 && availT > 0) {
				pq.add(new int[] {teleport[cr][cc][0], teleport[cr][cc][1], p, availT - 1});
			}
		}
	}

	static boolean canGo(int r, int c) {
		return r >= 1 && r <= N && c >= 1 && c <= M;
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
