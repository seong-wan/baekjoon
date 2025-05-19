import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static long[][] dp;
	static int N, end;
	static int[] input;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());

		input = new int[N - 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N - 1; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		end = Integer.parseInt(st.nextToken());

		dp = new long[N - 1][21];
		dp[0][input[0]] = 1;

		for (int i = 1; i < N - 1; i++) {
			for (int j = input[i]; j <= 20; j++) {
				dp[i][j - input[i]] += dp[i - 1][j];
			}

			for (int j = 0; j <= 20 - input[i]; j++) {
				dp[i][j + input[i]] += dp[i - 1][j];
			}
		}

		System.out.println(dp[N - 2][end]);
	}
}