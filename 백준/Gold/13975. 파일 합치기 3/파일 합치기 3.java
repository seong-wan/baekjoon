import java.util.PriorityQueue;

public class Main {
	static int T;
	static PriorityQueue<Long> pq = new PriorityQueue<>((e1, e2) -> (Long.compare(e1, e2)));
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();

		T = in.nextInt();
		for (int t = 0; t < T; t++) {
			int K = in.nextInt();
			long ans = 0;

			for (int i = 0; i < K; i++) {
				pq.add((long)in.nextInt());
			}

			while (pq.size() > 1) {
				long first = pq.poll();
				long second = pq.poll();
				long sum = first + second;

				ans += sum;
				pq.add(sum);
			}

			pq.poll();
			sb.append(ans).append("\n");
		}

		System.out.print(sb);
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