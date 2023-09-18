import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int N, Q, cnt, k, v;// 정점의 수, 질문의 수,볼 수 있는 동영상의 수,USADO 기준,기준 정점
	static List<int[]>[] adlist;
	static boolean[] visit;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());

		adlist = new List[N + 1];// 0은 더미

		for (int i = 1; i <= N; i++) {
			adlist[i] = new ArrayList<int[]>();
		} // 인접리스트 초기화

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			adlist[from].add(new int[] { to, w });
			adlist[to].add(new int[] { from, w });
		}

		for (int i = 0; i < Q; i++) {
			visit = new boolean[N + 1];
			cnt = 0;
			st = new StringTokenizer(br.readLine());
			k = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			visit[v] = true;
			dfs(v);
			sb.append(cnt + "\n");
		}
		System.out.println(sb);
	}

	static void dfs(int n) {
		for (int[] to : adlist[n]) {
			if (!visit[to[0]] && to[1] >= k) {
				visit[to[0]] = true;
				cnt++;
				dfs(to[0]);

			}
		}

	}

}
