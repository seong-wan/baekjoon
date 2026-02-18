import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
	static int N, M, A, B;
	static boolean[] visit;

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();

		N = in.nextInt();
		M = in.nextInt();
		A = in.nextInt();
		B = in.nextInt();

		visit = new boolean[N + 1];

		//강아지가 사라지는 닫힌 구간
		for (int i = 0; i < M; i++) {
			int start = in.nextInt();
			int end = in.nextInt();

			//갈 수 없는 수이기에 visit처리
			for (int j = start; j <= end; j++) {
				visit[j] = true;
			}
		}

		System.out.print(bfs());
	}

	static int bfs() {
		Deque<Integer> queue = new ArrayDeque<>();
		queue.add(0); //0마리로 시작
		int cnt = 0;

		while (!queue.isEmpty()) {
			cnt++;
			int size = queue.size();

			for (int i = 0; i < size; i++) {
				int cur = queue.poll();

				int ANext = cur + A;
				int BNext = cur + B;

				if (ANext == N || BNext == N)
					return cnt;

				if (ANext <= N && !visit[ANext]) {
					visit[ANext] = true;
					queue.add(ANext);
				}

				if (BNext <= N && !visit[BNext]) {
					visit[BNext] = true;
					queue.add(BNext);
				}
			}
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