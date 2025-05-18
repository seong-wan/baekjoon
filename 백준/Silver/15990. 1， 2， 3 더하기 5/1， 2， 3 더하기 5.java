import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int T, N;
	static long[][] dp = new long[100001][4];
	static final int div = 1_000_000_009;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		init();

		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			sb.append((dp[N][1] + dp[N][2] + dp[N][3]) % div).append("\n");
		}

		System.out.println(sb);
	}

	static void init() {
		dp[1][1] = 1;
		dp[2][2] = 1;
		dp[3][1] = 1;
		dp[3][2] = 1;
		dp[3][3] = 1;

		for (int i = 4; i <= 100000; i++) {
			dp[i][1] = (dp[i - 1][2] + dp[i - 1][3]) % div;
			dp[i][2] = (dp[i - 2][1] + dp[i - 2][3]) % div;
			dp[i][3] = (dp[i - 3][1] + dp[i - 3][2]) % div;
		}
	}
}