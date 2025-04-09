import java.util.ArrayList;
import java.util.List;

public class Main {
	static int N, M, seq = 1;
	static List<int[]>[] tree;
	static StringBuilder sb = new StringBuilder();
	static int[] disFromRoot;
	static int[] numToSeq;
	static int[] seqToNum;
	static int[] firstSearch;
	static List<Integer> trace = new ArrayList<>();
	static int[] segment;
	static boolean[] visit;

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();

		N = in.nextInt();
		tree = new List[N + 1];
		disFromRoot = new int[N + 1];
		numToSeq = new int[N + 1];
		seqToNum = new int[N + 1];
		firstSearch = new int[N + 1];
		visit = new boolean[N + 1];

		for (int i = 1; i <= N; i++) {
			tree[i] = new ArrayList<>();
		}

		for (int i = 0; i < N - 1; i++) {
			int from = in.nextInt();
			int to = in.nextInt();
			int w = in.nextInt();

			tree[from].add(new int[] {to, w});
			tree[to].add(new int[] {from, w});
		}

		dfs(1, 0);
		int endIdx = trace.size() - 1;
		segment = new int[4 * endIdx];

		init(1, 0, endIdx);

		M = in.nextInt();
		for (int i = 0; i < M; i++) {
			int a = in.nextInt();
			int b = in.nextInt();

			int firstA = firstSearch[numToSeq[a]];
			int firstB = firstSearch[numToSeq[b]];

			int seqLCA = query(1, 0, endIdx, Math.min(firstA, firstB), Math.max(firstA, firstB));
			sb.append(disFromRoot[a] + disFromRoot[b] - 2 * disFromRoot[seqToNum[seqLCA]]).append("\n");
		}

		System.out.println(sb);
	}

	static int query(int node, int left, int right, int qLeft, int qRight) {
		if (left > qRight || right < qLeft)
			return Integer.MAX_VALUE;

		if (qLeft <= left && qRight >= right)
			return segment[node];

		int mid = (left + right) >> 1;
		int leftResult = query(node << 1, left, mid, qLeft, qRight);
		int rightResult = query(node << 1 | 1, mid + 1, right, qLeft, qRight);

		return Math.min(leftResult, rightResult);
	}

	static void init(int node, int left, int right) {
		if (left == right) {
			segment[node] = trace.get(left);
			return;
		}

		int mid = (left + right) >> 1;
		init(node << 1, left, mid);
		init(node << 1 | 1, mid + 1, right);

		segment[node] = Math.min(segment[node << 1], segment[node << 1 | 1]);
	}

	static void dfs(int node, int dis) {
		visit[node] = true;
		disFromRoot[node] = dis;
		numToSeq[node] = seq;
		seqToNum[seq] = node;
		firstSearch[seq] = trace.size();
		trace.add(seq++);

		for (int[] next : tree[node]) {
			if (visit[next[0]])
				continue;

			dfs(next[0], dis + next[1]);
			trace.add(numToSeq[node]);
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