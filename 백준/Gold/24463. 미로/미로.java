import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, M;
	static StringBuilder sb = new StringBuilder();
	static char[][] map;
	static int[][] check;
	static int[] start = {-1, -1}, end = new int[2];
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};

	public static void main(String[] args) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		check = new int[N][M];

		for (int i = 0; i < N; i++) {
			Arrays.fill(check[i], -1);
		}

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);

				if (map[i][j] == '.') {
					map[i][j] = '@';

					if (i == 0 || j == 0 || i == N - 1 || j == M - 1) {
						if (start[0] == -1) {
							start[0] = i;
							start[1] = j;
						} else {
							end[0] = i;
							end[1] = j;
						}
					}
				}

			}
		}

		bfs();
		tracking();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(map[i][j]);
			}
			sb.append("\n");
		}

		System.out.print(sb);
	}

	static void tracking() {
		int cr = end[0];
		int cc = end[1];
		map[cr][cc] = '.';

		while (check[cr][cc] != 0) {
			int prevR = check[cr][cc] / M;
			int prevC = check[cr][cc] % M;

			cr = prevR;
			cc = prevC;

			map[cr][cc] = '.';
		}
	}

	static void bfs() {
		Deque<int[]> queue = new ArrayDeque<>();
		queue.add(start);
		check[start[0]][start[1]] = 0;

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int cr = cur[0];
			int cc = cur[1];

			for (int dir = 0; dir < 4; dir++) {
				int nr = cr + dr[dir];
				int nc = cc + dc[dir];

				if (canGo(nr, nc)) {
					check[nr][nc] = cr * M + cc;
					queue.add(new int[] {nr, nc});
				}
			}
		}
	}

	static boolean canGo(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M && map[r][c] == '@' && check[r][c] == -1;
	}
}