import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int R, S, ans;
	static boolean[][] map;
	static int[] dr = {0, -1, -1, -1, 0, 1, 1, 1};
	static int[] dc = {-1, -1, 0, 1, 1, 1, 0, -1};

	public static void main(String[] args) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());

		map = new boolean[R][S];

		for (int i = 0; i < R; i++) {
			String s = br.readLine();
			for (int j = 0; j < S; j++) {
				if (s.charAt(j) != '.')
					map[i][j] = true;
			}
		}

		int count = 0;

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < S; j++) {
				int temp = 0;
				for (int dir = 0; dir < 8; dir++) {
					int nr = i + dr[dir];
					int nc = j + dc[dir];

					if (canGo(nr, nc) && map[nr][nc]) {
						if (dir < 4 && map[i][j])
							continue;

						temp++;
					}
				}

				if (!map[i][j])
					count = Math.max(count, temp);
				else
					ans += temp;
			}
		}

		System.out.println(ans + count);

	}

	static boolean canGo(int r, int c) {
		return r >= 0 && r < R && c >= 0 && c < S;
	}
}