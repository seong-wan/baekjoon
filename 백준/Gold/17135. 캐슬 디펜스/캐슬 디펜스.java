import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
	static int N, M, D, ans;
	static int[][] originMap;
	static int[] archors = new int[3];
	static int[] dr = {0, -1, 0};
	static int[] dc = {-1, 0, 1};

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();

		N = in.nextInt();
		M = in.nextInt();
		D = in.nextInt();

		originMap = new int[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				originMap[i][j] = in.nextInt();
			}
		}

		comb(0, 0);
		System.out.println(ans);
	}

	static void comb(int num, int idx) {
		if (idx == 3) {
			simul();
			return;
		}

		if (num == M)
			return;

		archors[idx] = num;
		comb(num + 1, idx + 1);
		comb(num + 1, idx);
	}

	static void simul() {
		int[][] map = new int[N][M];
		int temp = 0;
		copy(map);
		Deque<int[]> removeList = new ArrayDeque<>();

		while (true) {
			//적 찾기
			for (int i = 0; i < 3; i++) {
				//바로 적을 잡은 경우
				if (map[N - 1][archors[i]] == 1) {
					removeList.add(new int[] {N - 1, archors[i]});
					continue;
				}

				//사정거리가 1인 경우
				if (D == 1)
					continue;

				Deque<int[]> queue = new ArrayDeque<>();
				boolean[][] visit = new boolean[N][M];

				queue.add(new int[] {N - 1, archors[i]});
				visit[N - 1][archors[i]] = true;
				int depth = 2;

				while (!queue.isEmpty()) {
					int size = queue.size();
					for (int j = 0; j < size; j++) {
						int[] cur = queue.poll();
						int cr = cur[0];
						int cc = cur[1];

						for (int dir = 0; dir < 3; dir++) {
							int nr = cr + dr[dir];
							int nc = cc + dc[dir];

							if (canGo(nr, nc) && !visit[nr][nc]) {
								if (map[nr][nc] == 1) {
									removeList.add(new int[] {nr, nc});
									size = 0;
									queue.clear();
									break;
								}

								visit[nr][nc] = true;
								queue.add(new int[] {nr, nc});
							}
						}
					}
					depth++;

					if (depth > D)
						break;
				}
			}

			int removeCount = removeList.size();
			for (int i = 0; i < removeCount; i++) {
				int[] cur = removeList.poll();
				if (map[cur[0]][cur[1]] == 1) {
					temp++;
					map[cur[0]][cur[1]] = 0;
				}
			}

			//적이 아직 남아있는지 체크
			boolean check = false;

			//적 이동 처리
			for (int i = N - 2; i >= 0; i--) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == 1) {
						check = true;
					}

					map[i + 1][j] = map[i][j];
				}
			}

			for (int i = 0; i < M; i++) {
				map[0][i] = 0;
			}

			//남아있는 적이 없는 경우
			if (!check) {
				ans = Math.max(temp, ans);
				return;
			}
		}
	}

	static boolean canGo(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}

	static void copy(int[][] map) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = originMap[i][j];
			}
		}
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