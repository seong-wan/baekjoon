public class Main {
	static int N, ans;
	static int[][] map;
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {-1, 0, 1, 0};

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();

		N = in.nextInt();
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = in.nextInt();
			}
		}

		simul();

		System.out.print(ans);
	}

	static void simul() {
		int[] loc = new int[] {N / 2, N / 2};
		int offset = 0; //한 방향으로 이동하는 거리
		int dir = 0;

		while (true) {
			if (dir % 2 == 0)
				offset++;

			for (int i = 0; i < offset; i++) {
				//이동할 위치
				loc[0] += dr[dir];
				loc[1] += dc[dir];

				//모래 이동 처리
				sandMove(loc, dir);
				map[loc[0]][loc[1]] = 0;

				if (loc[0] == 0 && loc[1] == 0)
					break;
			}

			if (loc[0] == 0 && loc[1] == 0)
				break;

			dir = (dir + 1) % 4;
		}
	}

	static void sandMove(int[] loc, int dir) {
		int totalSand = map[loc[0]][loc[1]];
		double sandPer = (double)totalSand / 100;
		int one = (int)sandPer;
		int two = (int)(sandPer * 2);
		int five = (int)(sandPer * 5);
		int seven = (int)(sandPer * 7);
		int ten = (int)(sandPer * 10);
		int alpha = totalSand - 2 * (one + two + seven + ten) - five;

		if (dir % 2 == 0) {
			//2%,7%,1%
			if (canGo(loc[0] - 2, loc[1])) {
				map[loc[0] - 2][loc[1]] += two;
				map[loc[0] - 1][loc[1]] += seven;
				map[loc[0] - 1][loc[1] - dc[dir]] += one;
			} else {
				ans += two;
				if (canGo(loc[0] - 1, loc[1])) {
					map[loc[0] - 1][loc[1]] += seven;
					map[loc[0] - 1][loc[1] - dc[dir]] += one;
				} else
					ans += seven + one;
			}

			if (canGo(loc[0] + 2, loc[1])) {
				map[loc[0] + 2][loc[1]] += two;
				map[loc[0] + 1][loc[1]] += seven;
				map[loc[0] + 1][loc[1] - dc[dir]] += one;
			} else {
				ans += two;
				if (canGo(loc[0] + 1, loc[1])) {
					map[loc[0] + 1][loc[1]] += seven;
					map[loc[0] + 1][loc[1] - dc[dir]] += one;
				} else
					ans += seven + one;
			}

			//5%,10%,alpha
			if (canGo(loc[0], loc[1] + dc[dir])) {
				map[loc[0]][loc[1] + dc[dir]] += alpha;

				if (canGo(loc[0] - 1, loc[1]))
					map[loc[0] - 1][loc[1] + dc[dir]] += ten;
				else
					ans += ten;

				if (canGo(loc[0] + 1, loc[1]))
					map[loc[0] + 1][loc[1] + dc[dir]] += ten;
				else
					ans += ten;

				if (canGo(loc[0], loc[1] + 2 * dc[dir]))
					map[loc[0]][loc[1] + 2 * dc[dir]] += five;
				else
					ans += five;

			} else
				ans += 2 * ten + alpha + five;

		} else {
			//2%,7%,1%
			if (canGo(loc[0], loc[1] - 2)) {
				map[loc[0]][loc[1] - 2] += two;
				map[loc[0]][loc[1] - 1] += seven;
				map[loc[0] - dr[dir]][loc[1] - 1] += one;
			} else {
				ans += two;
				if (canGo(loc[0], loc[1] - 1)) {
					map[loc[0]][loc[1] - 1] += seven;
					map[loc[0] - dr[dir]][loc[1] - 1] += one;
				} else
					ans += seven + one;
			}

			if (canGo(loc[0], loc[1] + 2)) {
				map[loc[0]][loc[1] + 2] += two;
				map[loc[0]][loc[1] + 1] += seven;
				map[loc[0] - dr[dir]][loc[1] + 1] += one;
			} else {
				ans += two;
				if (canGo(loc[0], loc[1] + 1)) {
					map[loc[0]][loc[1] + 1] += seven;
					map[loc[0] - dr[dir]][loc[1] + 1] += one;
				} else
					ans += seven + one;
			}

			//5%,10%,alpha
			if (canGo(loc[0] + dr[dir], loc[1])) {
				map[loc[0] + dr[dir]][loc[1]] += alpha;

				if (canGo(loc[0], loc[1] - 1))
					map[loc[0] + dr[dir]][loc[1] - 1] += ten;
				else
					ans += ten;

				if (canGo(loc[0], loc[1] + 1))
					map[loc[0] + dr[dir]][loc[1] + 1] += ten;
				else
					ans += ten;

				if (canGo(loc[0] + 2 * dr[dir], loc[1]))
					map[loc[0] + 2 * dr[dir]][loc[1]] += five;
				else
					ans += five;

			} else
				ans += 2 * ten + alpha + five;

		}
	}

	static boolean canGo(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
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
