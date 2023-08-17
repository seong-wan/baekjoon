import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, chk;
	static List<Integer>[] adlist;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		adlist = new List[N];

		for (int i = 0; i < N; i++) {
			adlist[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			adlist[x].add(y);
			adlist[y].add(x);

		}
		for (int i = 0; i < N; i++) {
			visited = new boolean[N];
			dfs(i, 0);
			if (chk >= 5)
				break;
		}

		if (chk >= 5)
			System.out.println(1);
		else
			System.out.println(0);

	}

	static void dfs(int v, int cnt) {
		if (chk >= 5)
			return;
		visited[v] = true;
		cnt++;
		for (int i = 0; i < adlist[v].size(); i++) {

			if (!visited[adlist[v].get(i)]) {
				dfs(adlist[v].get(i), cnt);
				visited[adlist[v].get(i)] = false;
			}
		}
		chk = Math.max(chk, cnt);

	}

}
