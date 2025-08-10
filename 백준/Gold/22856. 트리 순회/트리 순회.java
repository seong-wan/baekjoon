import java.util.HashMap;

public class Main {
	static class Node {
		int parent;
		int left;
		int right;
		boolean visited;
	}

	static HashMap<Integer, Node> tree = new HashMap<>();
	static int N, end;
	static int moveCnt;
	static boolean isEnd;

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		N = in.nextInt();

		for (int i = 1; i <= N; i++) {
			int num = in.nextInt();

			tree.putIfAbsent(num, new Node());
			Node temp = tree.get(num);

			temp.left = in.nextInt();
			temp.right = in.nextInt();

			tree.putIfAbsent(temp.left, new Node());
			tree.putIfAbsent(temp.right, new Node());

			Node leftNode = tree.get(temp.left);
			Node rightNode = tree.get(temp.right);

			leftNode.parent = num;
			rightNode.parent = num;
		}

		findend(1);
		search(1);

		System.out.print(moveCnt);
	}

	static void findend(int num) {
		Node cur = tree.get(num);
		if (cur.right == -1) {
			end = num;
			return;
		}

		findend(cur.right);
	}

	static void search(int num) {
		Node cur = tree.get(num);

		cur.visited = true;

		if (cur.left != -1 && !tree.get(cur.left).visited) {
			search(cur.left);
			moveCnt++;
		}

		if (cur.right != -1 && !tree.get(cur.right).visited) {
			search(cur.right);
			moveCnt++;
		}

		if (num == end) {
			isEnd = true;
			return;
		}

		if (isEnd)
			return;

		if (cur.parent != 0) {
			search(cur.parent);
			moveCnt++;
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