import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int N, t = 1;
	static int[][] map;
	static boolean[][] visit;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, -1, 0, 1 };

	public static void main(String[] args) throws Exception {
		while (true) {
			N = Integer.parseInt(br.readLine());
			if (N == 0)
				break;
			map = new int[N][N];
			visit = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			} // map 입력

			dikstra();
			t++;
		}
		System.out.println(sb);
	}

	static void dikstra() {
		PriorityQueue<int[]> pq = new PriorityQueue<>((e1, e2) -> e1[2] - e2[2]);
		pq.add(new int[] { 0, 0, map[0][0] });
		visit[0][0] = true;
		while (!pq.isEmpty()) {
			int cur[] = pq.poll();
			int cr = cur[0];
			int cc = cur[1];
			int cost = cur[2];
			if (cr == N - 1 && cc == N - 1) {
				sb.append("Problem " + t + ": " + cost + "\n");
				return;
			}

			for (int dir = 0; dir < 4; dir++) {
				int nr = cr + dr[dir];
				int nc = cc + dc[dir];
				if (cango(nr, nc)) {
					visit[nr][nc] = true;
					pq.add(new int[] { nr, nc, cost + map[nr][nc] });
				}

			}
		}
	}

	static boolean cango(int nr, int nc) {
		if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visit[nr][nc])
			return true;
		return false;
	}

}