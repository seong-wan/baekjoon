import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int R, C, T;
	static int[][] map, temp;
	static int[] cleaner = new int[2];
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, -1, 0, 1 };

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		int idx = 0;
		map = new int[R][C];
		temp = new int[R][C];

		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == -1)
					cleaner[idx++] = i;// 공기청정기의 행 정보 입력
			}
		} // 맵 입력

		for (int i = 0; i < T; i++) {
			extend();
			airclean();
		}
		System.out.println(find());
	}

	static int find() {
		int ans = 2;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				ans += map[i][j];
			}
		}
		return ans;
	}

	static void airclean() {
		{
			for (int i = cleaner[0] - 1; i >= 1; i--) {
				map[i][0] = map[i - 1][0];
			}
			for (int i = 0; i < C - 1; i++) {
				map[0][i] = map[0][i + 1];
			}
			for (int i = 0; i < cleaner[0]; i++) {
				map[i][C - 1] = map[i + 1][C - 1];
			}
			for (int i = C - 1; i >= 2; i--) {
				map[cleaner[0]][i] = map[cleaner[0]][i - 1];
			}
			map[cleaner[0]][1] = 0;
		} // 공기청정기 위쪽 반시계 회전

		{
			for (int i = cleaner[1] + 1; i < R - 1; i++) {
				map[i][0] = map[i + 1][0];
			}
			for (int i = 0; i < C - 1; i++) {
				map[R - 1][i] = map[R - 1][i + 1];
			}
			for (int i = R - 1; i >= cleaner[1] + 1; i--) {
				map[i][C - 1] = map[i - 1][C - 1];
			}
			for (int i = C - 1; i >= 2; i--) {
				map[cleaner[1]][i] = map[cleaner[1]][i - 1];
			}
			map[cleaner[1]][1] = 0;
		} // 공기청정기 밑쪽 반시계 회전
	}

	static void extend() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] > 0) {// 미세 먼지가 있는 칸을 발견했을 때
					int edust = map[i][j] / 5;// 확산될 미세먼지의 양
					for (int dir = 0; dir < 4; dir++) {
						int nr = i + dr[dir];
						int nc = j + dc[dir];
						if (cango(nr, nc)) {
							temp[nr][nc] += edust;
							temp[i][j] -= edust;
						} // 확산이 가능하면 미세먼지 확산
					}
				}
			}
		}
		sum();
		tempclean();

	}

	static boolean cango(int nr, int nc) {
		if (nr >= 0 && nr < R && nc >= 0 && nc < C && map[nr][nc] != -1)
			return true;
		return false;
	}

	static void tempclean() {
		for (int i = 0; i < R; i++) {
			Arrays.fill(temp[i], 0);
		}
	}// 템프 배열 초기화

	static void sum() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				map[i][j] += temp[i][j];
			}
		}
	}// map과 temp 배열 합치기
}