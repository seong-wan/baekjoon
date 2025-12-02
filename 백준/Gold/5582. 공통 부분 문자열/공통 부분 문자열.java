import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static String A, B;
	static int[][] dp = new int[4001][4001];
	static int ans;

	public static void main(String[] args) throws Exception {
		A = br.readLine();
		B = br.readLine();

		for (int i = 1; i <= A.length(); i++) {
			for (int j = 1; j <= B.length(); j++) {
				if (A.charAt(i - 1) == B.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
					ans = Math.max(ans, dp[i][j]);
				}
			}
		}

		System.out.print(ans);
	}
}