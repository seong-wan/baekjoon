import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, r1, c1, r2, c2;
	static int[][] visited;
	static int[] dr = { -2, -2, 0, 0, 2, 2 };
	static int[] dc = { -1, 1, -2, 2, -1, 1 };// 데스나이트 이동방향

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		r1 = Integer.parseInt(st.nextToken());
		c1 = Integer.parseInt(st.nextToken());
		r2 = Integer.parseInt(st.nextToken());
		c2 = Integer.parseInt(st.nextToken());
		visited = new int[N][N];
		bfs();
		System.out.println(visited[r2][c2] == 0 ? -1 : visited[r2][c2] - 1);
		// 방문 가능하면 방문 배열의 도착점 값-1 방문 불가능하면 -1 출력
	}

	static void bfs() {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] { r1, c1 });
		visited[r1][c1] = 1;// 출발점을 1로 잡았으므로 결과에서 1을 빼줌
		while (!queue.isEmpty()) {
			int[] v = queue.poll();
			int sr = v[0];
			int sc = v[1];
			for (int i = 0; i < 6; i++) {
				int nr = sr + dr[i];
				int nc = sc + dc[i];
				if (nr >= 0 && nr < N && nc >= 0 && nc < N && visited[nr][nc] == 0) {
					queue.add(new int[] { nr, nc });
					visited[nr][nc] = visited[sr][sc] + 1;
				}

			}
		}
	}

}
