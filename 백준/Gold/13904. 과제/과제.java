import java.util.PriorityQueue;

public class Main {
	static int N, ans;
	static PriorityQueue<int[]> pq = new PriorityQueue<>((e1, e2) -> e2[1] - e1[1]);
	static int[] dayScore = new int[1001];
	static int[] route = new int[1001];

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		N = in.nextInt();

		for (int i = 0; i < N; i++) {
			pq.add(new int[] {in.nextInt(), in.nextInt()});
		}

		for (int i = 1; i <= 1000; i++) {
			route[i] = i;
		}

		while (!pq.isEmpty()) {
			int[] cur = pq.poll();

			int day = cur[0];
			int score = cur[1];

			int loc = find(day);

			if (loc != 0) {
				dayScore[loc] = score;
				route[loc]--;
				ans += score;
			}
		}

		System.out.println(ans);
	}

	static int find(int num) {
		if (route[num] == num)
			return num;

		return route[num] = find(route[num]);
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