import java.util.ArrayList;
import java.util.List;

public class Main {
	static int N, Q, end;
	static List<Integer>[] adlist = new List[1001];
	static int[] milkCnt = new int[1001];
	static StringBuilder sb = new StringBuilder();
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();

		N = in.nextInt();
		for (int i = 1; i <= N; i++) {
			adlist[i] = new ArrayList<>();
		}

		for (int i = 0; i < N - 1; i++) {
			int a = in.nextInt();
			int b = in.nextInt();

			adlist[a].add(b);
			adlist[b].add(a);
		}

		Q = in.nextInt();
		for (int i = 0; i < Q; i++) {
			int t = in.nextInt();

			if (t == 1) {
				int start = in.nextInt();
				end = in.nextInt();

				visited = new boolean[N + 1];
				visited[start] = true;
				dfs(start, 0);
			} else if (t == 2) {
				int x = in.nextInt();
				sb.append(milkCnt[x]).append("\n");
			}
		}

		System.out.print(sb);
	}

	static boolean dfs(int cur, int seq) {
		if (cur == end) {
			milkCnt[cur] += seq;
			return true;
		}

		for (Integer next : adlist[cur]) {
			if (visited[next])
				continue;

			visited[next] = true;
			boolean result = dfs(next, seq + 1);
			if (result) {
				milkCnt[cur] += seq;
				return true;
			}
		}

		return false;
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