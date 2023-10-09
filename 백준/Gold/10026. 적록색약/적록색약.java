import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static char[][] map, origin;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, -1, 0, 1 };

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());

		origin = new char[N][N];
		map = new char[N][N];
		for (int i = 0; i < N; i++) {
			origin[i] = br.readLine().toCharArray();
		} // map입력

		find(0);// 색약이 없는 사람
		find(1);// 색약이 있는 사람
	}

	static void find(int mode) {
		mapcopy();// 기존 맵 유지를 위해
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 'R' || map[i][j] == 'G' || map[i][j] == 'B') {
					cnt++;// 다른 구역을 만날 때마다 cnt +1
					bfs(i, j, map[i][j], mode);
				}
			}
		}
		System.out.print(cnt + " ");

	}

	static void bfs(int r, int c, char ch, int mode) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] { r, c });
		map[r][c] = '#';// 방문처리
		while (!queue.isEmpty()) {
			int cur[] = queue.poll();
			int cr = cur[0];
			int cc = cur[1];
			for (int dir = 0; dir < 4; dir++) {
				int nr = cr + dr[dir];
				int nc = cc + dc[dir];
				if (cango(nr, nc)) {
					if (mode == 1 && (ch == 'R' || ch == 'G')) {// 색약이 있고 빨강,초록인 경우
						if (map[nr][nc] == 'R' || map[nr][nc] == 'G') {
							queue.add(new int[] { nr, nc });
							map[nr][nc] = '#';
						}
					} else {
						if (map[nr][nc] == ch) {
							queue.add(new int[] { nr, nc });
							map[nr][nc] = '#';
						}
					}
				}
			}
		}
	}

	static boolean cango(int nr, int nc) {
		if (nr >= 0 && nr < N && nc >= 0 && nc < N)
			return true;
		return false;
	}

	static void mapcopy() {
		for (int i = 0; i < N; i++) {
			map[i] = origin[i].clone();
		}
	}

}