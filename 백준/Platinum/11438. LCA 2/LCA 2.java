import java.util.ArrayList;
import java.util.List;

public class Main {
	static int N, M, maxLength, seq = 1;
	static List<Integer>[] tree;
	static boolean[] visit;
	static int[] numToSeq;
	static int[] seqToNum;
	static int[] firstSearch;
	static List<Integer> trace = new ArrayList<>();
	static int[] segment;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();

		N = in.nextInt();

		tree = new List[N + 1];
		visit = new boolean[N + 1];
		numToSeq = new int[N + 1];
		seqToNum = new int[N + 1];
		firstSearch = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			tree[i] = new ArrayList<>();
		}

		for (int i = 0; i < N - 1; i++) {
			int from = in.nextInt();
			int to = in.nextInt();

			tree[from].add(to);
			tree[to].add(from);
		}

		dfs(1);

		maxLength = trace.size() - 1;
		segment = new int[4 * maxLength];
		init(1, 0, maxLength);

		M = in.nextInt();

		for (int i = 0; i < M; i++) {
			int a = in.nextInt();
			int b = in.nextInt();

			int aSeq = numToSeq[a];
			int bSeq = numToSeq[b];

			int result = query(1, 0, maxLength, Math.min(firstSearch[aSeq], firstSearch[bSeq]),
				Math.max(firstSearch[aSeq], firstSearch[bSeq]));
			sb.append(seqToNum[result]).append("\n");
		}

		System.out.println(sb);
	}

	static int query(int node, int left, int right, int qLeft, int qRight) {
		if (left > qRight || right < qLeft)
			return Integer.MAX_VALUE;

		if (left >= qLeft && right <= qRight)
			return segment[node];

		int mid = (left + right) >> 1;
		int resultL = query(node << 1, left, mid, qLeft, qRight);
		int resultR = query(node << 1 | 1, mid + 1, right, qLeft, qRight);

		return Math.min(resultL, resultR);
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

	static void dfs(int node) {
		visit[node] = true;
		numToSeq[node] = seq;
		seqToNum[seq] = node;
		firstSearch[seq] = trace.size();
		trace.add(seq++);

		for (Integer next : tree[node]) {
			if (visit[next])
				continue;

			dfs(next);
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