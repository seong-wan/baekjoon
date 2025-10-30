//x,y의 모든 조합에 대해 꼭짓점으로 생각하고 한 쪽 방향으로의 점의 개수 구하기
public class Main {
	static int[][] dots = new int[100][2];
	static int N, A, B, ans;
	static final int MAX = 2_000_000_000;

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();

		N = in.nextInt();
		A = in.nextInt();
		B = in.nextInt();

		for (int i = 0; i < N; i++) {
			dots[i][0] = in.nextInt();
			dots[i][1] = in.nextInt();
		}

		for (int[] x : dots) {
			for (int[] y : dots) {
				int count = 0;

				for (int k = 0; k < N; k++) {
					if (dots[k][0] >= x[0] && dots[k][0] <= Math.min(x[0] + A, MAX)
						&& dots[k][1] >= y[1] && dots[k][1] <= Math.min(y[1] + B, MAX))
						count++;
				}

				ans = Math.max(ans, count);
			}
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