import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

//8초 후에도 움직일 수 있는 방법이 있다면 방해물이 없으므로 무조건 YES 처리
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static boolean[][] map = new boolean[8][8];
	static int[] dr = {0, -1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dc = {0, 0, -1, -1, -1, 0, 1, 1, 1};
	static boolean[][][] visit = new boolean[8][8][8];

	public static void main(String[] args) throws Exception {
		for (int i = 0; i < 8; i++) {
			String s = br.readLine();
			for (int j = 0; j < 8; j++) {
				if (s.charAt(j) == '.')
					map[i][j] = true;
			}
		}

		System.out.print(search());
	}

	static int search() {
		Deque<int[]> queue = new ArrayDeque<>();
		int time = 1;
		queue.add(new int[] {7, 0});
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				int[] cur = queue.poll();
				int cr = cur[0];
				int cc = cur[1];

				//현재 위치로 벽이 이동해온 경우
				if (!map[cr][cc])
					continue;

				//모든 방해물이 사라지거나 도착한 경우
				if (time == 9 || (cr == 0 && cc == 7))
					return 1;

				for (int dir = 0; dir < 9; dir++) {
					int nr = cr + dr[dir];
					int nc = cc + dc[dir];

					if (canGo(nr, nc) && !visit[nr][nc][time - 1]) {
						visit[nr][nc][time - 1] = true;
						queue.add(new int[] {nr, nc});
					}

				}
			}

			//시간이 다 지난 시점에서 움직일 수 있는 방법이 없을 때
			if (time == 9)
				break;

			wallMove(time);

			time++;
		}

		return 0;
	}

	static boolean canGo(int r, int c) {
		return r >= 0 && r < 8 && c >= 0 && c < 8 && map[r][c];
	}

	static void wallMove(int time) {
		for (int i = 7; i >= time; i--) {
			for (int j = 0; j < 8; j++) {
				map[i][j] = map[i - 1][j];
			}
		}

		Arrays.fill(map[time - 1], true);
	}
}