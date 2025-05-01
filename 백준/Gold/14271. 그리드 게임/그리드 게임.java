import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, M, K, count;
	static boolean[][] map = new boolean[3051][3051];
	static Deque<int[]> queue = new ArrayDeque<>();
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};

	public static void main(String[] args) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				if (s.charAt(j) == 'o') {
					map[i + 1500][j + 1500] = true;
					count++;
					queue.add(new int[] {i + 1500, j + 1500});
				}
			}
		}

		K = Integer.parseInt(br.readLine());
		bfs();
		System.out.println(count);
	}

	static void bfs() {
		int depth = 0;

		while (!queue.isEmpty()) {
			if (depth == K)
				return;

			int size = queue.size();
			for (int i = 0; i < size; i++) {
				int[] cur = queue.poll();
				int cr = cur[0];
				int cc = cur[1];

				for (int dir = 0; dir < 4; dir++) {
					int nr = cr + dr[dir];
					int nc = cc + dc[dir];

					if (!map[nr][nc]) {
						count++;
						map[nr][nc] = true;
						queue.add(new int[] {nr, nc});
					}
				}
			}

			depth++;
		}
	}
}