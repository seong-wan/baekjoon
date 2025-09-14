import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int m, n, crossRoadCount;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static char[][] map;
	static int[] start, end = new int[2];
	static int[][] crossRoads;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	static boolean[][] visit;

	public static void main(String[] args) throws Exception {
		while (true) {
			st = new StringTokenizer(br.readLine());
			m = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());

			if (m == 0)
				break;

			map = new char[m][n];
			visit = new boolean[m][n];
			crossRoadCount = 0;
			start = new int[2];

			for (int i = 0; i < m; i++) {
				String s = br.readLine();
				for (int j = 0; j < n; j++) {
					map[i][j] = s.charAt(j);

					if (map[i][j] == '#' || map[i][j] == '.')
						continue;

					if (map[i][j] == 'A') {
						start[0] = i;
						start[1] = j;
					} else if (map[i][j] == 'B') {
						end[0] = i;
						end[1] = j;
					} else {
						crossRoadCount++;
					}
				}
			}

			crossRoads = new int[crossRoadCount][3];
			for (int i = 0; i < crossRoadCount; i++) {
				st = new StringTokenizer(br.readLine());
				st.nextToken();
				int dir = 1;

				//세로 시작
				if (st.nextToken().charAt(0) == '|')
					dir = 0;

				crossRoads[i][0] = dir;
				crossRoads[i][1] = Integer.parseInt(st.nextToken());
				crossRoads[i][2] = Integer.parseInt(st.nextToken());
			}

			br.readLine();

			int result = dijk();
			sb.append(result == -1 ? "impossible" : result).append("\n");
		}

		System.out.print(sb);
	}

	static int dijk() {
		PriorityQueue<int[]> pq = new PriorityQueue<>((e1, e2) -> e1[2] - e2[2]);
		pq.add(new int[] {start[0], start[1], 0});

		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int cr = cur[0];
			int cc = cur[1];
			int time = cur[2];

			if (visit[cr][cc])
				continue;

			if (cr == end[0] && cc == end[1])
				return time;

			visit[cr][cc] = true;

			for (int dir = 0; dir < 4; dir++) {
				int nr = cr + dr[dir];
				int nc = cc + dc[dir];

				if (canGo(nr, nc)) {
					//일반 도로나 도착지인 경우
					if (map[nr][nc] == '#' || map[nr][nc] == 'B')
						pq.add(new int[] {nr, nc, time + 1});
					else {
						int roadNum = map[nr][nc] - '0';
						int curDir = dir % 2;

						int restTime = time % (crossRoads[roadNum][1] + crossRoads[roadNum][2]) + 1;

						//세로인 경우
						if (crossRoads[roadNum][0] == 0 && restTime <= crossRoads[roadNum][2] ||
							crossRoads[roadNum][0] == 1 && restTime > crossRoads[roadNum][1]) {

							//현재 세로라면
							if (curDir == 0)
								pq.add(new int[] {nr, nc, time + 1});
							else
								pq.add(new int[] {nr, nc,
									crossRoads[roadNum][0] == 0 ? time + (crossRoads[roadNum][2] - restTime) + 2 :
										time + (crossRoads[roadNum][1] + crossRoads[roadNum][2] - restTime) + 2});
						}

						//가로인 경우
						if (crossRoads[roadNum][0] == 1 && restTime <= crossRoads[roadNum][1] ||
							crossRoads[roadNum][0] == 0 && restTime > crossRoads[roadNum][2]) {

							//현재 가로라면
							if (curDir == 1)
								pq.add(new int[] {nr, nc, time + 1});
							else
								pq.add(new int[] {nr, nc,
									crossRoads[roadNum][0] == 1 ? time + (crossRoads[roadNum][1] - restTime) + 2 :
										time + (crossRoads[roadNum][1] + crossRoads[roadNum][2] - restTime) + 2});
						}
					}
				}
			}
		}

		return -1;
	}

	static boolean canGo(int r, int c) {
		return r >= 0 && r < m && c >= 0 && c < n && map[r][c] != '.' && !visit[r][c];
	}
}