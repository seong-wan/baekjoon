//자기 자신을 부모로 하는 배열 생성
//합집합이 되면 작은 수를 기준으로 union
//찾을 때 같은 부모를 가지고 있다면 같은 집합에 속한 걸로 판정
public class Main {
	static int n, m;
	static int[] parent;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();

		n = in.nextInt();
		m = in.nextInt();

		make();

		for (int i = 0; i < m; i++) {
			int cmd = in.nextInt();
			int a = in.nextInt();
			int b = in.nextInt();

			//union
			if (cmd == 0)
				union(a, b);

				//find
			else {
				if (find(a) == find(b))
					sb.append("YES");
				else
					sb.append("NO");

				sb.append("\n");
			}
		}

		System.out.print(sb);
	}

	static void make() {
		parent = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			parent[i] = i;
		}
	}

	static int find(int num) {
		if (parent[num] == num) {
			return num;
		}
		return parent[num] = find(parent[num]);
	}

	static void union(int a, int b) {
		if (a == b)
			return;

		int rootA = find(a);
		int rootB = find(b);

		if (rootA < rootB)
			parent[rootB] = rootA;
		else
			parent[rootA] = rootB;
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