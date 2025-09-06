public class Main {
	static class Node {
		int groupSum;
		long value;
	}

	static int[] parent;
	static StringBuilder sb = new StringBuilder();
	static int N, Q;
	static Node[] nadories;

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();

		N = in.nextInt();
		Q = in.nextInt();

		nadories = new Node[N + 1];
		for (int i = 1; i <= N; i++) {
			nadories[i] = new Node();
			nadories[i].groupSum = in.nextInt();
		}

		make();

		for (int i = 0; i < Q; i++) {
			int a = in.nextInt();
			int b = in.nextInt();

			sb.append(union(a, b)).append("\n");
		}

		System.out.print(sb);
	}

	static void make() {
		parent = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}
	}

	static int find(int num) {
		if (parent[num] == num)
			return num;

		return parent[num] = find(parent[num]);
	}

	static long union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);

		if (aRoot != bRoot) {
			parent[bRoot] = aRoot;
			nadories[aRoot].value = nadories[aRoot].value + nadories[bRoot].value
				+ (long)nadories[aRoot].groupSum * nadories[bRoot].groupSum;
			nadories[aRoot].groupSum += nadories[bRoot].groupSum;
		}

		return nadories[aRoot].value;
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