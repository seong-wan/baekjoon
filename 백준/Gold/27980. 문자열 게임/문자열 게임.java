import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, M;
	static String A, B;
	static int[][] dp;

	public static void main(String[] args) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		dp = new int[M][N + 2]; //0과 N+1은 더미
		A = br.readLine();
		B = br.readLine();

		for (int i = M - 1; i >= 0; i--) {
			for (int j = 1; j <= N; j++) {
				if (B.charAt(i) == A.charAt(j - 1))
					dp[i][j] = 1;

				if (i == M - 1)
					continue;

				dp[i][j] += Math.max(dp[i + 1][j - 1], dp[i + 1][j + 1]);
			}
		}

		int ans = 0;
		for (int i = 1; i <= N; i++) {
			ans = Math.max(dp[0][i], ans);
		}

		System.out.print(ans);
	}
}