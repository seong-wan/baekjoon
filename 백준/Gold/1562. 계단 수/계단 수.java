import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, ans;
	static int[][][] dp;
	static final int div = 1_000_000_000;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());

		dp = new int[N + 1][10][1024];

		for (int i = 1; i <= 9; i++) {
			dp[1][i][1 << i] = 1;
		}

		for (int i = 1; i < N; i++) {
			for (int j = 0; j <= 9; j++) {
				for (int k = 1; k <= 1023; k++) {
					if (j != 0)
						dp[i + 1][j - 1][k | 1 << (j - 1)] = (dp[i + 1][j - 1][k | 1 << (j - 1)] + dp[i][j][k]) % div;

					if (j != 9)
						dp[i + 1][j + 1][k | 1 << (j + 1)] = (dp[i + 1][j + 1][k | 1 << (j + 1)] + dp[i][j][k]) % div;
				}
			}
		}
		
		for (int i = 0; i <= 9; i++) {
			ans = (ans + dp[N][i][1023]) % div;
		}

		System.out.println(ans);
	}
}