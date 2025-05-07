import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
	static int N, Q;
	static List<Integer>[] adlist;
	static int[] root;
	static int[] visit;
	static boolean check;
	static Set<Integer> cycle = new HashSet<>();
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();
		N = in.nextInt();
		Q = in.nextInt();

		adlist = new List[N + 1];
		root = new int[N + 1];
		visit = new int[N + 1];
		Arrays.fill(visit, -1);

		for (int i = 1; i <= N; i++) {
			adlist[i] = new ArrayList<>();
		}

		for (int i = 0; i < N; i++) {
			int a = in.nextInt();
			int b = in.nextInt();

			adlist[a].add(b);
			adlist[b].add(a);

		}

		visit[1] = 0;
		cycleCheck(1);

		for (Integer num : cycle) {
			root[num] = num;
			dfs(num, num);
		}

		for (int i = 0; i < Q; i++) {
			int A = in.nextInt();
			int B = in.nextInt();

			if (root[A] == root[B])
				sb.append(1);
			else
				sb.append(2);

			sb.append("\n");
		}

		System.out.println(sb);
	}

	static void cycleCheck(int node) {
		for (Integer next : adlist[node]) {
			if (check)
				return;

			if (visit[node] == next)
				continue;

			if (visit[next] == -1) {
				visit[next] = node;
				cycleCheck(next);
			} else {
				check = true;
				int temp = node;
				while (temp != next) {
					cycle.add(temp);
					temp = visit[temp];
				}
				cycle.add(next);
			}
		}
	}

	//사이클에 속하면 true를 리턴
	static void dfs(int node, int r) {
		for (Integer next : adlist[node]) {
			if (cycle.contains(next))
				continue;

			if (root[next] == 0) {
				root[next] = r;
				dfs(next, r);
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