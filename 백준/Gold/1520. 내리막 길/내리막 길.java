import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int M, N, cnt, map[][], memoi[][];
	static int dr[] = { -1, 0, 1, 0 };
	static int dc[] = { 0, -1, 0, 1 };// 상,좌,하,우

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[M][N];
		memoi = new int[M][N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				memoi[i][j] = -1;
			}

		} // map입력
		System.out.println(dfs(0, 0));

	}

	static int dfs(int r, int c) {
		if (r == M - 1 && c == N - 1) {
			return 1;
		}
		if (memoi[r][c] != -1)// 값이 저장되어 있지 않다면
			return memoi[r][c];
		memoi[r][c] = 0;
		for (int dir = 0; dir < 4; dir++) {
			int nr = r + dr[dir];
			int nc = c + dc[dir];
			if (cango(nr, nc) && map[nr][nc] < map[r][c]) {
				memoi[r][c] += dfs(nr, nc);
			}
		}
		return memoi[r][c];

	}

	static boolean cango(int r, int c) {
		if (r >= 0 && r < M && c >= 0 && c < N)
			return true;
		return false;
	}
}
