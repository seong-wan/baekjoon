import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, M, ans = 1;
	static int[] dp;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		dp = new int[N + 1];
		dp[0] = 1;
		dp[1] = 1;

		for (int i = 2; i <= N; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}

		int start = 0;

		for (int i = 0; i < M; i++) {
			int end = Integer.parseInt(br.readLine());
			int diff = end - start - 1;
			start = end;

			ans *= dp[diff];
		}

		ans *= dp[N - start];

		System.out.println(ans);
	}
}