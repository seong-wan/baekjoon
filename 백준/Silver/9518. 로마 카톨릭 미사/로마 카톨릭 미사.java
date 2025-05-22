import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int R, S, ans;
	static int[] location = new int[2];
	static char[][] map;
	static int[] dr = {-1, -1, -1, 0, 1, 1, 1, 0};
	static int[] dc = {-1, 0, 1, 1, 1, 0, -1, -1};

	public static void main(String[] args) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());

		map = new char[R][S];

		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}

		int count = 0;

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < S; j++) {
				int temp = 0;
				if (map[i][j] == '.') {
					for (int dir = 0; dir < 8; dir++) {
						int nr = i + dr[dir];
						int nc = j + dc[dir];

						if (canGo(nr, nc) && map[nr][nc] == 'o')
							temp++;
					}

					if (temp > count) {
						location[0] = i;
						location[1] = j;
						count = temp;
					}
				}
			}
		}

		if (count > 0)
			map[location[0]][location[1]] = 'o';

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < S; j++) {
				if (map[i][j] == 'o') {
					for (int dir = 0; dir < 8; dir++) {
						int nr = i + dr[dir];
						int nc = j + dc[dir];

						if (canGo(nr, nc) && map[nr][nc] == 'o')
							ans++;
					}
				}
			}
		}

		System.out.println(ans / 2);

	}

	static boolean canGo(int r, int c) {
		return r >= 0 && r < R && c >= 0 && c < S;
	}
}