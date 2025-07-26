public class Main {
	static int N, HP, ans = Integer.MAX_VALUE;
	static int[][] skills;
	static int[] coolTime;

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		N = in.nextInt();
		HP = in.nextInt();

		skills = new int[N][2];
		coolTime = new int[10];

		for (int i = 0; i < N; i++) {
			skills[i][0] = in.nextInt();
			skills[i][1] = in.nextInt();
		}

		dfs(HP, 0);

		System.out.print(ans);
	}

	static void dfs(int HP, int time) {
		if (time >= ans) {
			return;
		}

		if (HP <= 0) {
			ans = Math.min(ans, time);
			return;
		}

		boolean used = false;
		for (int i = 0; i < N; i++) {
			if (coolTime[i] > time)
				continue;

			used = true;
			int temp = coolTime[i];
			coolTime[i] = time + skills[i][0];
			dfs(HP - skills[i][1], time + 1);
			coolTime[i] = temp;
		}

		if (!used)
			dfs(HP, time + 1);
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
