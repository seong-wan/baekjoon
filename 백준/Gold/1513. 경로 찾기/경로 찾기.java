public class Main {
	static int N, M, C;
	static int[][][][] map = new int[51][51][51][51];
	static final int div = 1_000_007;
	static int[][] gameSpace = new int[51][51];
	static int[][] spaceToPoint = new int[51][2];
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();

		N = in.nextInt();
		M = in.nextInt();
		C = in.nextInt();

		for (int i = 1; i <= C; i++) {
			int r = in.nextInt();
			int c = in.nextInt();
			gameSpace[r][c] = i;
			spaceToPoint[i][0] = r;
			spaceToPoint[i][1] = c;
		}

		//출발지가 오락실이 아닌 경우
		if (gameSpace[1][1] == 0) {
			map[1][1][0][0] = 1;

			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= M; j++) {
					if (i == 1 && j == 1)
						continue;

					int value = (map[i - 1][j][0][0] + map[i][j - 1][0][0]) % div;

					if (gameSpace[i][j] != 0)
						map[i][j][gameSpace[i][j]][1] = value;
					else
						map[i][j][0][0] = value;
				}
			}
		}
		//출발지가 오락실인 경우
		else
			map[1][1][gameSpace[1][1]][1] = 1;

		//방문횟수
		for (int i = 1; i <= C; i++) {
			//마지막 방문 오락실
			for (int j = 1; j <= C; j++) {
				int r = spaceToPoint[j][0];
				int c = spaceToPoint[j][1];

				if (map[r][c][j][i] == 0)
					continue;

				for (int k = r; k <= N; k++) {
					for (int l = 1; l <= M; l++) {
						if (gameSpace[k][l] == j) {
							continue;
						}

						int value = (map[k - 1][l][j][i] + map[k][l - 1][j][i]) % div;

						if (gameSpace[k][l] == 0)
							map[k][l][j][i] = value;
							//다른 오락실을 지나치는 경우
						else {
							if (gameSpace[k][l] > j)
								map[k][l][gameSpace[k][l]][i + 1] = (map[k][l][gameSpace[k][l]][i + 1] + value) % div;
						}
					}
				}
			}
		}

		sb.append(map[N][M][0][0]).append(" ");

		//오락실 방문 횟수
		for (int i = 1; i <= C; i++) {
			int sum = 0;
			for (int j = 1; j <= C; j++) {
				sum = (sum + map[N][M][j][i]) % div;
			}

			sb.append(sum).append(" ");
		}

		System.out.print(sb);
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