public class Main {
	static int[][] board = new int[19][19];
	static boolean[][][] visited = new boolean[19][19][4];
	static int[] dr = {-1, 0, 1, 1};
	static int[] dc = {1, 1, 1, 0};
	static boolean isFinished = false;

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();

		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {
				board[i][j] = in.nextInt();
			}
		}

		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {
				if (board[j][i] == 0)
					continue;

				for (int dir = 0; dir < 4; dir++) {
					dfs(j, i, board[j][i], dir, 1);

					if (isFinished) {
						System.out.println(board[j][i]);
						System.out.print((j + 1) + " " + (i + 1));
						return;
					}
				}
			}
		}

		System.out.print(0);
	}

	static void dfs(int r, int c, int color, int dir, int cnt) {
		if (visited[r][c][dir])
			return;

		visited[r][c][dir] = true;
		boolean isEnd = true;

		int nr = r + dr[dir];
		int nc = c + dc[dir];

		if (canGo(nr, nc) && board[nr][nc] == color) {
			isEnd = false;
			dfs(nr, nc, color, dir, cnt + 1);
		}

		if (cnt == 5 && isEnd)
			isFinished = true;
	}

	static boolean canGo(int r, int c) {
		return r >= 0 && r < 19 && c >= 0 && c < 19;
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