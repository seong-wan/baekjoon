import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static long[] dp;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());

		dp = new long[N + 1];
		dp[1] = 1;

		for (int i = 2; i <= N; i++) {
			dp[i] = dp[i - 2] + dp[i - 1];
		}

		System.out.print(dp[N]);
	}
}