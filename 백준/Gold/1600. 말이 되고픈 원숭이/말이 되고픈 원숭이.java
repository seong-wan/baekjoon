import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int K, W, H, ans;// 입력값
	static int[][] map;
	static int[] drm = { -1, 0, 1, 0 };
	static int[] dcm = { 0, -1, 0, 1 };
	static int[] drh = { -1, -2, -2, -1, 1, 2, 2, 1 };
	static int[] dch = { -2, -1, 1, 2, 2, 1, -1, -2 };
	static boolean visit[][][];

	public static void main(String[] args) throws Exception {
		K = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[H][W];
		visit = new boolean[H][W][K + 1];
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}

		}
		bfs();

	}

	static void bfs() {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] { 0, 0, 0 });// r,c,말로 이동한 횟수
		visit[0][0][0] = true;
		int depth = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int k = 0; k < size; k++) {
				int[] cur = queue.poll();
				int cr = cur[0];
				int cc = cur[1];
				int cnt = cur[2];
				if (cr == H - 1 && cc == W - 1) {
					System.out.println(depth);
					return;
				}
				for (int i = 0; i < 4; i++) {
					int nr = cr + drm[i];
					int nc = cc + dcm[i];
					if (cango(nr, nc, cnt)) {
						visit[nr][nc][cnt] = true;
						queue.add(new int[] { nr, nc, cnt });
					}
				}
				if (cnt < K) {
					for (int i = 0; i < 8; i++) {
						int nr = cr + drh[i];
						int nc = cc + dch[i];
						if (cango(nr, nc, cnt + 1)) {
							visit[nr][nc][cnt + 1] = true;
							queue.add(new int[] { nr, nc, cnt + 1 });
						}

					}
				}
			}
			depth++;
		}
		System.out.println(-1);
	}

	static boolean cango(int r, int c, int cnt) {
		if (r >= 0 && r < H && c >= 0 && c < W && !visit[r][c][cnt] && map[r][c] != 1)
			return true;
		return false;
	}
}