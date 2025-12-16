public class Main {
	static int N, min = Integer.MAX_VALUE;
	static int[][] scores = new int[20][20];

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();

		N = in.nextInt();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				scores[i][j] = in.nextInt();
			}
		}

		for (int i = 0; i < N; i++) {
			search(i, 1, 1 << i);
		}

		System.out.print(min);
	}

	static void search(int idx, int cnt, int mask) {
		if (min == 0 || !check(mask))
			return;

		if (cnt == (N + 1) / 2)
			return;

		for (int i = idx + 1; i < N; i++) {
			search(i, cnt + 1, mask | 1 << i);
		}
	}

	static boolean check(int mask) {
		int start = 0;
		int link = 0;
		boolean[] isStart = new boolean[N];

		for (int i = 0; i < N; i++) {
			if ((mask & (1 << i)) != 0)
				isStart[i] = true;

			for (int j = 0; j < i; j++) {
				//start팀 점수 계산
				if (isStart[i] && isStart[j]) {
					start += scores[i][j];
					start += scores[j][i];
				}

				if (!isStart[i] && !isStart[j]) {
					link += scores[i][j];
					link += scores[j][i];
				}
			}
		}

		min = Math.min(min, Math.abs(start - link));

		//스타트팀을 더 늘릴 경우 차이가 더 커지기 때문에 진행 x
		if (start - link >= min)
			return false;

		return true;
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