import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int R, C, cntp, cnt, time;// 녹는 치즈의 개수
	static int[][] cheese;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, -1, 0, 1 };

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		cheese = new int[R][C];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				cheese[i][j] = Integer.parseInt(st.nextToken());
			}
		} // cheese 입력

		outair(0, 0);// 처음 외부 공기와 내부 공기 구분
		while (true) {
			afterhour();
			if (cnt == 0)
				break;
		}
		System.out.println(time);
		System.out.println(cntp);
	}

	static void outair(int r, int c) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] { r, c });
		cheese[r][c] = 2;// 외부 공기는 2로 처리
		while (!queue.isEmpty()) {
			int cur[] = queue.poll();
			int cr = cur[0];
			int cc = cur[1];
			for (int dir = 0; dir < 4; dir++) {
				int nr = cr + dr[dir];
				int nc = cc + dc[dir];
				if (cango(nr, nc) && cheese[nr][nc] == 0) {
					queue.add(new int[] { nr, nc });
					cheese[nr][nc] = 2;// 외부 공기는 2로 처리
				}
			}
		}
	}// 처음에 외부 공기와 내부 공기 구분

	static boolean cango(int nr, int nc) {
		if (nr >= 0 && nr < R && nc >= 0 && nc < C)
			return true;
		return false;
	}

	static void afterhour() {
		cnt = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (cheese[i][j] == 1) {
					for (int dir = 0; dir < 4; dir++) {
						int nr = i + dr[dir];
						int nc = j + dc[dir];
						if (cheese[nr][nc] == 2) {
							cnt++;
							cheese[i][j] = 0;// 다음 치즈의 녹음에 영향을 주지 않게 내부 공기로 처리
							break;// 한 면이 외부 공기이므로 탐색 종료
						} // 한 면이 외부 공기라면 치즈를 녹임
					}
				}
			}
		} // 한 시간 후 치즈가 녹은 상태
		if (cnt != 0) {// 녹은 치즈 수가 0이 아니라면
			cntp = cnt;
			// 다 녹기 1시간 전의 녹은 치즈 수 체크를 위해 현재 녹은 치즈 수가 0이 아니라면 그 시간 동안 녹은 치즈 수를 저장
			time++;
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (cheese[i][j] == 0) {
						for (int dir = 0; dir < 4; dir++) {
							int nr = i + dr[dir];
							int nc = j + dc[dir];
							if (cheese[nr][nc] == 2) {
								outair(i, j);
								break;
							}
						}
					}
				}
			}
		}

	}

//	static void print() {
//		for (int i = 0; i < R; i++) {
//			for (int j = 0; j < C; j++) {
//				System.out.print(cheese[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
//
//	}
}
