import java.util.Arrays;

public class Main {
	static int N, M, ans = Integer.MAX_VALUE;

	static int[] tree;

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		N = in.nextInt();
		M = in.nextInt();

		tree = new int[N];
		for (int i = 0; i < N; i++) {
			tree[i] = in.nextInt();
		}

		Arrays.sort(tree);

		for (int i = 0; i < M; i++) {
			int apple = in.nextInt();
			int loc = Arrays.binarySearch(tree, apple);

			if (loc >= 0) {
				ans = 0;
				break;
			}

			int nextLoc = -loc - 1;
			int preLoc = nextLoc - 1;

			if (nextLoc < N)
				ans = Math.min(ans, Math.abs(apple - tree[nextLoc]));

			if (preLoc >= 0)
				ans = Math.min(ans, Math.abs(apple - tree[preLoc]));
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