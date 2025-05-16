public class Main {
	static int N;
	static int[][] paper;
	static int[] ans = new int[3];

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();

		N = in.nextInt();
		paper = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				paper[i][j] = in.nextInt();
			}
		}

		divCon(0, 0, N);

		for (int i = 0; i < 3; i++) {
			System.out.println(ans[i]);
		}
	}

	static void divCon(int r, int c, int dis) {
		for (int i = r; i < r + dis; i++) {
			for (int j = c; j < c + dis; j++) {
				if (paper[i][j] != paper[r][c]) {
					int nextDis = dis / 3;

					for (int k = r; k <= r + dis - nextDis; k += nextDis) {
						for (int l = c; l <= c + dis - nextDis; l += nextDis) {
							divCon(k, l, nextDis);
						}
					}

					return;
				}
			}
		}

		if (paper[r][c] == -1)
			ans[0]++;
		else if (paper[r][c] == 0)
			ans[1]++;
		else
			ans[2]++;
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