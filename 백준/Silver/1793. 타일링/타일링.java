import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static BigInteger[] dp = new BigInteger[251];
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		init();

		String input = "";
		while ((input = br.readLine()) != null && !input.isEmpty()) {
			sb.append(dp[Integer.parseInt(input)]).append("\n");
		}

		System.out.println(sb);
	}

	static void init() {

		dp[0] = new BigInteger("1");
		dp[1] = new BigInteger("1");

		for (int i = 2; i <= 250; i++) {
			dp[i] = dp[i - 1].add(dp[i - 2].multiply(new BigInteger("2")));
		}
	}
}