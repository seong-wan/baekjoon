import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static String s;
	static int[] dp;

	public static void main(String[] args) throws Exception {
		s = br.readLine();
		dp = new int[s.length()];

		dp[0] = s.charAt(0) == '+' ? 10 : 1;

		if (dp.length >= 2)
			dp[1] = s.startsWith("+-") ? 11 : Integer.MIN_VALUE;

		for (int i = 2; i < s.length(); i++) {
			int tempNum = s.charAt(i) == '+' ? 10 : 1;

			dp[i] = calc(dp[i - 2], s.charAt(i - 1), tempNum);

			//숫자가 11이 되는 경우
			if (i >= 3 && tempNum == 1 && s.charAt(i - 1) == '+') {
				dp[i] = Math.max(dp[i], calc(dp[i - 3], s.charAt(i - 2), 11));
			}
		}

		System.out.print(dp[dp.length - 1]);
	}

	static int calc(int prev, char oper, int num) {
		if (prev == Integer.MIN_VALUE)
			return prev;

		if (oper == '+') {
			return prev + num;
		} else {
			return prev - num;
		}
	}
}