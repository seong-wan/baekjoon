import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	//범위별 팰린드롬 판정
	//판정된 팰림드롬 영역을 바탕으로 [0:i]까지의 팰린드롬 최소 분할 개수 갱신
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static String input;
	static boolean[][] isPalindrome;
	static int[] dp;

	public static void main(String[] args) throws Exception {
		input = br.readLine();
		int size = input.length();

		isPalindrome = new boolean[size][size];
		dp = new int[size];

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (i == j)
					isPalindrome[i][j] = true;
			}
		}

		for (int term = 1; term < size; term++) {
			for (int i = 0; i < size - term; i++) {
				if (input.charAt(i) == input.charAt(i + term)) {
					if (term == 1 || isPalindrome[i + 1][i + term - 1])
						isPalindrome[i][i + term] = true;
				}
			}
		}

		dp[0] = 1;
		for (int i = 1; i < size; i++) {
			dp[i] = dp[i - 1] + 1;

			for (int j = 0; j < i; j++) {
				if (isPalindrome[j][i]) {
					if (j == 0)
						dp[i] = 1;
					else
						dp[i] = Math.min(dp[i], dp[j - 1] + 1);
				}
			}
		}

		System.out.println(dp[size - 1]);
	}
}