import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, before, after;
	static int[][] mat;
	static boolean[] visit;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		mat = new int[N][N];
		visit = new boolean[N];

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				char temp = s.charAt(j);
				if (temp == '0')
					mat[i][j] = Integer.MAX_VALUE;
				else {
					if (Character.isLowerCase(temp))
						mat[i][j] = temp - 'a' + 1;
					else
						mat[i][j] = temp - 'A' + 27;
					before += mat[i][j];
				}
			}
		}

		prim();
	}

	static void prim() {
		PriorityQueue<int[]> pq = new PriorityQueue<>((e1, e2) -> e1[1] - e2[1]);
		pq.add(new int[] { 0, 0 });
		int cnt = 0;
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int to = cur[0];
			if (visit[to])
				continue;
			int cost = cur[1];
			cnt++;
			after += cost;
			if (cnt == N) {
				System.out.println(before - after);
				return;
			}
			visit[to] = true;
			for (int i = 0; i < N; i++) {
				if (visit[i])
					continue;
				int nextc = Math.min(mat[to][i], mat[i][to]);
				if (nextc != Integer.MAX_VALUE)
					pq.add(new int[] { i, nextc });

			}
		}
		System.out.println(-1);
	}
}