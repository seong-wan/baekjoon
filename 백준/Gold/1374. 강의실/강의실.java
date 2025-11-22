import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
	static int N;
	static int[][] lesson;
	static PriorityQueue<Integer> pq = new PriorityQueue<>();

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		N = in.nextInt();

		lesson = new int[N][2];
		for (int i = 0; i < N; i++) {
			in.nextInt();
			lesson[i][0] = in.nextInt();
			lesson[i][1] = in.nextInt();
		}

		Arrays.sort(lesson, (e1, e2) -> e1[0] - e2[0]);
		pq.add(lesson[0][1]);

		for (int i = 1; i < N; i++) {
			int temp = pq.peek();
			if (temp <= lesson[i][0]) {
				pq.poll();
			}

			pq.add(lesson[i][1]);
		}

		System.out.print(pq.size());
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