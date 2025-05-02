import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static char[][] A, B;
	static int N, M;
	static boolean[][] check;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};

	public static void main(String[] args) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		A = new char[N][M];
		B = new char[N][M];
		check = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			A[i] = br.readLine().toCharArray();
		}

		for (int i = 0; i < N; i++) {
			B[i] = br.readLine().toCharArray();
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (A[i][j] != B[i][j]) {
					if (check[i][j]) {
						System.out.println("NO");
						return;
					}

					bfs(i, j);
				}
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (A[i][j] != B[i][j]) {
					System.out.println("NO");
					return;
				}
			}
		}

		System.out.println("YES");
	}

	static void bfs(int r, int c) {
		Deque<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] {r, c});
		check[r][c] = true;
		char before = A[r][c];
		char after = B[r][c];
		A[r][c] = after;

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int cr = cur[0];
			int cc = cur[1];

			for (int dir = 0; dir < 4; dir++) {
				int nr = cr + dr[dir];
				int nc = cc + dc[dir];

				if (canGo(nr, nc) && A[nr][nc] == before) {
					check[nr][nc] = true;
					A[nr][nc] = after;
					queue.add(new int[] {nr, nc});
				}
			}
		}
	}

	static boolean canGo(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M && !check[r][c];
	}
}