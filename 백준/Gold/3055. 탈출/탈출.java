import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int R, C;
	static char[][] map;
	static Queue<int[]> water = new ArrayDeque<>();
	static int[] dochi = new int[2]; // 고슴도치의 r,c좌표
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, -1, 0, 1 };

	public static void main(String[] args) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			String s = br.readLine();
			map[i] = s.toCharArray();
		} // map 입력

		find();
		bfs();
	}

	static void find() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] == 'S') {
					dochi[0] = i;
					dochi[1] = j;// 고슴도치의 시작 저장
				} else if (map[i][j] == '*') {
					water.add(new int[] { i, j });
				} // 물의 좌표들을 큐에 담음
			}
		}

	}

	static void bfs() {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(dochi);
		int cnt = 0;
		while (!queue.isEmpty()) {
			extend();// 물의 확산
			cnt++;
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				int[] cur = queue.poll();
				int cr = cur[0];
				int cc = cur[1];
				for (int dir = 0; dir < 4; dir++) {
					int nr = cr + dr[dir];
					int nc = cc + dc[dir];
					if (cango(nr, nc)) {
						if (map[nr][nc] == 'D') {
							System.out.println(cnt);
							return;// 비버굴에 도착한 경우
						} else if (map[nr][nc] == '.') {
							map[nr][nc] = 'S';// 방문처리
							queue.add(new int[] { nr, nc });
						} // 물이 있지 않은 빈 땅을 밟은 경우
					}
				}
			}
		}
		System.out.println("KAKTUS");// 탐색이 끝났음에도 비버굴에 도착하지 못한 경우

	}

	static void extend() {
		int size = water.size();
		for (int i = 0; i < size; i++) {
			int[] cur = water.poll();
			int cr = cur[0];
			int cc = cur[1];
			for (int dir = 0; dir < 4; dir++) {
				int nr = cr + dr[dir];
				int nc = cc + dc[dir];
				if (cango(nr, nc) && map[nr][nc] != 'D' && map[nr][nc] != '*') {
					map[nr][nc] = '*';
					water.add(new int[] { nr, nc });
				}
			}
		}

	}

	static boolean cango(int nr, int nc) {
		if (nr >= 0 && nr < R && nc >= 0 && nc < C && map[nr][nc] != 'X')
			return true;
		return false;
	}

}