import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {// 플로이드-워셜을 거친 인접행렬의 mst를 구하면 본래의 트리가 나옴.
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int[][] mat;
	static PriorityQueue<Integer>[] edge;
	static boolean[] visit;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		mat = new int[N + 1][N + 1];
		visit = new boolean[N + 1];
		edge = new PriorityQueue[N + 1];
		for (int i = 1; i <= N; i++) {
			edge[i] = new PriorityQueue<>((e1, e2) -> e1 - e2);
		} // 오름차순 정렬을 위해 정점별 간선리스트를 pq로 만듬

		for (int i = 1; i <= N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = i + 1; j <= N; j++) {
				int temp = Integer.parseInt(st.nextToken());
				mat[i][j] = mat[j][i] = temp;
			} // 플로이드-워셜을 거친 인접행렬 입력
		}
		prim();
		print();
	}

	static void prim() {
		PriorityQueue<int[]> pq = new PriorityQueue<>((e1, e2) -> e1[1] - e2[1]);
		int cnt = 0;
		pq.add(new int[] { 1, 0, 0 });
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int to = cur[0];
			if (visit[to])
				continue;
			int from = cur[2];
			if (from != 0) {
				edge[from].add(to);
				edge[to].add(from);
			}
			cnt++;
			if (cnt == N)
				return;
			visit[to] = true;
			for (int i = 1; i <= N; i++) {
				if (visit[i])
					continue;
				pq.add(new int[] { i, mat[to][i], to });
			}
		}
	}

	static void print() {
		for (int i = 1; i <= N; i++) {
			sb.append(edge[i].size() + " ");
			while (!edge[i].isEmpty()) {
				sb.append(edge[i].poll() + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}