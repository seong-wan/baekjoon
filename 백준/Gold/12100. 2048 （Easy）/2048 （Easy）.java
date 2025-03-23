public class Main {
	static int N, max;
	static int[][] origin;

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();

		N = in.nextInt();

		origin = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				origin[i][j] = in.nextInt();

				max = Math.max(max, origin[i][j]);
			}
		}

		simul(0, origin);

		System.out.println(max);
	}

	static void simul(int depth, int[][] map) {
		if (depth == 5)
			return;

		//위로 움직임
		int[][] upMap = new int[N][N];
		copy(map, upMap);
		for (int i = 0; i < N; i++) {
			int end = 0;
			for (int j = 1; j < N; j++) {
				if (upMap[j][i] == 0)
					continue;

				if (upMap[end][i] == 0) {
					upMap[end][i] = upMap[j][i];
					upMap[j][i] = 0;
					continue;
				}

				if (upMap[j][i] == upMap[end][i]) {
					upMap[end][i] *= 2;
					upMap[j][i] = 0;

					max = Math.max(max, upMap[end][i]);
					end++;
				} else {
					end++;

					if (end == j)
						continue;

					upMap[end][i] = upMap[j][i];
					upMap[j][i] = 0;
				}
			}
		}

		simul(depth + 1, upMap);

		//하로 움직임
		int[][] downMap = new int[N][N];
		copy(map, downMap);
		for (int i = 0; i < N; i++) {
			int end = N - 1;
			for (int j = N - 2; j >= 0; j--) {
				if (downMap[j][i] == 0)
					continue;

				if (downMap[end][i] == 0) {
					downMap[end][i] = downMap[j][i];
					downMap[j][i] = 0;
					continue;
				}

				if (downMap[j][i] == downMap[end][i]) {
					downMap[end][i] *= 2;
					downMap[j][i] = 0;

					max = Math.max(max, downMap[end][i]);
					end--;
				} else {
					end--;

					if (end == j)
						continue;

					downMap[end][i] = downMap[j][i];
					downMap[j][i] = 0;
				}
			}
		}

		simul(depth + 1, downMap);

		//좌로 움직임
		int[][] leftMap = new int[N][N];
		copy(map, leftMap);
		for (int i = 0; i < N; i++) {
			int end = 0;
			for (int j = 1; j < N; j++) {
				if (leftMap[i][j] == 0)
					continue;

				if (leftMap[i][end] == 0) {
					leftMap[i][end] = leftMap[i][j];
					leftMap[i][j] = 0;
					continue;
				}

				if (leftMap[i][j] == leftMap[i][end]) {
					leftMap[i][end] *= 2;
					leftMap[i][j] = 0;

					max = Math.max(max, leftMap[i][end]);
					end++;
				} else {
					end++;

					if (end == j)
						continue;

					leftMap[i][end] = leftMap[i][j];
					leftMap[i][j] = 0;
				}
			}
		}

		simul(depth + 1, leftMap);

		//우로 움직임
		int[][] rightMap = new int[N][N];
		copy(map, rightMap);
		for (int i = 0; i < N; i++) {
			int end = N - 1;
			for (int j = N - 2; j >= 0; j--) {
				if (rightMap[i][j] == 0)
					continue;

				if (rightMap[i][end] == 0) {
					rightMap[i][end] = rightMap[i][j];
					rightMap[i][j] = 0;
					continue;
				}

				if (rightMap[i][j] == rightMap[i][end]) {
					rightMap[i][end] *= 2;
					rightMap[i][j] = 0;

					max = Math.max(max, rightMap[i][end]);
					end--;
				} else {
					end--;

					if (end == j)
						continue;

					rightMap[i][end] = rightMap[i][j];
					rightMap[i][j] = 0;
				}
			}
		}

		simul(depth + 1, rightMap);
	}

	static void copy(int[][] origin, int[][] newMap) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				newMap[i][j] = origin[i][j];
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