import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, ans;
    static int[][] dp;
    static final int div = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine());

        dp = new int[N][10];

        dp[0][1] = 1;
        dp[0][2] = 1;
        dp[0][3] = 1;
        dp[0][4] = 1;
        dp[0][5] = 1;
        dp[0][6] = 1;
        dp[0][7] = 1;
        dp[0][8] = 1;
        dp[0][9] = 1;

        for (int i = 1; i < N; i++) {
            dp[i][0] = dp[i - 1][1];
            dp[i][1] = (dp[i - 1][0] + dp[i - 1][2]) % div;
            dp[i][2] = (dp[i - 1][1] + dp[i - 1][3]) % div;
            dp[i][3] = (dp[i - 1][2] + dp[i - 1][4]) % div;
            dp[i][4] = (dp[i - 1][3] + dp[i - 1][5]) % div;
            dp[i][5] = (dp[i - 1][4] + dp[i - 1][6]) % div;
            dp[i][6] = (dp[i - 1][5] + dp[i - 1][7]) % div;
            dp[i][7] = (dp[i - 1][6] + dp[i - 1][8]) % div;
            dp[i][8] = (dp[i - 1][7] + dp[i - 1][9]) % div;
            dp[i][9] = dp[i - 1][8];
        }

        for (int i = 0; i < 10; i++) {
            ans = (ans + dp[N - 1][i]) % div;
        }

        System.out.println(ans);
    }
}