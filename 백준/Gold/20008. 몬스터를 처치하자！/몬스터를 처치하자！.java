import java.util.Arrays;

public class Main {
	static int N, HP, ans = Integer.MAX_VALUE;
	static int[][] skills;
	static int[] used = new int[10];

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		N = in.nextInt();
		HP = in.nextInt();

		skills = new int[N][2];
		for (int i = 0; i < N; i++) {
			skills[i][0] = in.nextInt();
			skills[i][1] = in.nextInt();
		}

		Arrays.fill(used, -1);

		dfs(HP, 0);

		System.out.print(ans);
	}

	static void dfs(int HP, int idx) {
		if (HP <= 0) {
			calc();
			return;
		}

		for (int i = 0; i < N; i++) {
			used[idx] = i;
			dfs(HP - skills[i][1], idx + 1);
			used[idx] = -1;
		}
	}

	static void calc() {
		int[] coolTime = new int[N];
		int time = 0;

		for (int i = 0; i < 10; i++) {
			if (used[i] == -1) {
				break;
			}

			if (coolTime[used[i]] != 0) {
				int temp = coolTime[used[i]];
				time += temp;
				for (int j = 0; j < N; j++) {
					coolTime[j] = Math.max(0, coolTime[j] - temp);
				}
			}

			coolTime[used[i]] = skills[used[i]][0];
			time++;

			for (int j = 0; j < N; j++) {
				coolTime[j] = Math.max(0, coolTime[j] - 1);
			}
		}

		ans = Math.min(ans, time);
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
