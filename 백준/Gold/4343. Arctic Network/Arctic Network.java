import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int N, S, P;
	static int[][] node;
	static double[][] mat;
	static boolean[] visit;
	static double[] ans;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		for (int t = 0; t < N; t++) {
			st = new StringTokenizer(br.readLine());
			S = Integer.parseInt(st.nextToken());
			P = Integer.parseInt(st.nextToken());

			mat = new double[P][P];
			node = new int[P][2];
			visit = new boolean[P];
			ans = new double[P - 1];
			for (int i = 0; i < P; i++) {
				st = new StringTokenizer(br.readLine());
				node[i][0] = Integer.parseInt(st.nextToken());
				node[i][1] = Integer.parseInt(st.nextToken());
			}

			makeedge();
			prim();
		}
	}

	static void prim() {
		PriorityQueue<double[]> pq = new PriorityQueue<>((e1, e2) -> e1[1] - e2[1] >= 0 ? 1 : -1);
		int cnt = 0;
		pq.add(new double[] { 0, 0 });
		while (!pq.isEmpty()) {
			double[] cur = pq.poll();
			int to = (int) cur[0];
			if (visit[to])
				continue;
			double cost = cur[1];
			if (cnt != 0)
				ans[cnt - 1] = cost;
			cnt++;
			if (cnt == P) {
				Arrays.sort(ans);
				System.out.printf("%.2f\n", Math.sqrt(ans[P - 2 - (S - 1)]));
			}
			visit[to] = true;
			for (int i = 0; i < P; i++) {
				if (!visit[i]) {
					pq.add(new double[] { i, mat[to][i] });
				}
			}
		}

	}

	static void makeedge() {
		for (int i = 0; i < P; i++) {
			for (int j = i + 1; j < P; j++) {
				double dx = node[i][0] - node[j][0];
				double dy = node[i][1] - node[j][1];

				mat[i][j] = dx * dx + dy * dy;
				mat[j][i] = dx * dx + dy * dy;
			}
		}
	}
}