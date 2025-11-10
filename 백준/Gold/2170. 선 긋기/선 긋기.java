import java.util.PriorityQueue;

public class Main {
	static PriorityQueue<int[]> pq = new PriorityQueue<>((e1, e2) -> e1[0] - e2[0]);
	static int ans;
	static int N;

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		N = in.nextInt();

		for (int i = 0; i < N; i++) {
			pq.add(new int[] {in.nextInt(), in.nextInt()});
		}

		int[] first = pq.poll();
		int start = first[0];
		int end = first[1];

		while (!pq.isEmpty()) {
			int[] line = pq.poll();

			//줄이 이어지는 경우
			if (line[0] <= end) {
				end = Math.max(end, line[1]);
			} else {
				ans += end - start;
				start = line[0];
				end = line[1];
			}
		}

		ans += end - start;

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