import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {// 루트에서 제일 먼 노드를 구하고 그 노드로부터 가장 먼 노드까지의 거리를 구하면 됨
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int n;
	static List<int[]>[] adlist;
	static boolean[] visit;
	static int ans;
	static int start;// 루트로부터 가장 먼 dfs의 시작 노드

	public static void main(String[] args) throws Exception {
		n = Integer.parseInt(br.readLine());
		adlist = new List[n + 1];
		visit = new boolean[n + 1];
		for (int i = 0; i <= n; i++) {
			adlist[i] = new ArrayList<>();
		}

		for (int i = 0; i < n - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			adlist[from].add(new int[] { to, cost });
			adlist[to].add(new int[] { from, cost });
		}
		visit[1] = true;
		dfs(1, 0);
		visit[1] = false;
		visit[start] = true;
		dfs(start, 0);
		System.out.println(ans);

	}

	static void dfs(int node, int dist) {
		if (dist > ans) {
			start = node;
			ans = dist;
		}
		for (int[] next : adlist[node]) {
			if (!visit[next[0]]) {
				visit[next[0]] = true;
				dfs(next[0], dist + next[1]);
				visit[next[0]] = false;
			}
		}

	}

}