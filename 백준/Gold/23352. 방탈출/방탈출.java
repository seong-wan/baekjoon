import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
	static int N, M;
	static int maxLength, ans;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	static int[][] map;

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

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] != 0)
					bfs(i, j);
			}
		}

		System.out.print(ans);
	}

	static void bfs(int r, int c) {
		Deque<int[]> queue = new ArrayDeque<>();
		boolean[][] visit = new boolean[N][M];

		visit[r][c] = true;
		queue.add(new int[] {r, c});
		int length = 1;

		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				int[] cur = queue.poll();
				int cr = cur[0];
				int cc = cur[1];

				int sum = map[cr][cc] + map[r][c];

				if (length == maxLength)
					ans = Math.max(ans, sum);

				if (length > maxLength) {
					maxLength = length;
					ans = sum;
				}

				for (int dir = 0; dir < 4; dir++) {
					int nr = cr + dr[dir];
					int nc = cc + dc[dir];
					if (canGo(nr, nc) && !visit[nr][nc]) {
						visit[nr][nc] = true;
						queue.add(new int[] {nr, nc});
					}
				}
			}
			length++;
		}
	}

	static boolean canGo(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M && map[r][c] != 0;
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