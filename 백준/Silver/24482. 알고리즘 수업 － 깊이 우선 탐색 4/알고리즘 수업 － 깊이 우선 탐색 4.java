import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class Main {
	static int N, M, R;
	static Set<Integer>[] adlist;
	static int[] ans;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		N = in.nextInt();
		M = in.nextInt();
		R = in.nextInt();

		ans = new int[N + 1];
		adlist = new Set[N + 1];

		for (int i = 1; i <= N; i++) {
			adlist[i] = new TreeSet<>((e1, e2) -> e2 - e1);
		}
		Arrays.fill(ans, -1);

		for (int i = 0; i < M; i++) {
			int from = in.nextInt();
			int to = in.nextInt();

			adlist[from].add(to);
			adlist[to].add(from);
		}

		ans[R] = 0;
		dfs(R, 0);

		for (int i = 1; i <= N; i++) {
			sb.append(ans[i]).append("\n");
		}

		System.out.println(sb);
	}

	static void dfs(int node, int depth) {
		for (Integer next : adlist[node]) {
			if (ans[next] != -1)
				continue;

			ans[next] = depth + 1;
			dfs(next, depth + 1);
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