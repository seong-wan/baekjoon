import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Main {
	static int N, M, S, E;
	static List<Integer>[] adlist;
	static boolean[] check;

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		N = in.nextInt();
		M = in.nextInt();
		S = in.nextInt();
		E = in.nextInt();

		adlist = new List[N + 1];
		check = new boolean[N + 1];

		for (int i = 1; i <= N; i++) {
			adlist[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			int from = in.nextInt();
			int to = in.nextInt();

			adlist[from].add(to);
			adlist[to].add(from);
		}

		System.out.println(bfs());
	}

	static int bfs() {
		Deque<Integer> queue = new ArrayDeque<>();
		queue.add(S);
		check[S] = true;
		int time = 1;

		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				int cur = queue.poll();

				int pCur = cur - 1;
				int nCur = cur + 1;

				if (pCur >= 1 && !check[pCur]) {
					if (pCur == E)
						return time;

					queue.add(pCur);
					check[pCur] = true;
				}

				if (nCur <= N && !check[nCur]) {
					if (nCur == E)
						return time;

					queue.add(nCur);
					check[nCur] = true;
				}

				for (int next : adlist[cur]) {
					if (next == E)
						return time;

					if (check[next])
						continue;

					queue.add(next);
				}
			}
			time++;
		}
		return -1;
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