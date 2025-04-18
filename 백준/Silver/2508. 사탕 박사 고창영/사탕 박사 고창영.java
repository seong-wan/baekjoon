import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int T, r, c;
	static char[][] map;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			int ans = 0;
			br.readLine();
			st = new StringTokenizer(br.readLine());

			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());

			map = new char[r][c];
			for (int i = 0; i < r; i++) {
				map[i] = br.readLine().toCharArray();
			}

			for (int i = 0; i < r; i++) {
				for (int j = 0; j < c; j++) {
					if (map[i][j] == 'o') {
						if (checkLR(i, j) || checkUD(i, j))
							ans++;
					}
				}
			}

			sb.append(ans).append("\n");
		}
		System.out.println(sb);
	}

	static boolean checkLR(int cr, int cc) {
		int left = cc - 1;
		int right = cc + 1;

		if (left < 0 || right >= c)
			return false;

		return map[cr][left] == '>' && map[cr][right] == '<';
	}

	static boolean checkUD(int cr, int cc) {
		int up = cr - 1;
		int down = cr + 1;

		if (up < 0 || down >= r)
			return false;

		return map[up][cc] == 'v' && map[down][cc] == '^';
	}
}