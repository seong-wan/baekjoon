import java.util.ArrayList;
import java.util.List;

public class Main {
	static int N;

	static class Node {
		long cnt;
		List<Integer> child = new ArrayList<>();
	}

	static Node[] tree;

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();

		N = in.nextInt();

		tree = new Node[N + 1];
		for (int i = 1; i <= N; i++) {
			tree[i] = new Node();
		}

		for (int i = 2; i <= N; i++) {
			char kind = in.nextChar();
			int cnt = in.nextInt();
			int parent = in.nextInt();

			if (kind == 'W')
				cnt *= -1;

			tree[i].cnt = cnt;
			tree[parent].child.add(i);
		}

		System.out.print(search(1));
	}

	static long search(int num) {
		for (int child : tree[num].child) {
			tree[num].cnt += search(child);
		}

		return Math.max(tree[num].cnt, 0);
	}

	static class Reader {
		final int SIZE = 1 << 13;
		byte[] buffer = new byte[SIZE];
		int index, size;

		char nextChar() throws Exception {
			byte c;
			while ((c = read()) < 32)
				;
			return (char)c;
		}

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