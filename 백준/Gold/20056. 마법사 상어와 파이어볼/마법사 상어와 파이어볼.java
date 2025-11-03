import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
	static int N, M, K;
	static Deque<int[]>[][] cur = new Deque[50][50];
	static Deque<int[]>[][] next = new Deque[50][50];
	static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		N = in.nextInt();
		M = in.nextInt();
		K = in.nextInt();

		init();

		for (int i = 0; i < M; i++) {
			int r = in.nextInt() - 1;
			int c = in.nextInt() - 1;
			int m = in.nextInt(); //질량
			int s = in.nextInt(); //속력
			int d = in.nextInt(); //방향

			cur[r][c].add(new int[] {m, s, d});
		}

		for (int i = 0; i < K; i++) {
			moveFire();
			sumFire();
		}

		System.out.print(calc());
	}

	static int calc() {
		int total = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				while (!cur[i][j].isEmpty()) {
					total += cur[i][j].poll()[0];
				}
			}
		}

		return total;
	}

	static void sumFire() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (next[i][j].isEmpty())
					continue;

				if (next[i][j].size() == 1) {
					cur[i][j].add(next[i][j].poll());
					continue;
				}

				int size = next[i][j].size();
				int totalM = 0;
				int totalS = 0;
				boolean isEven = true;
				boolean isOdd = true;

				while (!next[i][j].isEmpty()) {
					int[] fire = next[i][j].poll();

					totalM += fire[0];
					totalS += fire[1];

					if (fire[2] % 2 == 0)
						isOdd = false;
					else
						isEven = false;
				}

				totalM /= 5;
				totalS /= size;

				//질량이 0이 되어 소멸하는 경우
				if (totalM == 0)
					continue;

				for (int k = 0; k < 8; k++) {
					if (isEven || isOdd) {
						if (k % 2 == 0)
							cur[i][j].add(new int[] {totalM, totalS, k});
					} else {
						if (k % 2 == 1)
							cur[i][j].add(new int[] {totalM, totalS, k});
					}
				}
			}
		}
	}

	static void moveFire() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				while (!cur[i][j].isEmpty()) {
					int[] fire = cur[i][j].poll();
					int m = fire[0];
					int s = fire[1];
					int d = fire[2];

					int nr = (i + dr[d] * (s % N) + N) % N;
					int nc = (j + dc[d] * (s % N) + N) % N;
					next[nr][nc].add(fire);
				}
			}
		}
	}

	static void init() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				cur[i][j] = new ArrayDeque<>();
				next[i][j] = new ArrayDeque<>();
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