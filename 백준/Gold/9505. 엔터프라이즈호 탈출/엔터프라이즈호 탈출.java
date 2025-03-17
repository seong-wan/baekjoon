import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
	static int T, K, W, H;
	static int[] clingOn = new int[26];
	static char[][] map;
	static boolean[][] visit;
	static int[] start;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		T = in.nextInt();

		for (int t = 0; t < T; t++) {
			K = in.nextInt();
			W = in.nextInt();
			H = in.nextInt();

			map = new char[H][W];
			visit = new boolean[H][W];
			start = new int[2];

			for (int i = 0; i < K; i++) {
				char alphabet = in.nextChar();
				int time = in.nextInt();

				clingOn[alphabet - 'A'] = time;
			}

			for (int i = 0; i < H; i++) {
				String s = in.nextString();
				for (int j = 0; j < W; j++) {
					map[i][j] = s.charAt(j);

					if (map[i][j] == 'E') {
						start[0] = i;
						start[1] = j;
					}
				}
			}

			sb.append(dijk()).append("\n");
		}
		System.out.println(sb);
	}

	static class Node {
		int r;
		int c;
		long w;

		public Node(int r, int c, long w) {
			this.r = r;
			this.c = c;
			this.w = w;
		}
	}

	static long dijk() {
		PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingLong(e -> e.w));
		pq.add(new Node(start[0], start[1], 0));

		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			int cr = cur.r;
			int cc = cur.c;
			long w = cur.w;

			if (cr == 0 || cr == H - 1 || cc == 0 || cc == W - 1)
				return w;

			if (visit[cr][cc])
				continue;

			visit[cr][cc] = true;

			for (int dir = 0; dir < 4; dir++) {
				int nr = cr + dr[dir];
				int nc = cc + dc[dir];
				char alphabet = map[nr][nc];

				if (visit[nr][nc])
					continue;

				pq.add(new Node(nr, nc, w + clingOn[alphabet - 'A']));
			}
		}
		return -1;
	}

	static class Reader {
		final int SIZE = 1 << 13;
		byte[] buffer = new byte[SIZE];
		int index, size;

		String nextString() throws Exception {
			StringBuilder sb = new StringBuilder();
			byte c;
			while ((c = read()) < 32) {
				if (size < 0)
					return "endLine";
			}
			do
				sb.appendCodePoint(c);
			while ((c = read()) > 32); // SPACE 분리라면 >로, 줄당 분리라면 >=로
			return sb.toString();
		}

		char nextChar() throws Exception {
			byte c;
			while ((c = read()) < 32)
				; // SPACE 분리라면 <=로, SPACE 무시라면 <로
			return (char)c;
		}

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
