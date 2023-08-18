import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, T, ans = Integer.MAX_VALUE, ans_sword = Integer.MAX_VALUE;// 맵의 크기,제한 시간,답
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };// 움직일 수 있는 방향(상,하,좌,우)
	static int[][] map;
	static int[] sword;// 검의 위치를 저장

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new int[N + 1][M + 1];// 1번부터 시작하므로 0은 dummy
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2)
					sword = new int[] { i, j };// 검의 위치
			}
		} // 맵 입력을 하면서 검의 위치를 찾음
		bfs(1, 1);
		ans = Math.min(ans, ans_sword);
		if (ans <= T) {
			System.out.println(ans);// 제한 시간 내라면 ans 출력
		} else
			System.out.println("Fail");// 제한 시간 밖이라면 Fail 출력

	}

	static void bfs(int r, int c) {
		Queue<int[]> queue = new ArrayDeque<int[]>();
		queue.add(new int[] { r, c });
		map[r][c] = 1;// 방문한 영역을 체크하면서 이동한 거리를 표시(이동한 거리+1)
		while (!queue.isEmpty()) {
			int[] v = queue.poll();
			int sr = v[0];
			int sc = v[1];

			for (int i = 0; i < 4; i++) {
				int nr = sr + dr[i];
				int nc = sc + dc[i];
				if (nr == sword[0] && nc == sword[1]) {
					ans_sword = map[sr][sc] + (N - sword[0]) + (M - sword[1]);
					sword[0] = Integer.MAX_VALUE;
					sword[1] = Integer.MAX_VALUE;// 검을 한 번 찾았다면 다시 검의 위치에 오지 않게 설정함
				} // 용사가 검의 위치에 왔다면 검까지 걸린 시간과 검에서 공주까지의 최단거리를 합쳐서 총 시간을 구함
				if (nr >= 1 && nr <= N && nc >= 1 && nc <= M && map[nr][nc] == 0) {
					map[nr][nc] = map[sr][sc] + 1;
					if (map[nr][nc] == T + 2)
						return;// 제한 시간이 넘었다면 더 이상 탐색하지 않고 종료

					if (nr == N && nc == M) {
						ans = map[nr][nc] - 1;
						return;
					} // 제한 시간 내에 공주에게 도착했다면 시간을 ans에 담고 종료

					queue.add(new int[] { nr, nc });

				}

			}
		}
	}

}
