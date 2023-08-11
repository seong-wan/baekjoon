import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static int[] dp;

	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());

		if (N > 5) {
			dp = new int[N + 1];

			dp[1] = Integer.MAX_VALUE/2;
			dp[2] = Integer.MAX_VALUE/2;
			dp[3] = 1;
			dp[4] = Integer.MAX_VALUE/2;
			dp[5] = 1;

			for (int i = 6; i < N + 1; i++) {
				dp[i] = Math.min(dp[i - 3] + 1, dp[i - 5] + 1);

			}if(dp[N]>=Integer.MAX_VALUE/2)
                	System.out.println(-1);

			else System.out.println(dp[N]);
		} else {
			if (N == 3||N==5)
				System.out.println(1);
			else
				System.out.println(-1);
		}

	}

}
