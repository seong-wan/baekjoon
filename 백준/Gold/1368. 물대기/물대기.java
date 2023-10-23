import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;
	static int mat[][];
	static boolean visit[];

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());

		mat = new int[N][N];
		for (int i = 0; i < N; i++) {
			mat[i][i] = Integer.parseInt(br.readLine());
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				if (i == j) {
					st.nextToken();
					continue;
				}
				mat[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		prim();
	}

	static void prim() {
		visit = new boolean[N];
		int weight = 0;
		int cnt = 0;
		PriorityQueue<int[]> pq = new PriorityQueue<>((e1, e2) -> e1[1] - e2[1]);
		for (int i = 0; i < N; i++) {
			pq.add(new int[] { i, mat[i][i] });
		} // 물대기부터 prim알고리즘 시작
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int to = cur[0];
			if (visit[to])
				continue;
			int cw = cur[1];
			weight += cw;
			cnt++;
			visit[to] = true;
			if (cnt == N) {
				System.out.println(weight);
				return;
			}
			for (int i = 0; i < N; i++) {
				if (!visit[i]) {
					pq.add(new int[] { i, mat[to][i] });
				}
			}
		}
	}

}