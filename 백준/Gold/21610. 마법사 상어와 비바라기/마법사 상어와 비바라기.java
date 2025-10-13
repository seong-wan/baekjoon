import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
	static int N, M;
	static int[][] map = new int[51][51];
	static boolean[][] clouded = new boolean[51][51];
	static int[] dr = {0, 0, -1, -1, -1, 0, 1, 1, 1};
	static int[] dc = {0, -1, -1, 0, 1, 1, 1, 0, -1};
	static Deque<int[]> clouds = new ArrayDeque<>();

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();

		N = in.nextInt();
		M = in.nextInt();

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				map[i][j] = in.nextInt();
			}
		}

		clouds.add(new int[] {N, 1});
		clouds.add(new int[] {N, 2});
		clouds.add(new int[] {N - 1, 1});
		clouds.add(new int[] {N - 1, 2});

		for (int i = 0; i < M; i++) {
			int d = in.nextInt();
			int s = in.nextInt();

			moveClouds(d, s);
			waterCopy();
			makeClouds();
		}

		int ans = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				ans += map[i][j];
			}
		}

		System.out.print(ans);
	}

	//구름 이동 후 물 증가 처리
	static void moveClouds(int d, int s) {
		while (!clouds.isEmpty()) {
			int[] cloud = clouds.poll();
			int r = cloud[0];
			int c = cloud[1];

			r = (r - 1 + N + (dr[d] * s) % N) % N + 1;
			c = (c - 1 + N + (dc[d] * s) % N) % N + 1;

			map[r][c]++;
			clouded[r][c] = true;
		}
	}
    //물복사 버그
	static void waterCopy() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (!clouded[i][j])
					continue;

				int waterBasket = 0;
				for (int dir = 2; dir <= 8; dir += 2) {
					int nr = i + dr[dir];
					int nc = j + dc[dir];

					if (inRange(nr, nc) && map[nr][nc] > 0)
						waterBasket++;
				}

				map[i][j] += waterBasket;
			}
		}
	}

	static boolean inRange(int r, int c) {
		return 1 <= r && r <= N && 1 <= c && c <= N;
	}
    //구름 생성
	static void makeClouds() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (clouded[i][j])
					clouded[i][j] = false;
				else {
					if (map[i][j] >= 2) {
						map[i][j] -= 2;
						clouds.add(new int[] {i, j});
					}
				}
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