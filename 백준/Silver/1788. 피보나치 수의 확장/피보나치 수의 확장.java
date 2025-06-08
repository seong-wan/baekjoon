import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static int[] dp = new int[1_000_001];
	static final int div = 1_000_000_000;
	static boolean positive;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());

		if (N == 0) {
			System.out.println(0);
			System.out.println(0);
			return;
		}

		if (N > 0)
			positive = true;

		N = Math.abs(N);

		dp[1] = 1;

		for (int i = 2; i <= N; i++) {
			dp[i] = (dp[i - 2] + dp[i - 1]) % div;
		}

		if (!positive && N % 2 == 0)
			System.out.println(-1);
		else
			System.out.println(1);

		System.out.println(dp[N]);
	}
}