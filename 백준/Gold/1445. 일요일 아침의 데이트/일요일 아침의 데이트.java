import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, M;
	static char[][] map;
	static boolean[][] nextTrash;
	static boolean[][] visit;
	static int[] start = new int[2];
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};

	public static void main(String[] args) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		nextTrash = new boolean[N][M];
		visit = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);

				if (map[i][j] == 'S') {
					start[0] = i;
					start[1] = j;
				}
			}
		}

		checkNextTrash();

		dijk();
	}

	static void dijk() {
		PriorityQueue<int[]> pq = new PriorityQueue<>((e1, e2) -> e1[2] == e2[2] ? e1[3] - e2[3] : e1[2] - e2[2]);
		pq.add(new int[] {start[0], start[1], 0, nextTrash[start[0]][start[1]] ? 1 : 0});

		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int cr = cur[0];
			int cc = cur[1];
			//거쳐간 쓰레기 수
			int throughCount = cur[2];
			//옆에 쓰레기가 있는 길을 거쳐간 수
			int nextCount = cur[3];

			if (map[cr][cc] == 'F') {
				System.out.println(throughCount + " " + nextCount);
				return;
			}

			if (visit[cr][cc])
				continue;

			visit[cr][cc] = true;

			for (int dir = 0; dir < 4; dir++) {
				int nr = cr + dr[dir];
				int nc = cc + dc[dir];

				if (canGo(nr, nc) && !visit[nr][nc]) {
					pq.add(new int[] {nr, nc, map[nr][nc] == 'g' ? throughCount + 1 : throughCount,
						nextTrash[nr][nc] ? nextCount + 1 : nextCount});
				}
			}
		}
	}

	static void checkNextTrash() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] != '.')
					continue;

				boolean check = false;
				for (int dir = 0; dir < 4; dir++) {
					int nr = i + dr[dir];
					int nc = j + dc[dir];

					if (canGo(nr, nc) && map[nr][nc] == 'g') {
						check = true;
						break;
					}
				}

				if (check)
					nextTrash[i][j] = true;
			}
		}
	}

	static boolean canGo(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}
}