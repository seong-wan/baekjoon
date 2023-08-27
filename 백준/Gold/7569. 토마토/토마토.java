import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int M, N, H, depth = -1;// 처음 토마토의 위치부터 depth가 증가하므로 조정을 위해 -1
	static int[][][] map;
	static List<int[]> tomato = new ArrayList<>();
	static int[] dr = { -1, 0, 1, 0, 0, 0 };
	static int[] dc = { 0, -1, 0, 1, 0, 0 };// 6방향 탐색
	static int[] dh = { 0, 0, 0, 0, 1, -1 };

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		map = new int[H][N][M];
		for (int h = 0; h < H; h++) {
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[h][i][j] = Integer.parseInt(st.nextToken());
					if (map[h][i][j] == 1)
						tomato.add(new int[] { h, i, j });// 토마토의 위치
				} // map 입력하면서 토마토의 위치를 찾아서 리스트에 저장
			}
		}
		bfs();
		System.out.println(ripe() ? depth : -1);
		// 다 익었다면 최소 날짜를 다 안 익었다면 -1출력
	}

	static void bfs() {
		Queue<int[]> queue = new ArrayDeque<>();
		for (int i = 0; i < tomato.size(); i++) {
			queue.add(tomato.get(i));
		}
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				int[] cur = queue.poll();
				for (int dir = 0; dir < 6; dir++) {
					int nh = cur[0] + dh[dir];
					int nr = cur[1] + dr[dir];
					int nc = cur[2] + dc[dir];
					if (cango(nh, nr, nc)) {
						map[nh][nr][nc] = 1;// 방문체크
						queue.add(new int[] { nh, nr, nc });
					}
				}
			}
			depth++;
		}
	}

	static boolean cango(int h, int r, int c) {
		if (h >= 0 && h < H && r >= 0 && r < N && c >= 0 && c < M && map[h][r][c] == 0)
			return true;
		else
			return false;
	}

	static boolean ripe() {
		for (int h = 0; h < H; h++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[h][i][j] == 0)
						return false;
				}

			}
		}
		return true;
	}// 다 익었는지 확인

}
