import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
	static int N, Q, size, sumIce, maxIceSize;
	static int[][] map = new int[64][64];
	static boolean[][] visited = new boolean[64][64];
	static Deque<int[]> queue = new ArrayDeque<>();
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		N = in.nextInt();
		Q = in.nextInt();

		size = 1 << N;

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				map[i][j] = in.nextInt();
			}
		}

		for (int i = 0; i < Q; i++) {
			int query = in.nextInt();
			int querySize = 1 << query;

			for (int j = 0; j < size; j += querySize) {
				for (int k = 0; k < size; k += querySize) {
					rotate(j, k, querySize);
				}
			}

			removeIce();
		}

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (map[i][j] == 0 || visited[i][j])
					continue;

				bfs(i, j);
			}
		}

		System.out.println(sumIce);
		System.out.print(maxIceSize);

		// for (int i = 0; i < size; i++) {
		// 	for (int j = 0; j < size; j++) {
		// 		System.out.print(map[i][j] + " ");
		// 	}
		// 	System.out.println();
		// }
	}

	static void bfs(int r, int c) {
		queue.add(new int[] {r, c});
		visited[r][c] = true;
		sumIce += map[r][c];
		int size = 1;

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int cr = cur[0];
			int cc = cur[1];

			for (int dir = 0; dir < 4; dir++) {
				int nr = cr + dr[dir];
				int nc = cc + dc[dir];

				if (canGo(nr, nc) && map[nr][nc] > 0 && !visited[nr][nc]) {
					sumIce += map[nr][nc];
					size++;
					visited[nr][nc] = true;
					queue.add(new int[] {nr, nc});
				}
			}
		}

		maxIceSize = Math.max(maxIceSize, size);
	}

	static void removeIce() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (map[i][j] == 0)
					continue;

				int cnt = 0;
				for (int dir = 0; dir < 4; dir++) {
					int nr = i + dr[dir];
					int nc = j + dc[dir];

					if (canGo(nr, nc) && map[nr][nc] > 0)
						cnt++;
				}

				if (cnt < 3)
					queue.add(new int[] {i, j});
			}
		}

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			map[cur[0]][cur[1]]--;
		}
	}

	static boolean canGo(int r, int c) {
		return r >= 0 && r < size && c >= 0 && c < size;
	}

	static void rotate(int startR, int startC, int querySize) {
		if (querySize <= 1)
			return;

		int endR = startR + querySize - 1;
		int endC = startC + querySize - 1;

		int[] temp = new int[querySize - 1];
		for (int i = 0; i < querySize - 1; i++) {
			temp[i] = map[startR][startC + i];
		}

		for (int i = 0; i < querySize - 1; i++) {
			map[startR][startC + i] = map[endR - i][startC];
		}

		for (int i = 1; i < querySize; i++) {
			map[startR + i][startC] = map[endR][startC + i];
		}

		for (int i = 1; i < querySize; i++) {
			map[endR][startC + i] = map[endR - i][endC];
		}

		for (int i = 0; i < querySize - 1; i++) {
			map[startR + i][endC] = temp[i];
		}

		rotate(startR + 1, startC + 1, querySize - 2);
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