import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
	static int X, Y, N;
	static final int base = 500;
	static int[][] map = new int[1001][1001];
	static boolean[][] visit = new boolean[1001][1001];
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();

		X = in.nextInt() + base;
		Y = in.nextInt() + base;
		N = in.nextInt();

		for (int i = 0; i < N; i++) {
			int x = in.nextInt() + base;
			int y = in.nextInt() + base;

			map[x][y] = 1;
		}

		System.out.print(bfs());
	}

	static int bfs() {
		Deque<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] {base, base});
		visit[base][base] = true;

		int time = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			time++;

			for (int i = 0; i < size; i++) {
				int[] cur = queue.poll();
				int cx = cur[0];
				int cy = cur[1];

				for (int dir = 0; dir < 4; dir++) {
					int nx = cx + dx[dir];
					int ny = cy + dy[dir];

					if (nx == X && ny == Y) {
						return time;
					}

					if (canGo(nx, ny)) {
						visit[nx][ny] = true;
						queue.add(new int[] {nx, ny});
					}
				}
			}
		}

		return -1;
	}

	static boolean canGo(int x, int y) {
		return x >= 0 && x <= 1000 && y >= 0 && y <= 1000 && !visit[x][y] && map[x][y] == 0;
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