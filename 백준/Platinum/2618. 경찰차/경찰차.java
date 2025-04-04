public class Main {
	static int N, w;
	static int[][] dp;
	static int[][] accident;
	static int[][] trace;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		N = in.nextInt();
		w = in.nextInt();

		dp = new int[w + 2][w + 2];
		accident = new int[w + 2][2];
		trace = new int[w + 2][w + 2];

		accident[0][0] = 1;
		accident[0][1] = 1;
		accident[1][0] = N;
		accident[1][1] = N;

		for (int i = 2; i < w + 2; i++) {
			accident[i][0] = in.nextInt();
			accident[i][1] = in.nextInt();
		}

		sb.append(dfs(0, 1)).append("\n");

		int one = 0, two = 1;
		for (int i = 2; i < w + 2; i++) {
			int temp = trace[one][two];
			sb.append(temp).append("\n");

			if (temp == 1)
				one = i;
			else
				two = i;
		}

		System.out.println(sb);
	}

	static int dfs(int oneIdx, int twoIdx) {
		//사건 인덱스 범위 밖
		if (oneIdx == w + 1 || twoIdx == w + 1)
			return 0;

		//이미 계산한 구간
		if (trace[oneIdx][twoIdx] != 0)
			return dp[oneIdx][twoIdx];

		int next = Math.max(oneIdx, twoIdx) + 1;

		int oneMove = dfs(next, twoIdx) + calcDistance(oneIdx, next);
		int twoMove = dfs(oneIdx, next) + calcDistance(twoIdx, next);

		if (oneMove < twoMove) {
			trace[oneIdx][twoIdx] = 1;
			dp[oneIdx][twoIdx] = oneMove;
		} else {
			trace[oneIdx][twoIdx] = 2;
			dp[oneIdx][twoIdx] = twoMove;
		}

		return dp[oneIdx][twoIdx];
	}

	static int calcDistance(int from, int to) {
		return Math.abs(accident[from][0] - accident[to][0]) + Math.abs(accident[from][1] - accident[to][1]);
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