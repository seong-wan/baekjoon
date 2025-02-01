import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static char[][] map = new char[5][5];
	static boolean[][] visit;
	static int[] tgt = new int[7];
	static int ans;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};

	public static void main(String[] args) throws Exception {
		for (int i = 0; i < 5; i++) {
			map[i] = br.readLine().toCharArray();
		}

		comb(0, 0);

		System.out.println(ans);
	}

	static void comb(int idx, int num) {
		if (idx == 7) {
			int SCount = 0;
			int YCount = 0;
			visit = new boolean[5][5];

			for (int i = 0; i < 7; i++) {
				int r = tgt[i] / 5;
				int c = tgt[i] % 5;

				if (map[r][c] == 'S')
					SCount++;
				else
					YCount++;

				visit[r][c] = true;
			}

			if (YCount >= 4)
				return;

			if (search())
				ans++;

			return;
		}

		if (num == 25)
			return;

		tgt[idx] = num;
		comb(idx + 1, num + 1);
		comb(idx, num + 1);
	}

	static boolean search() {
		Deque<int[]> queue = new ArrayDeque<>();
		int cnt = 1;

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (visit[i][j]) {
					queue.add(new int[] {i, j});
					visit[i][j] = false;
					break;
				}
			}

			if (!queue.isEmpty())
				break;
		}

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int cr = cur[0];
			int cc = cur[1];

			for (int dir = 0; dir < 4; dir++) {
				int nr = cr + dr[dir];
				int nc = cc + dc[dir];

				if (canGo(nr, nc)) {
					visit[nr][nc] = false;
					queue.add(new int[] {nr, nc});
					cnt++;

					if (cnt == 7)
						return true;
				}
			}
		}
		return false;
	}

	static boolean canGo(int r, int c) {
		return r >= 0 && r < 5 && c >= 0 && c < 5 && visit[r][c];
	}
}