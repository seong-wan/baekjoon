import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] maps;
	static ArrayList<Point> points;
	static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		maps = new int[N][M];

		points = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				maps[i][j] = Integer.parseInt(st.nextToken());
				if (maps[i][j] != 0 && maps[i][j] != 6) {
					points.add(new Point(i, j, maps[i][j]));
				}
			}
		}
		for (int i = points.size() - 1; i >= 0; i--) {
			int r = points.get(i).r;
			int c = points.get(i).c;
			int num = points.get(i).num;

			// 5번 완료 - 회전 필요 없음
			if (num == 5) {
				points.remove(i);

				// 행 바꿈
				for (int j = c - 1; j >= 0; j--) {
					if (maps[r][j] == 6)
						break;
					if (maps[r][j] == 0)
						maps[r][j] = 5;
				}
				for (int j = c; j < M; j++) {
					if (maps[r][j] == 6)
						break;
					if (maps[r][j] == 0)
						maps[r][j] = 5;
				}
				// 열 바꿈
				for (int j = r - 1; j >= 0; j--) {
					if (maps[j][c] == 6)
						break;
					if (maps[j][c] == 0)
						maps[j][c] = 5;
				}
				for (int j = r; j < N; j++) {
					if (maps[j][c] == 6)
						break;
					if (maps[j][c] == 0)
						maps[j][c] = 5;
				}

			}
		}

		dfs(0, maps);

		System.out.println(result);

	}

	static void dfs(int idx, int[][] map) {

		if (idx == points.size()) {
			result = Math.min(result, countZero(map));
			return;
		}

		int r = points.get(idx).r;
		int c = points.get(idx).c;
		int num = points.get(idx).num;
		int[][] tmp;

		if (num == 1) {

			tmp = copyMap(map);
			checkLeft(tmp, r, c);
			dfs(idx + 1, tmp);

			tmp = copyMap(map);
			checkRight(tmp, r, c);
			dfs(idx + 1, tmp);

			tmp = copyMap(map);
			checkUp(tmp, r, c);
			dfs(idx + 1, tmp);

			tmp = copyMap(map);
			checkDown(tmp, r, c);
			dfs(idx + 1, tmp);

		} else if (num == 2) {

			tmp = copyMap(map);
			checkLeft(tmp, r, c);
			checkRight(tmp, r, c);
			dfs(idx + 1, tmp);

			tmp = copyMap(map);
			checkUp(tmp, r, c);
			checkDown(tmp, r, c);
			dfs(idx + 1, tmp);

		} else if (num == 3) {

			tmp = copyMap(map);
			checkLeft(tmp, r, c);
			checkUp(tmp, r, c);
			dfs(idx + 1, tmp);

			tmp = copyMap(map);
			checkUp(tmp, r, c);
			checkRight(tmp, r, c);
			dfs(idx + 1, tmp);

			tmp = copyMap(map);
			checkDown(tmp, r, c);
			checkRight(tmp, r, c);
			dfs(idx + 1, tmp);

			tmp = copyMap(map);
			checkDown(tmp, r, c);
			checkLeft(tmp, r, c);
			dfs(idx + 1, tmp);

		} else if (num == 4) {

			tmp = copyMap(map);
			checkLeft(tmp, r, c);
			checkUp(tmp, r, c);
			checkDown(tmp, r, c);
			dfs(idx + 1, tmp);

			tmp = copyMap(map);
			checkLeft(tmp, r, c);
			checkRight(tmp, r, c);
			checkDown(tmp, r, c);
			dfs(idx + 1, tmp);

			tmp = copyMap(map);
			checkLeft(tmp, r, c);
			checkRight(tmp, r, c);
			checkUp(tmp, r, c);
			dfs(idx + 1, tmp);

			tmp = copyMap(map);
			checkRight(tmp, r, c);
			checkUp(tmp, r, c);
			checkDown(tmp, r, c);
			dfs(idx + 1, tmp);

		}
	}

	public static int[][] copyMap(int[][] map) {
		int[][] tmp = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				tmp[i][j] = map[i][j];
			}
		}
		return tmp;
	}

	public static void checkLeft(int[][] map, int x, int y) {
		for (int i = y - 1; i >= 0; i--) {
			if (map[x][i] == 6)
				return;
			if (map[x][i] != 0)
				continue;
			map[x][i] = -1;
		}
	}

	public static void checkRight(int[][] map, int x, int y) {
		for (int i = y + 1; i < M; i++) {
			if (map[x][i] == 6)
				return;
			if (map[x][i] != 0)
				continue;
			map[x][i] = -1;
		}
	}

	public static void checkUp(int[][] map, int x, int y) {
		for (int i = x - 1; i >= 0; i--) {
			if (map[i][y] == 6)
				return;
			if (map[i][y] != 0)
				continue;
			map[i][y] = -1;
		}
	}

	public static void checkDown(int[][] map, int x, int y) {
		for (int i = x + 1; i < N; i++) {
			if (map[i][y] == 6)
				return;
			if (map[i][y] != 0)
				continue;
			map[i][y] = -1;
		}
	}

	static int countZero(int[][] map) {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {

				if (map[i][j] == 0) {

					cnt++;
				}
			}
		}
		return cnt;
	}

	static class Point {
		int r, c, num;

		Point(int r, int c, int num) {
			this.r = r;
			this.c = c;
			this.num = num;
		}
	}

}