import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

public class Main {
	static int N, M, fuel;
	static int[][] guests;
	static int[][] map;
	static int[] taxi = new int[2];
	static int[] dr = {-1, 0, 0, 1};
	static int[] dc = {0, -1, 1, 0};

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		N = in.nextInt();
		M = in.nextInt();
		fuel = in.nextInt();

		map = new int[N + 1][N + 1];
		guests = new int[M + 2][2];

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				map[i][j] = in.nextInt();
			}
		}

		taxi[0] = in.nextInt();
		taxi[1] = in.nextInt();

		for (int i = 2; i < M + 2; i++) {
			int startR = in.nextInt();
			int startC = in.nextInt();
			int endR = in.nextInt();
			int endC = in.nextInt();

			map[startR][startC] = i;
			guests[i][0] = endR;
			guests[i][1] = endC;
		}

		while (M != 0) {
			int guest = findGuest();

			//손님을 찾지 못하거나 연료가 바닥난 상황
			if (guest == -1) {
				fuel = -1;
				break;
			}

			//도착지로 가지 못하거나 연료가 바닥난 상황
			if (!moveTaxi(guest)) {
				fuel = -1;
				break;
			}

			M--;
		}

		System.out.print(fuel);
	}

	static int findGuest() {
		PriorityQueue<int[]> queue = new PriorityQueue<>((e1, e2) -> e1[0] == e2[0] ? e1[1] - e2[1] : e1[0] - e2[0]);
		Deque<int[]> nextQueue = new ArrayDeque<>();
		boolean[][] visited = new boolean[N + 1][N + 1];
		int wasteFuel = 0;
		queue.add(taxi);

		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				int[] cur = queue.poll();
				int cr = cur[0];
				int cc = cur[1];

				//손님을 찾은 경우
				if (map[cr][cc] > 1) {
					int guest = map[cr][cc];
					map[cr][cc] = 0; //손님을 태웠으니 해당 위치는 비워둠
					taxi[0] = cr;
					taxi[1] = cc;
					fuel -= wasteFuel; //연료 차감
					return guest;
				}

				for (int dir = 0; dir < 4; dir++) {
					int nr = cr + dr[dir];
					int nc = cc + dc[dir];

					if (canGo(nr, nc) && !visited[nr][nc]) {
						visited[nr][nc] = true;
						nextQueue.add(new int[] {nr, nc});
					}
				}
			}

			while (!nextQueue.isEmpty()) {
				queue.add(nextQueue.poll());
			}
			wasteFuel++;

			if (wasteFuel > fuel)
				break;
		}

		return -1; //손님을 찾지 못한거나 연료가 바닥난 경우
	}

	static boolean moveTaxi(int guest) {
		Deque<int[]> queue = new ArrayDeque<>();
		boolean[][] visited = new boolean[N + 1][N + 1];
		int wasteFuel = 0;
		int endR = guests[guest][0];
		int endC = guests[guest][1];
		queue.add(taxi);

		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				int[] cur = queue.poll();
				int cr = cur[0];
				int cc = cur[1];

				//도착지에 도착한 경우
				if (cr == endR && cc == endC) {
					taxi[0] = cr;
					taxi[1] = cc;
					fuel += wasteFuel; //연료 충전
					return true;
				}

				for (int dir = 0; dir < 4; dir++) {
					int nr = cr + dr[dir];
					int nc = cc + dc[dir];

					if (canGo(nr, nc) && !visited[nr][nc]) {
						visited[nr][nc] = true;
						queue.add(new int[] {nr, nc});
					}
				}

			}

			wasteFuel++;
			if (wasteFuel > fuel)
				break;
		}

		return false; //도착지로 가지 못하거나 연료가 바닥난 경우
	}

	static boolean canGo(int r, int c) {
		return 1 <= r && r <= N && 1 <= c && c <= N && map[r][c] != 1;
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