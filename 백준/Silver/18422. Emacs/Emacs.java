import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, M, ans;
	static char[][] map;
	static StringTokenizer st;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == '*') {
					bfs(i, j);
					ans++;
				}
			}
		}

		System.out.print(ans);
	}

	static void bfs(int r, int c) {
		Deque<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] {r, c});
		map[r][c] = '.';

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int cr = cur[0];
			int cc = cur[1];

			for (int dir = 0; dir < 4; dir++) {
				int nr = cr + dr[dir];
				int nc = cc + dc[dir];

				if (canGo(nr, nc)) {
					map[nr][nc] = '.';
					queue.add(new int[] {nr, nc});
				}
			}
		}
	}

	static boolean canGo(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M && map[r][c] == '*';
	}
}