import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, K, S, X, Y;
	static int[][] map;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, -1, 0, 1 };

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N + 1][N + 1];// 0은 더미
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // 맵 입력

		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());

		bfs();
		System.out.println(map[X][Y]);
	}

	static void bfs() {
		Queue<int[]> queue = new ArrayDeque<>();
		// 바이러스의 값 기준 오름차순 정렬
		int time = 0;
		for (int k = 1; k <= K; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (map[i][j] == k)
						queue.add(new int[] { i, j, k });
				}
			}
		}
		// 맵의 모든 바이러스를 번호 순으로 큐에 담음
		while (!queue.isEmpty()) {
			if (time == S)
				return;
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				int[] cur = queue.poll();
				int cr = cur[0];
				int cc = cur[1];
				int num = cur[2];
				for (int dir = 0; dir < 4; dir++) {
					int nr = cr + dr[dir];
					int nc = cc + dc[dir];
					if (cango(nr, nc)) {
						map[nr][nc] = num;
						queue.add(new int[] { nr, nc, num });
					}
				}
			}
			time++;
		}
	}

	static boolean cango(int nr, int nc) {
		if (nr >= 1 && nr <= N && nc >= 1 && nc <= N && map[nr][nc] == 0)
			return true;
		return false;
	}

}