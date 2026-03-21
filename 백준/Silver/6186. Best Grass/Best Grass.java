import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int R, C, cnt;
	static char[][] map;
	static int[] dr = {1, 0};
	static int[] dc = {0, 1};

	public static void main(String[] args) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] == '#') {
					cnt++;
					map[i][j] = '.';

					for (int dir = 0; dir < 2; dir++) {
						int nr = i + dr[dir];
						int nc = j + dc[dir];

						if (canGo(nr, nc))
							map[nr][nc] = '.';
					}
				}
			}
		}

		System.out.print(cnt);
	}

	static boolean canGo(int r, int c) {
		return r >= 0 && r < R && c >= 0 && c < C && map[r][c] == '#';
	}
}