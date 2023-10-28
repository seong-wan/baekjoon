import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, ans;
	static int[][] mat;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, -1, 0, 1 };
	static boolean[][] visit;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		mat = new int[N][N];
		visit = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				mat[i][j] = Integer.parseInt(st.nextToken());
			}
		} // 인접행렬

		dijk();
	}

	static void dijk() {
		PriorityQueue<int[]> pq = new PriorityQueue<>((e1, e2) -> e1[2] - e2[2]);
		pq.add(new int[] { 0, 0, 0 });
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int cr = cur[0];
			int cc = cur[1];
			if (visit[cr][cc])
				continue;
			int cost = cur[2];
			ans = Math.max(ans, cost);
			if (cr == N - 1 && cc == N - 1)
				System.out.println(ans);
			visit[cr][cc] = true;
			for (int dir = 0; dir < 4; dir++) {
				int nr = cr + dr[dir];
				int nc = cc + dc[dir];
				if (cango(nr, nc))
					pq.add(new int[] { nr, nc, Math.abs(mat[cr][cc] - mat[nr][nc]) });
			}
		}
	}

	static boolean cango(int nr, int nc) {
		if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visit[nr][nc])
			return true;
		return false;
	}

}