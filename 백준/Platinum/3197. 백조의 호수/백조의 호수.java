import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int R, C;
	static int[] start = new int[2], end = new int[2];
	static char[][] map;
	static int[][] numMap;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	static Deque<int[]> queue = new ArrayDeque<>();
	static int[] parent;
	static boolean check;

	public static void main(String[] args) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		start[0] = -1;
		map = new char[R][C];
		numMap = new int[R][C];

		for (int i = 0; i < R; i++) {
			String s = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = s.charAt(j);

				if (map[i][j] == 'L') {
					if (start[0] == -1) {
						start[0] = i;
						start[1] = j;
					} else {
						end[0] = i;
						end[1] = j;
					}
				}
			}
		}

		int num = 1;
		makeSet(start[0], start[1], num++);
		if (check) {
			System.out.println(0);
			return;
		}
		makeSet(end[0], end[1], num++);

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] == '.')
					makeSet(i, j, num++);
			}
		}

		parent = new int[num];
		make();

		System.out.println(bfs());
	}

	static int find(int num) {
		if (parent[num] == num)
			return num;

		return parent[num] = find(parent[num]);
	}

	static void make() {
		for (int i = 1; i < parent.length; i++) {
			parent[i] = i;
		}
	}

	static void makeSet(int r, int c, int num) {
		Deque<int[]> makeQ = new ArrayDeque<>();
		makeQ.add(new int[] {r, c});
		map[r][c] = 'O';
		numMap[r][c] = num;

		while (!makeQ.isEmpty()) {
			int[] cur = makeQ.poll();
			int cr = cur[0];
			int cc = cur[1];

			for (int dir = 0; dir < 4; dir++) {
				int nr = cr + dr[dir];
				int nc = cc + dc[dir];

				if (canGo(nr, nc)) {
					if (map[nr][nc] == '.')
						makeQ.add(new int[] {nr, nc});
					else if (map[nr][nc] == 'X')
						queue.add(new int[] {nr, nc});
					else if (map[nr][nc] == 'L') {
						check = true;
						return;
					}

					numMap[nr][nc] = num;
					map[nr][nc] = 'O';
				}
			}
		}
	}

	static int bfs() {
		Deque<int[]> nextTime = new ArrayDeque<>();
		int depth = 1;
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				int[] cur = queue.poll();
				int cr = cur[0];
				int cc = cur[1];

				for (int dir = 0; dir < 4; dir++) {
					int nr = cr + dr[dir];
					int nc = cc + dc[dir];

					if (canGo(nr, nc)) {
						if (map[nr][nc] == 'X') {
							nextTime.add(new int[] {nr, nc});
							queue.add(new int[] {nr, nc});
							numMap[nr][nc] = numMap[cr][cc];
							map[nr][nc] = 'N';
						} else if (map[nr][nc] == 'N')
							continue;
						else {
							int a = find(numMap[cr][cc]);
							int b = find(numMap[nr][nc]);

							int first = Math.min(a, b);
							int second = Math.max(a, b);

							if (first == 1 && second == 2)
								return depth;

							if (first == second)
								continue;

							parent[second] = first;
						}
					}
				}
			}
			while (!nextTime.isEmpty()) {
				int[] cur = nextTime.poll();

				map[cur[0]][cur[1]] = 'O';
			}

			depth++;
		}
		return 0;
	}

	static boolean canGo(int r, int c) {
		return r >= 0 && r < R && c >= 0 && c < C;
	}
}