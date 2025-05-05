public class Main {
	static int T, n, ans;
	static boolean[] check;
	static boolean[] visit;
	static int[] input;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		T = in.nextInt();

		for (int t = 0; t < T; t++) {
			n = in.nextInt();

			check = new boolean[n + 1];
			visit = new boolean[n + 1];
			input = new int[n + 1];
			ans = n;

			for (int i = 1; i <= n; i++) {
				input[i] = in.nextInt();
			}

			for (int i = 1; i <= n; i++) {
				if (!visit[i]) {
					visit[i] = true;
					dfs(i);
				}

			}

			sb.append(ans).append("\n");
		}
		System.out.println(sb);
	}

	static void dfs(int num) {
		int next = input[num];

		if (!visit[next]) {
			visit[next] = true;
			dfs(next);
		}
		//사이클 형성
		else if (!check[next]) {
			int temp = input[next];
			ans--;

			while (temp != next) {
				ans--;
				temp = input[temp];
			}
		}

		check[num] = true;
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