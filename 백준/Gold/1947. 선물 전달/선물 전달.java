import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static final int div = 1_000_000_000;
	static int[] dp = new int[1_000_001];

	public static void main(String[] args) throws Exception {
		dp[2] = 1;

		N = Integer.parseInt(br.readLine());

		for (int i = 3; i <= N; i++) {
			long temp = i - 1;
			temp *= dp[i - 1] + dp[i - 2];
			temp %= div;
			dp[i] = (int)temp;
		}

		System.out.println(dp[N]);
	}
}