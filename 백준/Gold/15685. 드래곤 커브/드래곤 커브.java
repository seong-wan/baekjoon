import java.util.ArrayList;
import java.util.List;

public class Main {
	static int N, ans;
	static boolean[][] map = new boolean[101][101];
	static int[] dy = {0, -1, 0, 1};
	static int[] dx = {1, 0, -1, 0};
	static List<int[]> dragon = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		N = in.nextInt();

		for (int i = 0; i < N; i++) {
			int startX = in.nextInt();
			int startY = in.nextInt();
			int d = in.nextInt();
			int g = in.nextInt();
			map[startX][startY] = true;

			int zeroX = startX + dx[d];
			int zeroY = startY + dy[d];
			map[zeroX][zeroY] = true;

			dragon.add(new int[] {startX, startY});
			dragon.add(new int[] {zeroX, zeroY});

			//다음 세대
			for (int j = 1; j <= g; j++) {
				//기준
				int ox = dragon.get(dragon.size() - 1)[0];
				int oy = dragon.get(dragon.size() - 1)[1];

				for (int k = dragon.size() - 2; k >= 0; k--) {
					int nx = oy - dragon.get(k)[1] + ox;
					int ny = dragon.get(k)[0] - ox + oy;

					dragon.add(new int[] {nx, ny});
					map[nx][ny] = true;
				}
			}

			dragon.clear();
		}

		squareCheck();

		System.out.println(ans);
	}

	static void squareCheck() {
		for (int x = 0; x < 100; x++) {
			for (int y = 0; y < 100; y++) {
				if (!map[x][y])
					continue;

				if (map[x + 1][y] && map[x][y + 1] && map[x + 1][y + 1])
					ans++;
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