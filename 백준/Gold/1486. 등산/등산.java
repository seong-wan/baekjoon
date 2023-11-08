import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[][] map;
	static int[][] timechk;// 각 점에서 호텔까지의 시간 체크
	static int N, M, T, D, ans;
	static boolean[][] visit;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, -1, 0, 1 };

	public static void main(String[] args) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		timechk = new int[N][M];

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				char c = s.charAt(j);
				if (Character.isUpperCase(c))
					map[i][j] = c - 'A';
				else
					map[i][j] = c - 'a' + 26;
			}
		} // 맵 입력

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (i == 0 && j == 0)
					continue;
				dijkchk(i, j);
			}
		}

		dijk();
		System.out.println(ans);
	}

	private static void dijkchk(int i, int j) {
		visit = new boolean[N][M];
		PriorityQueue<int[]> pq = new PriorityQueue<>((e1, e2) -> e1[2] - e2[2]);
		pq.add(new int[] { i, j, 0 });// 현재 위치와 이동하는데 걸린 총 시간
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int cr = cur[0];
			int cc = cur[1];
			int time = cur[2];
			if (cr == 0 && cc == 0) {
				timechk[i][j] = time;
				return;
			}

			if (visit[cr][cc])
				continue;// 방문한적 있다면 pass
			visit[cr][cc] = true;

			for (int dir = 0; dir < 4; dir++) {
				int nr = cr + dr[dir];
				int nc = cc + dc[dir];
				if (cango(nr, nc)) {
					int diff = map[nr][nc] - map[cr][cc];
					if (Math.abs(diff) > T)// 높이의 차이가 T보다 큰 경우
						continue;
					if (diff > 0) {
						if (time + diff * diff <= D)// 시간이 D보다 작거나 같게 걸린 경우만 체크
							pq.add(new int[] { nr, nc, time + diff * diff });
					} // 더 높은 곳으로 이동
					else {
						if (time + 1 <= D)// 시간이 D보다 작거나 같게 걸린 경우만 체크
							pq.add(new int[] { nr, nc, time + 1 });
					}
					// 낮은 곳으로 이동
				}
			}
		}
	}

	static void dijk() {
		visit = new boolean[N][M];
		PriorityQueue<int[]> pq = new PriorityQueue<>((e1, e2) -> e1[2] - e2[2]);
		pq.add(new int[] { 0, 0, 0 });// 현재 위치와 이동하는데 걸린 총 시간
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int cr = cur[0];
			int cc = cur[1];
			if (visit[cr][cc])
				continue;// 방문한적 있다면 pass
			visit[cr][cc] = true;
			ans = Math.max(ans, map[cr][cc]);// 높이의 최대 체크
			int time = cur[2];
			for (int dir = 0; dir < 4; dir++) {
				int nr = cr + dr[dir];
				int nc = cc + dc[dir];
				if (cango(nr, nc)) {
					int diff = map[nr][nc] - map[cr][cc];
					if (Math.abs(diff) > T)// 높이의 차이가 T보다 큰 경우
						continue;
					if (diff > 0) {
						if (time + diff * diff + timechk[nr][nc] <= D)// 시간이 D보다 작거나 같게 걸린 경우만 체크
							// 돌아가는 시간도 고려하여 시간체크
							pq.add(new int[] { nr, nc, time + diff * diff });// 더 높은 곳으로 이동
					} else {
						if (time + 1 + timechk[nr][nc] <= D)
							pq.add(new int[] { nr, nc, time + 1 });// 낮은 곳으로 이동
					}
				}
			}
		}
	}

	static boolean cango(int nr, int nc) {
		if (nr >= 0 && nr < N && nc >= 0 && nc < M && !visit[nr][nc])
			return true;
		return false;
	}
}