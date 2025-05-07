import java.util.Arrays;

public class Main {
	static int N, Q;
	static adNode[] adlist;
	static int[] parent;
	static int[] cycle = new int[2];
	static StringBuilder sb = new StringBuilder();

	static class adNode {
		int to;
		adNode next;

		public adNode(int to, adNode next) {
			this.to = to;
			this.next = next;
		}
	}

	public static void main(String[] args) throws Exception {
		N = read();
		Q = read();

		adlist = new adNode[N + 1];
		parent = new int[N + 1];
		Arrays.fill(parent, -1);

		for (int i = 0; i < N; i++) {
			int a = read();
			int b = read();

			adlist[a] = new adNode(b, adlist[a]);
			adlist[b] = new adNode(a, adlist[b]);
		}

		parent[1] = 0;
		cycleCheck(1);
		make();

		for (int i = 0; i < Q; i++) {
			int A = read();
			int B = read();

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
		for (adNode cur = adlist[node]; cur != null; cur = cur.next) {
			int next = cur.to;
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

	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}
}