import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
	static int N, ans = 1;
	static PriorityQueue<Integer> pq = new PriorityQueue<>((e1, e2) -> e1 - e2);
	static int[][] lines;

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();

		N = in.nextInt();

		lines = new int[N][2];
		for (int i = 0; i < N; i++) {
			lines[i][0] = in.nextInt();
			lines[i][1] = in.nextInt();
		}

		Arrays.sort(lines, (e1, e2) -> e1[0] - e2[0]);

		pq.add(lines[0][1]);
		for (int i = 1; i < N; i++) {

			//제일 앞에 있는 끝점들을 빼가며 겹치지 않는 선분들 제거
			while (!pq.isEmpty()) {
				int cur = pq.poll();

				if (cur > lines[i][0]) {
					pq.add(cur);
					break;
				}
			}

			pq.add(lines[i][1]);

			ans = Math.max(ans, pq.size());
		}

		System.out.print(ans);
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