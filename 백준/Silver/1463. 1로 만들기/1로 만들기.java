import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int X;
	static int[] dp;

	public static void main(String[] args) throws Exception {
		X = Integer.parseInt(br.readLine());
		dp = new int[X + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[1] = 0;

		for (int i = 1; i <= X; i++) {
			int na = i + 1;
			int nb = 2 * i;
			int nc = 3 * i;
			if (na <= X)
				dp[na] = Math.min(dp[na], dp[i] + 1);
			if (nb <= X)
				dp[nb] = Math.min(dp[nb], dp[i] + 1);
			if (nc <= X)
				dp[nc] = Math.min(dp[nc], dp[i] + 1);

		}
		System.out.println(dp[X]);

	}

}
