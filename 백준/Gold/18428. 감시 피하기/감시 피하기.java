import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static char[][] map;
	static int[] obstacle = new int[3];
	static StringTokenizer st;
	static List<int[]> teachers = new ArrayList<>();
	static boolean canHide = false;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());

		map = new char[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = st.nextToken().charAt(0);
				if (map[i][j] == 'T') {
					teachers.add(new int[] {i, j});
				}
			}
		}

		comb(0, 0);

		System.out.print(canHide ? "YES" : "NO");
	}

	static void comb(int idx, int num) {
		if (canHide)
			return;

		if (idx == 3) {
			int r1 = obstacle[0] / N;
			int c1 = obstacle[0] % N;
			int r2 = obstacle[1] / N;
			int c2 = obstacle[1] % N;
			int r3 = obstacle[2] / N;
			int c3 = obstacle[2] % N;

			if (!isEmpty(r1, c1, r2, c2, r3, c3))
				return;

			map[r1][c1] = 'O';
			map[r2][c2] = 'O';
			map[r3][c3] = 'O';

			teacherCheck();

			map[r1][c1] = 'X';
			map[r2][c2] = 'X';
			map[r3][c3] = 'X';

			return;
		}

		if (num == N * N)
			return;

		obstacle[idx] = num;

		comb(idx + 1, num + 1);
		comb(idx, num + 1);
	}

	static void teacherCheck() {
		for (int[] teacher : teachers) {
			int cr = teacher[0];
			int cc = teacher[1];

			for (int dir = 0; dir < 4; dir++) {
				int nr = cr;
				int nc = cc;

				while (true) {
					nr += dr[dir];
					nc += dc[dir];

					if (!canGo(nr, nc))
						break;

					//학생을 본 경우
					if (map[nr][nc] == 'S')
						return;
				}
			}
		}

		canHide = true;
	}

	static boolean canGo(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N && map[r][c] != 'O';
	}

	static boolean isEmpty(int r1, int c1, int r2, int c2, int r3, int c3) {
		return map[r1][c1] == 'X' && map[r2][c2] == 'X' && map[r3][c3] == 'X';
	}
}