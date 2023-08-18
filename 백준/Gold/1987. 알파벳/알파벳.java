import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int R, C, ans;
	static char[][] map;
	static boolean[] visited = new boolean[26];//알파벳별 방문 표시
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };// 상,하,좌,우

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		} // 맵 입력
		visited[map[0][0] - 65] = true;//말이 있는 처음 위치 알파벳 방문 표시
		dfs(0, 0, 1);//처음 칸 포함이므로 cnt=1로 시작
		System.out.println(ans);
	}

	static void dfs(int r, int c, int cnt) {

		ans = Math.max(ans, cnt);
		for (int dir = 0; dir < 4; dir++) {
			int nr = r + dr[dir];
			int nc = c + dc[dir];
			if (nr >= 0 && nr <= R - 1 && nc >= 0 && nc <= C - 1 && !visited[map[nr][nc] - 65]) {
				visited[map[nr][nc] - 65] = true;//갔던 알파벳 방문 표시
				dfs(nr, nc, cnt + 1);
				visited[map[nr][nc] - 65] = false;//갔던 길 되돌아갈 때 원복
			}
		}
	}

}
