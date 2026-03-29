import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, L, R;
	static int[][] map;
	static PriorityQueue<int[]> pq = new PriorityQueue<>((e1, e2) -> (e1[2] + e1[3]) - (e2[2] + e2[3]));
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j) - '0';

				if (map[i][j] == 2)
					pq.add(new int[] {i, j, 0, 0});
			}
		}

		System.out.print(dijk());
	}

	static int dijk() {
		int cnt = 0;

		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int cr = cur[0];
			int cc = cur[1];
			int left = cur[2];
			int right = cur[3];

			if (map[cr][cc] == 3)
				continue;

			map[cr][cc] = 3;
			cnt++;

			//위,아래 이동
			for (int dir = 0; dir < 2; dir++) {
				int nr = cr + dr[dir];
				int nc = cc + dc[dir];

				if (canGo(nr, nc))
					pq.add(new int[] {nr, nc, left, right});
			}

			//좌측 이동
			if (left < L) {
				int nr = cr + dr[2];
				int nc = cc + dc[2];

				if (canGo(nr, nc))
					pq.add(new int[] {nr, nc, left + 1, right});
			}

			//우측 이동
			if (right < R) {
				int nr = cr + dr[3];
				int nc = cc + dc[3];

				if (canGo(nr, nc))
					pq.add(new int[] {nr, nc, left, right + 1});
			}
		}

		return cnt;
	}

	static boolean canGo(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M && map[r][c] != 1 && map[r][c] != 3;
	}
}