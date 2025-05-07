import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
	static int N, Q;
	static List<Integer>[] adlist;
	static int[] parent;
	static int[] cycle = new int[2];
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		N = in.nextInt();
		Q = in.nextInt();

		adlist = new List[N + 1];
		parent = new int[N + 1];
		Arrays.fill(parent, -1);

		for (int i = 1; i <= N; i++) {
			adlist[i] = new ArrayList<>();
		}

		for (int i = 0; i < N; i++) {
			int a = in.nextInt();
			int b = in.nextInt();

			adlist[a].add(b);
			adlist[b].add(a);
		}

		parent[1] = 0;
		cycleCheck(1);
		make();

		for (int i = 0; i < Q; i++) {
			int A = in.nextInt();
			int B = in.nextInt();

			int rootA = find(A);
			int rootB = find(B);

			if (rootA == rootB)
				sb.append(1);
			else
				sb.append(2);

			sb.append("\n");
		}

		System.out.println(sb);
	}

	static int find(int node) {
		if (parent[node] == node)
			return node;

		return parent[node] = find(parent[node]);
	}

	static void make() {
		int before = parent[cycle[0]];
		int temp = cycle[0];
		while (temp != cycle[1]) {
			parent[temp] = temp;
			temp = before;
			before = parent[temp];
		}

		while (temp != 0) {
			parent[temp] = cycle[1];
			temp = before;
			before = parent[temp];
		}
	}

	static void cycleCheck(int node) {
		for (Integer next : adlist[node]) {
			if (parent[node] == next)
				continue;

			if (parent[next] == -1) {
				parent[next] = node;
				cycleCheck(next);
			} else {
				if (cycle[0] == 0) {
					cycle[0] = node;
					cycle[1] = next;
				}
			}
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