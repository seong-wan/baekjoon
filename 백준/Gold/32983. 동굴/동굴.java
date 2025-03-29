import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
	static int N, M, C, ans;
	static int[] start = new int[2];
	static int[][] map;
	static boolean[][] visit;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		N = in.nextInt();
		M = in.nextInt();
		C = in.nextInt();

		start[0] = in.nextInt();
		start[1] = in.nextInt();

		map = new int[N + 1][M + 1];
		visit = new boolean[N + 1][M + 1];

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				map[i][j] = in.nextInt();
			}
		}

		bfs();

		System.out.println(ans);
	}

	static void bfs() {
		Deque<int[]> queue = new ArrayDeque<>();

		queue.add(start);
		visit[start[0]][start[1]] = true;
		int depth = 1;
		int loopy = map[start[0]][start[1]];
		ans = loopy;

		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				int[] cur = queue.poll();
				int cr = cur[0];
				int cc = cur[1];

				for (int dir = 0; dir < 4; dir++) {
					int nr = cr + dr[dir];
					int nc = cc + dc[dir];

					if (canGo(nr, nc)) {
						loopy += map[nr][nc];
						visit[nr][nc] = true;
						queue.add(new int[] {nr, nc});
					}
				}
			}
			ans = Math.max(ans, loopy - depth++ * C);
		}
	}

	static boolean canGo(int r, int c) {
		return r >= 1 && r <= N && c >= 1 && c <= M && !visit[r][c] && map[r][c] != -1;
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