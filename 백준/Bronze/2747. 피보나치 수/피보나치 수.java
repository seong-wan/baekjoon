import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static int[] dp;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		if (N == 0)
			System.out.println(0);
		else {
			dp = new int[N + 2];
			dp[1] = 1;
			dp[2] = 1;
			for (int i = 2; i <= N; i++) {
				dp[i] = dp[i - 1] + dp[i - 2];
			}
			System.out.println(dp[N]);
		}
	}

}
