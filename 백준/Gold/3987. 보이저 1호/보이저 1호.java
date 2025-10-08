import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, ans;
	static char[][] map;
	static int[] start = new int[2];
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};

	static char ansDir = 'U';

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

		st = new StringTokenizer(br.readLine());
		start[0] = Integer.parseInt(st.nextToken()) - 1;
		start[1] = Integer.parseInt(st.nextToken()) - 1;

		simul();

		System.out.println(ansDir);
		System.out.print(ans == Integer.MAX_VALUE ? "Voyager" : ans);
	}

	static void simul() {
		for (int dir = 0; dir < 4; dir++) {
			boolean[][][] visit = new boolean[N][M][4];

			int time = 1;
			int cr = start[0];
			int cc = start[1];
			int curDir = dir;
			visit[cr][cc][curDir] = true;

			while (true) {
				int nr = cr + dr[curDir];
				int nc = cc + dc[curDir];

				if (!canGo(nr, nc)) {
					if (time > ans) {
						ans = time;

						switch (dir) {
							case 0: {
								ansDir = 'U';
								break;
							}

							case 1: {
								ansDir = 'R';
								break;
							}
							case 2: {
								ansDir = 'D';
								break;
							}
							case 3: {
								ansDir = 'L';
								break;
							}
						}
					}
					break;
				}

				if (visit[nr][nc][curDir]) {
					ans = Integer.MAX_VALUE;

					switch (dir) {
						case 0: {
							ansDir = 'U';
							break;
						}
						case 1: {
							ansDir = 'R';
							break;
						}
						case 2: {
							ansDir = 'D';
							break;
						}
						case 3: {
							ansDir = 'L';
							break;
						}
					}
					break;
				}

				if (map[nr][nc] == '\\') {
					switch (curDir) {
						case 0: {
							curDir = 3;
							break;
						}
						case 3: {
							curDir = 0;
							break;
						}
						case 2: {
							curDir = 1;
							break;
						}
						case 1: {
							curDir = 2;
							break;
						}
					}
				}

				if (map[nr][nc] == '/') {
					switch (curDir) {
						case 0: {
							curDir = 1;
							break;
						}
						case 1: {
							curDir = 0;
							break;
						}
						case 2: {
							curDir = 3;
							break;
						}
						case 3: {
							curDir = 2;
							break;
						}
					}
				}

				cr = nr;
				cc = nc;
				time++;

				visit[nr][nc][curDir] = true;
			}

			if (ans == Integer.MAX_VALUE)
				break;
		}
	}

	static boolean canGo(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M && map[r][c] != 'C';
	}
}