import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
	static int N, K;
	static PriorityQueue<int[]> pq = new PriorityQueue<>((e1, e2) -> e1[1] - e2[1]);
	static int[][] points = new int[500][2];
	static int[] visit = new int[500];

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		N = in.nextInt();
		K = in.nextInt();

		for (int i = 0; i < N; i++) {
			points[i][0] = in.nextInt();
			points[i][1] = in.nextInt();
		}

		Arrays.fill(visit, -1);

		pq.add(new int[] {0, 0, K});
		dijk();
	}

	static void dijk() {
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int num = cur[0];
			int dis = cur[1];
			int availPass = cur[2];

			//더 많은 건너뛰기 기회가 있음에도 짧은 거리로 해당 지점에 왔을 때
			if (visit[num] >= availPass) {
				continue;
			}

			visit[num] = availPass;

			if (num == N - 1) {
				System.out.println(dis);
				return;
			}

			for (int i = 1; i <= availPass + 1; i++) {
				int nextNum = num + i;
				if (nextNum >= N) {
					break;
				}

				int dx = points[nextNum][0] - points[num][0];
				int dy = points[nextNum][1] - points[num][1];
				int nextDis = dis + Math.abs(dx) + Math.abs(dy);

				int nextAvailPass = availPass - (i - 1);

				if (visit[nextNum] >= nextAvailPass)
					continue;
				pq.add(new int[] {nextNum, nextDis, nextAvailPass});
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