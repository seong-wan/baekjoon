import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, ans, start;
	static List<int[]>[] tree;
	static boolean[] visit;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		visit = new boolean[N + 1];
		tree = new List[N + 1];
		for (int i = 1; i <= N; i++) {
			tree[i] = new ArrayList<>();
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			while (true) {
				int to = Integer.parseInt(st.nextToken());
				if (to == -1)
					break;
				int cost = Integer.parseInt(st.nextToken());
				tree[from].add(new int[] { to, cost });
			}
		} // 트리 입력
		visit[1] = true;
		dfs(1, 0);
		visit[1] = false;
		visit[start] = true;
		dfs(start, 0);

		System.out.println(ans);
	}

	static void dfs(int from, int dist) {
		if (dist > ans) {
			ans = dist;
			start = from;
		}
		for (int[] next : tree[from]) {
			if (!visit[next[0]]) {
				visit[next[0]] = true;
				dfs(next[0], dist + next[1]);
				visit[next[0]] = false;
			}
		}

	}
}