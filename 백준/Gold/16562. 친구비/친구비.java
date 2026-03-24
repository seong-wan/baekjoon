import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
	static int N, M, k, ans;
	static int[][] fee;
	static boolean[] check;
	static List<Integer>[] adlist;

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();

		N = in.nextInt();
		M = in.nextInt();
		k = in.nextInt();

		adlist = new List[N + 1];
		for (int i = 1; i <= N; i++) {
			adlist[i] = new ArrayList<>();
		}

		check = new boolean[N + 1];
		fee = new int[N][2];
		for (int i = 1; i <= N; i++) {
			fee[i - 1][0] = i;
			fee[i - 1][1] = in.nextInt();
		}

		Arrays.sort(fee, (e1, e2) -> e1[1] - e2[1]);

		for (int i = 0; i < M; i++) {
			int a = in.nextInt();
			int b = in.nextInt();

			adlist[a].add(b);
			adlist[b].add(a);
		}

		for (int i = 0; i < N; i++) {
			if (check[fee[i][0]])
				continue;

			ans += fee[i][1];

			if (ans > k) {
				System.out.print("Oh no");
				return;
			}

			check[fee[i][0]] = true;
			dfs(fee[i][0]);
		}

		System.out.print(ans);
	}

	static void dfs(int node) {
		for (Integer next : adlist[node]) {
			if (check[next])
				continue;

			check[next] = true;
			dfs(next);
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