import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int R, C;
	static char[][] map;
	static StringBuilder sb = new StringBuilder();
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};

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
				if (map[i][j] == 'W') {
					boolean canEat = false;
					for (int dir = 0; dir < 4; dir++) {
						int nr = i + dr[dir];
						int nc = j + dc[dir];

						if (canGo(nr, nc) && map[nr][nc] == 'S') {
							canEat = true;
							break;
						}
					}

					if (canEat) {
						System.out.println(0);
						return;
					}
				}
			}
		}

		sb.append(1).append("\n");

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				sb.append(map[i][j] == '.' ? 'D' : map[i][j]);
			}
			sb.append("\n");
		}

		System.out.println(sb);
	}

	static boolean canGo(int r, int c) {
		return r >= 0 && r < R && c >= 0 && c < C;
	}
}
