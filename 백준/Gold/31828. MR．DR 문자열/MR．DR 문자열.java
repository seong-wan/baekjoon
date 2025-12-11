import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static long[][] dp = new long[100001][5];
	static final int div = 1_000_000_007;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		dp[0][0] = 1;

		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < 5; j++) {
				if (j == 0)
					dp[i][j] = dp[i - 1][j] * 26;
				else {
					dp[i][j] += dp[i - 1][j] * 25;
					dp[i][j] += dp[i - 1][j - 1];
				}

				dp[i][j] %= div;
			}
		}

		System.out.print(dp[N][4]);
	}
}