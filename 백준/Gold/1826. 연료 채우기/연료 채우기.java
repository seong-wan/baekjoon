import java.util.PriorityQueue;

public class Main {
	static int N, end, availLoc, ans;

	static PriorityQueue<int[]> distancePq = new PriorityQueue<>((e1, e2) -> e1[0] - e2[0]);
	static PriorityQueue<int[]> oilPq = new PriorityQueue<>((e1, e2) -> e2[1] - e1[1]);

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();

		N = in.nextInt();

		for (int i = 0; i < N; i++) {
			distancePq.add(new int[] {in.nextInt(), in.nextInt()});
		}

		end = in.nextInt();
		availLoc = in.nextInt();

		//바로 갈 수 있을 때
		if (availLoc >= end) {
			System.out.println(0);
			return;
		}

		while (true) {
			if (availLoc >= end) {
				System.out.println(ans);
				break;
			}

			while (!distancePq.isEmpty()) {
				int[] cur = distancePq.poll();

				if (cur[0] <= availLoc)
					oilPq.add(cur);
				else {
					distancePq.add(cur);
					break;
				}
			}

			if (oilPq.isEmpty()) {
				System.out.println(-1);
				break;
			}

			int[] oilBank = oilPq.poll();
			availLoc += oilBank[1];
			ans++;
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
