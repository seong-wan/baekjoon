import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int X;
	static int[] dp;

	public static void main(String[] args) throws Exception {
		X = Integer.parseInt(br.readLine());
		dp = new int[X + 1];

		for (int i = 2; i <= X; i++) {
			dp[i] = dp[i - 1] + 1;
			if (i % 2 == 0)
				dp[i] = Math.min(dp[i], dp[i / 2] + 1);
			if (i % 3 == 0)
				dp[i] = Math.min(dp[i], dp[i / 3] + 1);

		}
		System.out.println(dp[X]);
	}
}
