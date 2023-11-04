import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int parents[];
	static int N;
	static StringTokenizer st;
	static int[][] x;
	static int[][] y;
	static int[][] z;
	static List<int[]> edgelist = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());

		x = new int[N][2];
		y = new int[N][2];
		z = new int[N][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken());
			int Y = Integer.parseInt(st.nextToken());
			int Z = Integer.parseInt(st.nextToken());
			x[i][0] = i;
			x[i][1] = X;
			y[i][0] = i;
			y[i][1] = Y;
			z[i][0] = i;
			z[i][1] = Z;
		}
		Arrays.sort(x, (e1, e2) -> e1[1] - e2[1]);
		Arrays.sort(y, (e1, e2) -> e1[1] - e2[1]);
		Arrays.sort(z, (e1, e2) -> e1[1] - e2[1]);

		make();
		makeedge();
		Collections.sort(edgelist, (e1, e2) -> e1[2] - e2[2]);
		int result = 0;// MST 비용
		int count = 0;// 연결된 간선 개수
		for (int[] e : edgelist) {
			if (union(e[0], e[1])) {
				result += e[2];
				if (++count == N - 1)
					break;
			}

		}
		System.out.println(result);
	}

	static void makeedge() {
		for (int i = 0; i < N - 1; i++) {
			int from = x[i][0];
			int to = x[i + 1][0];
			int cost = x[i + 1][1] - x[i][1];
			edgelist.add(new int[] { from, to, cost });
			from = y[i][0];
			to = y[i + 1][0];
			cost = y[i + 1][1] - y[i][1];
			edgelist.add(new int[] { from, to, cost });
			from = z[i][0];
			to = z[i + 1][0];
			cost = z[i + 1][1] - z[i][1];
			edgelist.add(new int[] { from, to, cost });
		}
		int from = x[N - 2][0];
		int to = x[N - 1][0];
		int cost = x[N - 1][1] - x[N - 2][1];
		edgelist.add(new int[] { from, to, cost });
		from = y[N - 2][0];
		to = y[N - 1][0];
		cost = y[N - 1][1] - y[N - 2][1];
		edgelist.add(new int[] { from, to, cost });
		from = z[N - 2][0];
		to = z[N - 1][0];
		cost = z[N - 1][1] - z[N - 2][1];
		edgelist.add(new int[] { from, to, cost });

	}

	static void make() {
		parents = new int[N];
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}
	}

	static int find(int a) {
		if (parents[a] == a)
			return a;
		return parents[a] = find(parents[a]);
	}

	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if (aRoot == bRoot)
			return false;
		parents[bRoot] = aRoot;
		return true;

	}

}