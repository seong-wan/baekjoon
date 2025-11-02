import java.util.ArrayList;
import java.util.List;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static final String template = "Sky %d contains %d constellations.";
	static int[][] star = new int[1000][2];
	static int N;
	static double[] minDist = new double[1000];
	static int[] parent = new int[1000];
	static List<Integer>[] adlist = new List[1000];

	public static void main(String[] args) throws Exception {
		Reader in = new Reader();

		for (int t = 1; ; t++) {
			N = in.nextInt();

			if (N == 0)
				break;

			init();

			for (int i = 0; i < N; i++) {
				star[i][0] = in.nextInt();
				star[i][1] = in.nextInt();

				for (int j = 0; j < i; j++) {
					int dx = star[i][0] - star[j][0];
					int dy = star[i][1] - star[j][1];
					double dist = Math.sqrt(dx * dx + dy * dy);

					//기존 별(j) 기준 최소의 거리일 때
					if (dist < minDist[j]) {
						minDist[j] = dist;
						adlist[j].clear();
						adlist[j].add(i);
					} else if (dist == minDist[j]) {
						adlist[j].add(i);
					}

					//새로 추가된 별(i) 기준 최소의 거리일 때
					if (dist < minDist[i]) {
						minDist[i] = dist;
						adlist[i].clear();
						adlist[i].add(j);
					} else if (dist == minDist[i]) {
						adlist[i].add(j);
					}
				}
			}

			for (int i = 0; i < N; i++) {
				for (Integer ad : adlist[i]) {
					union(i, ad);
				}
			}

			int cnt = check();
			sb.append(String.format(template, t, cnt)).append('\n');
		}

		System.out.print(sb);
	}

	static int check() {
		int cnt = 0;
		boolean[] checked = new boolean[N];
		for (int i = 0; i < N; i++) {
			int starNum = find(i);
			if (!checked[starNum]) {
				cnt++;
				checked[starNum] = true;
			}
		}

		return cnt;
	}

	static void init() {
		for (int i = 0; i < N; i++) {
			parent[i] = i;
			minDist[i] = Integer.MAX_VALUE;
			adlist[i] = new ArrayList<>();
		}
	}

	static int find(int num) {
		if (parent[num] == num)
			return num;

		return parent[num] = find(parent[num]);
	}

	static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);

		if (rootA != rootB) {
			parent[rootB] = rootA;
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