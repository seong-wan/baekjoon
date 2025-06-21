import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static long[] dp;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		while (true) {
			N = Integer.parseInt(br.readLine());
			if (N == 0)
				break;

			dp = new long[N];

			dp[0] = Integer.parseInt(br.readLine());
			long ans = dp[0];

			for (int i = 1; i < N; i++) {
				int temp = Integer.parseInt(br.readLine());
				dp[i] = Math.max(temp, temp + dp[i - 1]);

				ans = Math.max(ans, dp[i]);
			}

			sb.append(ans).append("\n");
		}

		System.out.print(sb);
	}
}