import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;
	static long ans = Long.MAX_VALUE;
	static boolean[] visit;
	static int[][] fees;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		fees = new int[N][N];
		visit = new boolean[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				fees[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(0, 0, 0);

		System.out.println(ans);

	}

	static void dfs(int pre, int fee, int cnt) {
		// 앞의 도시 번호 , 앞 도시에서 다음 도시로 오는 데 필요한 비용,몇 개의 도시를 들렸는지
		if (cnt == N - 1&&fees[pre][0]!=0) {
			ans = Math.min(ans, fee + fees[pre][0]);
			return;
		}

		for (int i = 1; i < N; i++) {
			if (!visit[i] && pre != i && fees[pre][i] != 0) {
				visit[i] = true;
				dfs(i, fee + fees[pre][i], cnt + 1);// 다음 도시를 기준으로 다음 도시로 떠남
				visit[i] = false;
			}
		}
	}

}
