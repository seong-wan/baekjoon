public class Main {
	static int N, L, ans;
	static int[][] map;

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();

		N = in.nextInt();
		L = in.nextInt();
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = in.nextInt();
			}
		}

		horizonCheck();
		verticalCheck();

		System.out.print(ans);
	}

	static void verticalCheck() {
		for (int i = 0; i < N; i++) {
			int temp = map[0][i];
			int length = 1;
			boolean check = false;

			for (int j = 1; j < N; j++) {
				if (map[j][i] == temp)
					length++;
				else {
					if (Math.abs(temp - map[j][i]) > 1)
						break;

					//내리막을 해당 높이에서 깔았어야 하는 경우라면
					if (check) {
						check = false;
						length -= L;
					}

					//더 큰 값을 만나는 경우
					if (temp < map[j][i]) {
						if (length < L)
							break;

						length = 1;
						temp = map[j][i];
					}
					//더 작은 값을 만나는 경우
					else {
						if (length < 0)
							break;

						length = 1;
						temp = map[j][i];
						check = true;
					}
				}

				//끝까지 도달했을 때
				if (j == N - 1) {
					if (check)
						length -= L;

					if (length >= 0)
						ans++;
				}
			}
		}
	}

	static void horizonCheck() {
		for (int i = 0; i < N; i++) {
			int temp = map[i][0];
			int length = 1;
			boolean check = false;

			for (int j = 1; j < N; j++) {
				if (map[i][j] == temp)
					length++;
				else {
					if (Math.abs(temp - map[i][j]) > 1)
						break;

					//내리막을 해당 높이에서 깔았어야 하는 경우라면
					if (check) {
						check = false;
						length -= L;
					}

					//더 큰 값을 만나는 경우
					if (temp < map[i][j]) {
						if (length < L)
							break;

						length = 1;
						temp = map[i][j];
					}
					//더 작은 값을 만나는 경우
					else {
						if (length < 0)
							break;

						length = 1;
						temp = map[i][j];
						check = true;
					}
				}

				//끝까지 도달했을 때
				if (j == N - 1) {
					if (check)
						length -= L;

					if (length >= 0)
						ans++;
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