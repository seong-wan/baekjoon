import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int N, M, K;
	static int mat[][];
	static int[][] edge;
	static boolean fail;
	static boolean[] visit;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		mat = new int[N + 1][N + 1];
		edge = new int[M][2];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			edge[i][0] = Integer.parseInt(st.nextToken());
			edge[i][1] = Integer.parseInt(st.nextToken());
			mat[edge[i][0]][edge[i][1]] = i + 1;
			mat[edge[i][1]][edge[i][0]] = i + 1;
		} // 간선 정보 입력

		for (int i = 0; i < K; i++) {
			if (fail) {
				sb.append(0 + " ");
				continue;
			}
			prim();
			mat[edge[i][0]][edge[i][1]] = 0;
			mat[edge[i][1]][edge[i][0]] = 0;// 가중치가 간선부터 없앰
		}
		System.out.println(sb);

	}

	static void prim() {
		PriorityQueue<int[]> pq = new PriorityQueue<>((e1, e2) -> e1[1] - e2[1]);
		int ans = 0;
		int cnt = 0;
		visit = new boolean[N + 1];
		pq.add(new int[] { 1, 0 });
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int to = cur[0];
			if (visit[to])
				continue;
			int cost = cur[1];
			ans += cost;
			cnt++;
			if (cnt == N) {
				sb.append(ans + " ");
				return;
			}
			visit[to] = true;
			for (int i = 1; i <= N; i++) {
				if (visit[i] || mat[to][i] == 0)
					continue;
				pq.add(new int[] { i, mat[to][i] });
			}
		}
		sb.append(0 + " ");
		fail = true;
	}
}