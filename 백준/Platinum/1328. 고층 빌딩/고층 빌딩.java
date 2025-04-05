import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static long[][][] dp = new long[101][101][101];
	static int N, L, R;
	static final int div = 1000000007;

	public static void main(String[] args) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		if (N == 1) {
			System.out.println(1);
			return;
		}

		for (int i = 0; i <= 100; i++) {
			for (int j = 0; j <= 100; j++) {
				Arrays.fill(dp[i][j], -1);
			}
		}

		dp[1][1][1] = 1;

		System.out.println(solve(N, L, R));
	}

	static long solve(int n, int l, int r) {
		if (n == 0 || l == 0 || r == 0)
			return 0;

		if (dp[n][l][r] != -1)
			return dp[n][l][r];

		dp[n][l][r] = (solve(n - 1, l - 1, r) + solve(n - 1, l, r - 1) + solve(n - 1, l, r) * (n - 2)) % div;

		return dp[n][l][r];
	}
}