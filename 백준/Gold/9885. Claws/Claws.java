import java.util.ArrayList;
import java.util.List;

public class Main {
	static int N, root, max;
	static List<Integer>[] tree;
	static int[] parent, value;

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();

		N = in.nextInt();
		parent = new int[N + 1];
		value = new int[N + 1];
		tree = new List[N + 1];
		for (int i = 1; i <= N; i++) {
			tree[i] = new ArrayList<>();
		}

		for (int i = 0; i < N - 1; i++) {
			int c = in.nextInt();
			int p = in.nextInt();
			int w = in.nextInt();

			tree[p].add(c);
			parent[c] = p;
			value[c] = w;
		}

		for (int i = 1; i <= N; i++) {
			if (parent[i] == 0) {
				root = i;
				break;
			}
		}

		calc(root, 0);
		dfs(root, 0);

		System.out.print(max);
	}

	static void dfs(int node, int v) {
		v += value[node];
		max = Math.max(max, v);

		for (int next : tree[node]) {
			dfs(next, v);
		}
	}

	static int calc(int node, int ancW) {
		int sum = value[node];
		for (int next : tree[node]) {
			sum += calc(next, value[node] + ancW);
		}

		value[node] = sum + ancW;

		return sum;
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
