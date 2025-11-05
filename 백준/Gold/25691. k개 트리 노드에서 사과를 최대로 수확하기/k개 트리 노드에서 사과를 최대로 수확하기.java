import java.util.ArrayList;
import java.util.List;

public class Main {
	static int n, k, ans;
	static List<Integer>[] tree = new List[17];
	static int appleMask;

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		n = in.nextInt();
		k = in.nextInt();

		for (int i = 0; i < n; i++) {
			tree[i] = new ArrayList<>();
		}

		for (int i = 0; i < n - 1; i++) {
			int parent = in.nextInt();
			int child = in.nextInt();

			tree[parent].add(child);
		}

		for (int i = 0; i < n; i++) {
			int cnt = in.nextInt();

			if (cnt == 1)
				appleMask |= (1 << i);
		}

		for (int mask = 1; mask < (1 << n); mask++) {
			if ((mask & 1) == 0)
				continue; //루트 노드가 포함되지 않은 경우 제외

			if (Integer.bitCount(mask) != k)
				continue; //k개의 노드가 아닌 경우 제외

			int appleCnt = Integer.bitCount(mask & appleMask);
			if (appleCnt < ans)
				continue;

			if (dfs(0, mask) == k)
				ans = appleCnt;
		}

		System.out.print(ans);
	}

	static int dfs(int node, int mask) {
		int cnt = 1;

		for (Integer child : tree[node]) {
			if ((mask & (1 << child)) == (1 << child))
				cnt += dfs(child, mask);
		}

		return cnt;
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