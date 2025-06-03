import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int T, n;
	static long[] dp = new long[68];
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		init();

		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			sb.append(dp[Integer.parseInt(br.readLine())]).append("\n");
		}

		System.out.println(sb);
	}

	static void init() {
		dp[0] = 1;
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 4;

		for (int i = 4; i <= 67; i++) {
			dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3] + dp[i - 4];
		}
	}
}