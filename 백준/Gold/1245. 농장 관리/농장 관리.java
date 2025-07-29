import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
	static int N, M, ans;
	static int[][] map;
	static int[] dr = {-1, 0, 1, 1, 1, 0, -1, -1};
	static int[] dc = {-1, -1, -1, 0, 1, 1, 1, 0};

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();

		N = in.nextInt();
		M = in.nextInt();
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = in.nextInt();
			}
		}

		for (int i = 500; i >= 1; i--) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < M; k++) {
					if (map[j][k] == i) {
						ans++;
						bfs(j, k);
					}
				}
			}
		}

		System.out.print(ans);
	}

	static void bfs(int r, int c) {
		Deque<int[]> queue = new ArrayDeque<>();

		queue.add(new int[] {r, c, map[r][c]});
		map[r][c] = 0;

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int cr = cur[0];
			int cc = cur[1];
			int level = cur[2];

			for (int dir = 0; dir < 8; dir++) {
				int nr = cr + dr[dir];
				int nc = cc + dc[dir];

				if (canGo(nr, nc) && map[nr][nc] <= level) {
					queue.add(new int[] {nr, nc, map[nr][nc]});
					map[nr][nc] = 0;
				}
			}
		}
	}

	static boolean canGo(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M && map[r][c] != 0;
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