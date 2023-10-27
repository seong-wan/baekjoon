import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, C;
	static int[][] Node;
	static int[][] mat;
	static boolean[] visit;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		Node = new int[N][2];
		mat = new int[N][N];
		visit = new boolean[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			Node[i][0] = Integer.parseInt(st.nextToken());
			Node[i][1] = Integer.parseInt(st.nextToken());
		} // 노드 정보 입력

		makeedge();
		prim();
	}

	static void makeedge() {
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				int dx = Node[i][0] - Node[j][0];
				int dy = Node[i][1] - Node[j][1];
				mat[i][j] = mat[j][i] = dx * dx + dy * dy;
			}
		}
	}

	static void prim() {
		PriorityQueue<int[]> pq = new PriorityQueue<>((e1, e2) -> e1[1] - e2[1]);
		int cnt = 0;
		long ans = 0;
		pq.add(new int[] { 0, 0 });
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int to = cur[0];
			if (visit[to])
				continue;
			int cost = cur[1];
			cnt++;
			ans += cost;
			if (cnt == N) {
				System.out.println(ans);
				return;
			}
			visit[to] = true;
			for (int i = 0; i < N; i++) {
				if (visit[i] || mat[to][i] < C)
					continue;
				pq.add(new int[] { i, mat[to][i] });
			}
		}
		System.out.println(-1);
	}
}