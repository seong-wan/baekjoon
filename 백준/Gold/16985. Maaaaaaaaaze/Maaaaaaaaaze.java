import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
	static int[][][][] pan = new int[5][4][5][5]; //입력된 순서, 회전 수(반시계), r,c
	static int[][][] maze = new int[5][5][5]; //z,r,c
	static boolean[] made = new boolean[5]; //이미 미로에 포함된 판인지 판정
	static boolean[][][] visit; //탐색 중 이미 방문한 칸인지 판정
	static int[] dr = {-1, 0, 1, 0, 0, 0};
	static int[] dc = {0, -1, 0, 1, 0, 0};
	static int[] dz = {0, 0, 0, 0, -1, 1};
	static int ans = -1;

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				for (int k = 0; k < 5; k++) {
					pan[i][0][j][k] = in.nextInt();

					if (pan[i][0][j][k] == 1) {
						pan[i][1][4 - k][j] = 1;
						pan[i][2][4 - j][4 - k] = 1;
						pan[i][3][k][4 - j] = 1;
					}
				}
			}
		}

		find(0);
		System.out.println(ans);
	}

	static void find(int depth) {
		if (depth == 5) {
			bfs();
			return;
		}

		for (int i = 0; i < 5; i++) {
			//이미 미로에 구성되어져 있는 판인 경우
			if (made[i])
				continue;
			for (int j = 0; j < 4; j++) {
				//출발점에 있을 수가 없는 경우
				if (depth == 0 && pan[i][j][0][0] != 1) {
					continue;
				}

				//도착점에 있을 수가 없는 경우
				if (depth == 4 && pan[i][j][4][4] != 1) {
					continue;
				}

				maze[depth] = pan[i][j];
				made[i] = true;
				find(depth + 1);
				made[i] = false;
			}
		}
	}

	static void bfs() {
		Deque<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] {0, 0, 0});
		visit = new boolean[5][5][5];
		visit[0][0][0] = true;
		int distance = 1;

		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				int[] cur = queue.poll();
				int cz = cur[0];
				int cr = cur[1];
				int cc = cur[2];

				for (int dir = 0; dir < 6; dir++) {
					int nz = cz + dz[dir];
					int nr = cr + dr[dir];
					int nc = cc + dc[dir];

					if (canGo(nz, nr, nc)) {
						//도착점에 도착한 경우
						if (nz == 4 && nr == 4 && nc == 4) {
							if (ans == -1)
								ans = distance;
							else
								ans = Math.min(distance, ans);

							return;
						}

						visit[nz][nr][nc] = true;
						queue.add(new int[] {nz, nr, nc});
					}
				}
			}
			distance++;
		}
	}

	static boolean canGo(int z, int r, int c) {
		return z >= 0 && z < 5 && r >= 0 && r < 5 && c >= 0 && c < 5 && !visit[z][r][c] && maze[z][r][c] == 1;
	}

	static class Reader {
		final int SIZE = 1 << 13;
		byte[] buffer = new byte[SIZE];
		int index, size;

		int nextInt() throws Exception {
			int n = 0;
			byte c;
			boolean isMinus = false;
			while ((c = read()) <= 32) {
				if (size < 0)
					return -1;
			}
			if (c == 45) {
				c = read();
				isMinus = true;
			}
			do
				n = (n << 3) + (n << 1) + (c & 15);
			while (isNumber(c = read()));
			return isMinus ? ~n + 1 : n;
		}

		boolean isNumber(byte c) {
			return 47 < c && c < 58;
		}

		byte read() throws Exception {
			if (index == size) {
				size = System.in.read(buffer, index = 0, SIZE);
				if (size < 0)
					buffer[0] = -1;
			}
			return buffer[index++];
		}
	}
}