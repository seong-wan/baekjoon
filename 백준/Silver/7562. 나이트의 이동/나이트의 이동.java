import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int T, I;
	static int[] start = new int[2], end = new int[2];

	static int[] dx = { -2, -1, 1, 2, -1, -2, 2, 1 };
	static int[] dy = { 1, 2, 2, 1, -2, -1, -1, -2 };
	static boolean[][] visited;
	static int move;

	public static void main(String[] args) throws Exception {
		for (int i = 0; i < 8; i++) {

		}
		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			newgame();
			visited[start[0]][start[1]] = true;
			bfs(start[0], start[1]);
			System.out.println(move);
		}

	}

	static void newgame() throws Exception {
		I = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		start[0] = Integer.parseInt(st.nextToken());
		start[1] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		end[0] = Integer.parseInt(st.nextToken());
		end[1] = Integer.parseInt(st.nextToken());
		visited = new boolean[I][I];
		move = 0;

	}

	static void bfs(int x, int y) {
		Deque<int[]> queue = new ArrayDeque<int[]>();
		queue.add(new int[] { x, y });

		while (!queue.isEmpty()) {

			int size = queue.size();

			for (int i = 0; i < size; i++) {

				int[] v = queue.poll();
				if (v[0] == end[0] && v[1] == end[1])
					return;

				for (int dir = 0; dir < 8; dir++) {
					int nx = v[0] + dx[dir];
					int ny = v[1] + dy[dir];
					if (nx >= 0 && nx <= I - 1 && ny >= 0 && ny <= I - 1 && !visited[nx][ny]) {

						queue.add(new int[] { nx, ny });
						visited[nx][ny] = true;
					}

				}
			}
			move++;

		}
	}

}
