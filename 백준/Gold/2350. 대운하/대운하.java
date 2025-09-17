import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

//폭이 넓은 운하를 우선으로 스패닝트리를 만듬
//해당 스패닝트리를 이용해서 경로의 최소 폭을 구함
public class Main {
	static int N, M, K;
	static List<int[]>[] adlist;
	static List<int[]>[] spanningTree;
	static int[][] memo;
	static StringBuilder sb = new StringBuilder();
	static boolean[] visit;

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();

		N = in.nextInt();
		M = in.nextInt();
		K = in.nextInt();

		memo = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			Arrays.fill(memo[i], -1);
		}

		adlist = new List[N + 1];
		spanningTree = new List[N + 1];
		for (int i = 1; i <= N; i++) {
			adlist[i] = new ArrayList<>();
			spanningTree[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			int from = in.nextInt();
			int to = in.nextInt();
			int w = in.nextInt();

			adlist[from].add(new int[] {to, w});
			adlist[to].add(new int[] {from, w});
		}

		makeTree();

		for (int i = 0; i < K; i++) {
			int from = in.nextInt();
			int to = in.nextInt();

			visit = new boolean[N + 1];
			visit[from] = true;
			sb.append(dfs(from, to)).append("\n");
		}

		System.out.print(sb);
	}

	static int dfs(int from, int to) {
		for (int[] next : spanningTree[from]) {
			int nextNode = next[0];
			int w = next[1];

			if (visit[nextNode])
				continue;

			visit[nextNode] = true;

			if (nextNode == to)
				return w;

			// if (memo[nextNode][to] != -1)
			// 	return memo[from][to] = Math.min(w, memo[nextNode][to]);

			int result = dfs(nextNode, to);

			//to로의 경로를 찾지 못한 경우
			if (result == -1)
				continue;

			return Math.min(w, result);
		}

		return -1;
	}

	static void makeTree() {
		//prim
		PriorityQueue<int[]> pq = new PriorityQueue<>((e1, e2) -> e2[2] - e1[2]);
		boolean[] visit = new boolean[N + 1];
		visit[1] = true;
		for (int[] next : adlist[1]) {
			pq.add(new int[] {1, next[0], next[1]});
		}

		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int from = cur[0];
			int to = cur[1];
			int w = cur[2];

			if (visit[to])
				continue;

			visit[to] = true;
			spanningTree[from].add(new int[] {to, w});
			spanningTree[to].add(new int[] {from, w});

			for (int[] next : adlist[to]) {
				if (!visit[next[0]])
					pq.add(new int[] {to, next[0], next[1]});
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