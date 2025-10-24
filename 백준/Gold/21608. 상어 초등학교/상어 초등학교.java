import java.util.HashSet;
import java.util.Set;

public class Main {
	static int N;
	static int[][] seat = new int[20][20];
	static Set<Integer>[] likes = new Set[401];
	static int[] satisfaction = {0, 1, 10, 100, 1000};
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		N = in.nextInt();

		for (int i = 0; i < N * N; i++) {
			int num = in.nextInt();
			likes[num] = new HashSet<>();

			for (int j = 0; j < 4; j++) {
				likes[num].add(in.nextInt());
			}

			if (i == 0)
				seat[1][1] = num;
			else
				place(num);
		}

		System.out.print(calcSatisfaction());
	}

	static int calcSatisfaction() {
		int total = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int num = seat[i][j];
				int likeCnt = 0;

				for (int dir = 0; dir < 4; dir++) {
					int nr = i + dr[dir];
					int nc = j + dc[dir];

					if (inRange(nr, nc) && likes[num].contains(seat[nr][nc]))
						likeCnt++;
				}

				total += satisfaction[likeCnt];
			}
		}

		return total;
	}

	static void place(int num) {
		int maxBlank = 0;
		int maxLike = 0;
		int cr = -1;
		int cc = -1;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (seat[i][j] != 0)
					continue;

				if (cr == -1) {
					cr = i;
					cc = j;
				}

				int like = 0;
				int blank = 0;

				for (int dir = 0; dir < 4; dir++) {
					int nr = i + dr[dir];
					int nc = j + dc[dir];

					if (inRange(nr, nc)) {
						if (seat[nr][nc] == 0)
							blank++;
						else if (likes[num].contains(seat[nr][nc]))
							like++;
					}
				}

				if (like > maxLike) {
					maxLike = like;
					maxBlank = blank;
					cr = i;
					cc = j;
				} else if (like == maxLike) {
					if (blank > maxBlank) {
						maxBlank = blank;
						cr = i;
						cc = j;
					}
				}
			}
		}

		seat[cr][cc] = num;
	}

	static boolean inRange(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
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