public class Main {
	static int[][] map = new int[10][10];
	static int[] start;
	static int end;
	static int ans = 26;

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				map[i][j] = in.nextInt();

				if (map[i][j] == 1) {
					if (start == null) {
						start = new int[] {i, j};
					}

					end = i * 10 + j;
				}
			}
		}

		//1이 없는 경우
		if (start == null) {
			System.out.print(0);
			return;
		}

		search(start[0], start[1], 0, 0, 0, 0, 0);

		System.out.print(ans == 26 ? -1 : ans);
	}

	static void search(int r, int c, int one, int two, int three, int four, int five) {
		int maxSize = 0;

		for (int i = 1; i <= 5; i++) {
			boolean isSearched = false;

			//해당 위치를 시작으로 i 사이즈의 색종이가 붙여지는지 확인
			// 추가 되는 부분만 확인하고 붙이는 처리
			if (check(r, c, i)) {
				maxSize = i;
				//붙여지는 경우 해당 색종이를 붙였다는 처리 후 dfs 진행
				attach(r, c, i);

				if (i == 1) {
					if (one == 5)
						continue;

					one++;
				} else if (i == 2) {
					if (two == 5)
						continue;

					two++;
				} else if (i == 3) {
					if (three == 5)
						continue;

					three++;
				} else if (i == 4) {
					if (four == 5)
						continue;

					four++;
				} else {
					if (five == 5)
						continue;

					five++;
				}

				for (int j = r; j < 10; j++) {
					for (int k = 0; k < 10; k++) {
						//1 탐색이 다 끝난 경우
						if (isEnd(j, k)) {
							break;
						}

						//다시 탐색을 시작할 구역
						if (map[j][k] == 1) {
							isSearched = true;
							search(j, k, one, two, three, four, five);
							break;
						}
					}

					if (isSearched)
						break;
				}

				if (!isSearched) {
					int sum = one + two + three + four + five;
					ans = Math.min(ans, sum);
				}

				if (i == 1)
					one--;
				else if (i == 2)
					two--;
				else if (i == 3)
					three--;
				else if (i == 4)
					four--;
				else
					five--;
			} else {
				break;
			}
		}
		restore(r, c, maxSize);
	}

	static boolean check(int r, int c, int size) {
		int nr = r + size - 1;
		int nc = c + size - 1;

		//해당 사이즈의 종이를 붙일 공간이 없는 경우
		if (nr >= 10 || nc >= 10)
			return false;

		for (int i = c; i <= c + size - 1; i++) {
			if (map[nr][i] == 0)
				return false;
		}

		for (int i = r; i < r + size - 1; i++) {
			if (map[i][nc] == 0)
				return false;
		}

		return true;
	}

	static void attach(int r, int c, int size) {
		int nr = r + size - 1;
		int nc = c + size - 1;

		for (int i = c; i <= nc; i++) {
			map[nr][i] = 0;
		}

		for (int i = r; i < nr; i++) {
			map[i][nc] = 0;

		}
	}

	static void restore(int r, int c, int size) {
		int nr = r + size - 1;
		int nc = c + size - 1;

		for (int i = r; i <= nr; i++) {
			for (int j = c; j <= nc; j++) {
				map[i][j] = 1;
			}
		}
	}

	static boolean isEnd(int r, int c) {
		return r * 10 + c > end;
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