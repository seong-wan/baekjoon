import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int T, N;
	static int[][] dp = new int[10][100001];
	static final int div = 1000000007;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		init();

		T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			int ans = 0;

			for (int i = 0; i < 10; i++) {
				ans = (ans + dp[i][N]) % div;
			}

			sb.append(ans).append("\n");
		}

		System.out.print(sb);
	}

	static void init() {
		Arrays.fill(dp[0], 1);
		for (int i = 1; i <= 9; i++) {
			dp[i][1] = 1;
		}

		for (int i = 2; i <= 100000; i++) {
			for (int j = 1; j < 10; j++) {
				dp[j][i] = (dp[j][i - 1] + dp[j - 1][i]) % div;
			}
		}
	}
}